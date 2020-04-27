package io.qkits.corejava.corejava.nio;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * User: mazhqa
 * Date: 14-4-28
 */
public class FilesExample {

    public static void main(String[] args) throws IOException {
        FilesExample.manipulateFiles();
    }

    /**
     * Files class is just like Collections, it's an utility class.
     * In this class including many useful methods, like transfer content to stream and copy contents to output stream.
     *
     * @throws IOException
     */
    public static void manipulateFiles() throws IOException {
        Path path = Files.createFile(Paths.get("new.txt").toAbsolutePath());
        List<String> content = new ArrayList<String>();
        content.add("Hello");
        content.add("World");
        Files.write(path, content, Charset.forName("UTF-8"));
        Files.size(path);
        byte[] bytes = Files.readAllBytes(path);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Files.copy(path, byteArrayOutputStream);
//        Files.delete(path);
    }

    public void addFileToZip(File zipFile, File fileToAdd) throws IOException {
        Map<String, String> env = new HashMap<String, String>();
        env.put("create", "true");
        FileSystem fileSystem = FileSystems.newFileSystem(URI.create("jar:" + zipFile.toURI()), env);
        Path fileToAddPath = fileToAdd.toPath();
        Path pathInZipFile = fileSystem.getPath("/" + fileToAdd.getName());
        Files.copy(fileToAddPath, pathInZipFile, StandardCopyOption.REPLACE_EXISTING);
        //use this in finally block.
        fileSystem.close();
    }

    public void asyncWrite() {
        try {
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(Paths.get("large.bin"), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            ByteBuffer byteBuffer = ByteBuffer.allocate(32 * 1024);
            Future<Integer> future = channel.write(byteBuffer, 0);
            Integer count = future.get();
            System.out.println(count);
        } catch (IOException e) {

        } catch (InterruptedException e) {

        } catch (ExecutionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
