package microservice.module.eurekaclientfilestorage.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {
    public static final String projectPath = "C:/Users/Rodion/IdeaProjects/saiko-rodion/eureka-client-file-storage/";
    public static final String relativePathUploadFiles = "";

    private static final String uploadFilesPath = "/local-file-storage/data/upload-files/";
    private static final String logPath = "/local-file-storage/data/reports/log/";

    public static String getUploadFilesPath() {
        String localUploadFilesPath = projectPath.concat(uploadFilesPath);
        File f = new File(localUploadFilesPath);
        boolean b = f.isDirectory() && f.exists();
        return b ? localUploadFilesPath : uploadFilesPath;
    }

    public static String getLogPath() {
        return new File(logPath).isDirectory() ? logPath : ".".concat(logPath);
    }

    public static String getTimeStamp () {
        return new SimpleDateFormat("yyyyMMdd-HHmmss-SSS").format(new Date());
    }
}
