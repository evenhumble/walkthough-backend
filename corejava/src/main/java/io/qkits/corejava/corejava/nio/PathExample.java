package io.qkits.corejava.corejava.nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileTime;

/**
 * User: mazhqa
 * Date: 14-4-28
 */
public class PathExample {

    public static void main(String[] args) {

    }

    public void usePath() {
        Path path1 = Paths.get("folder1", "sub1");
        Path path2 = Paths.get("folder2", "sub2");
        //folder1/sub1/folder2/sub2
        path1.resolve(path2);
        //folder1/folder2/sub2
        path1.resolveSibling(path2);

        // ../../folder2/sub2
        path1.relativize(path2);
        //folder1
        path1.subpath(0, 1);

        //false
        path1.startsWith(path2);
        //false
        path2.endsWith(path1);

        //folder2/my.txt
        Paths.get("folder1/./../folder2/my.text").normalize();
    }

    /**
     * use directorystream to access files.
     * but directorystream can only access direct sub files(non-recursive).
     *
     * @throws IOException
     */
    public void listFiles() throws IOException {
        Path path = Paths.get("");
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path, "*.java");
        for (Path entry : directoryStream) {
            //use entry to do somthing

        }
        //usually use this close method in finally block...
        directoryStream.close();

    }

    /**
     * read file attributes in nio2
     *
     * @throws IOException
     */
    public void useFileAttributeView() throws IOException {
        Path path = Paths.get("content.txt");
        DosFileAttributeView fileAttributeView = Files.getFileAttributeView(path, DosFileAttributeView.class);
        if (fileAttributeView != null) {
            DosFileAttributes dosFileAttributes = fileAttributeView.readAttributes();
            System.out.println(dosFileAttributes);
        }
    }

    /**
     * check that the path file's lastmodified time is in control(the substraction is lower than intervalInMillis)
     * @param path
     * @param intervalInMillis
     * @return
     * @throws IOException
     */
    public boolean checkUpdatesRequired(Path path, int intervalInMillis) throws IOException {
        FileTime fileTime = (FileTime) Files.getAttribute(path, "lastModifiedTime");
        long currentTime = System.currentTimeMillis();
        return currentTime - fileTime.toMillis() > intervalInMillis;
    }

    /**
     * Use this method to listening file system changed.
     * @throws IOException
     * @throws InterruptedException
     */
    public void calculate() throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get("").toAbsolutePath();
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        while (true) {
            WatchKey watchKey = watchService.take();
            for (WatchEvent<?> watchEvent : watchKey.pollEvents()) {
                Path createPath = (Path) watchEvent.context();
                Path resolvePath = path.resolve(createPath);
                long size = Files.size(resolvePath);
                System.out.println(resolvePath + "--->" + size);
            }
            watchKey.reset();
            //if you want to cancel listen to the path change, you should cancel the event watch key.
            //watchKey.cancel();
        }
    }

}
