package Concurrent;

import java.io.File;

public class SerialFileSearch {
    public static void searchFiles(File file, String fileName,
                                   Result result) {
        File[] contents;
        contents=file.listFiles();
        if ((contents==null) || (contents.length==0)) {
            return;
        }
        for (File content : contents) {
            if (content.isDirectory()) {
                searchFiles(content,fileName, result);
            } else {
                if (content.getName().equals(fileName)) {
                    result.setAbsolutePath(content.getAbsolutePath());
                    result.setFound(true);
                    System.out.printf("Serial Search: Path: %s%n",
                            result.getAbsolutePath());
                    return;
                }
            }
            if (result.isFound()) {
                return;
            }
        }
    }

    class Result{

       private String absolutePath;

        public String getAbsolutePath() {
            return absolutePath;
        }

        public void setAbsolutePath(String absolutePath) {
            this.absolutePath = absolutePath;
        }

        public boolean isFound() {
            return found;
        }

        public void setFound(boolean found) {
            this.found = found;
        }

        private boolean found;



    }


    }