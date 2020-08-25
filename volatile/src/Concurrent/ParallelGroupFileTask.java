package Concurrent;

import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ParallelGroupFileTask implements Runnable {
    private final String fileName;
    private final ConcurrentLinkedQueue<File> directories;
    private final SerialFileSearch.Result parallelResult;
    private boolean found;

    public ParallelGroupFileTask(String fileName, SerialFileSearch.Result parallelResult,
                                 ConcurrentLinkedQueue<File> directories) {
        this.fileName = fileName;
        this.parallelResult = parallelResult;
        this.directories = directories;
        this.found = false;
    }

    @Override
    public void run() {
        while (directories.size() > 0) {
            File file = directories.poll();
            processDirectory(file, fileName, parallelResult);
            if (found) {
                System.out.printf("%s has found the file%n",
                        Thread.currentThread().getName());
                System.out.printf("Parallel Search: Path: %s%n",
                        parallelResult.getAbsolutePath());
                return;
            }
        }
    }

    private void processDirectory(File file, String fileName, SerialFileSearch.Result parallelResult) {
        File[] contents;
        contents = file.listFiles();
        if ((contents == null) || (contents.length == 0)) {
            return;
        }
        for (File content : contents) {
            if (content.isDirectory()) {
                processDirectory(content, fileName, parallelResult);
                if (Thread.currentThread().isInterrupted()) {
                    try {
                        throw new InterruptedException();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (found) {
                    return;
                }
            } else {
                processFile(content, fileName, parallelResult);
                if (Thread.currentThread().isInterrupted()) {
                    try {
                        throw new InterruptedException();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (found) {
                    return;
                }
            }
        }
    }


    private void processFile(File content, String fileName,
                             SerialFileSearch.Result parallelResult) {
        if (content.getName().equals(fileName)) {
            parallelResult.setAbsolutePath(content.getAbsolutePath());
            this.found = true;
        }
    }
    public boolean getFound() {
        return found;
    }
}

