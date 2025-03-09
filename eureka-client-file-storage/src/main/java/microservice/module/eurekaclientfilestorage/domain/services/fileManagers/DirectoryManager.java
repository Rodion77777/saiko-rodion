package microservice.module.eurekaclientfilestorage.domain.services.fileManagers;

import java.io.File;

public class DirectoryManager {

    public static String clearTheFilesInPath (String path, String dataName) {
        File directory = new File(path);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files == null) return "Directory is empty: " + path;
            boolean deleted = false;
            for (File file : files)
                if (file.isFile()) deleted = file.delete();
            if (deleted) return "Directory (" + dataName + ") has been successfully cleared";
            else return "Something wrong! File quantity: " + files.length;
        } else {
            String msg = "Directory: \"" + directory + "\" does not exist or is not a directory.";
            System.out.println(msg);
            return msg;
        }
    }

}
