package io.qkits.corejava.corejava.nio;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * In order to delete .svn file in source, we should define multi-strategy in visitor definition.
 * User: mazhqa
 * Date: 14-4-28
 */
public class SvnInfoCleanVisitor extends SimpleFileVisitor<Path> {

    private boolean cleanMark = false;

    private boolean isSvnFolder(Path dir) {
        return ".svn".equals(dir.getFileName().toString());
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        if (isSvnFolder(dir)) {
            cleanMark = true;
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        if (cleanMark && exc == null) {
            Files.delete(dir);
            if (isSvnFolder(dir)) {
                cleanMark=false;
            }
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (cleanMark) {
            Files.setAttribute(file, "dos:readonly", false);
            Files.delete(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
