package microservice.module.eurekaclientfilestorage.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import microservice.module.eurekaclientfilestorage.domain.services.fileManagers.DirectoryManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import microservice.module.eurekaclientfilestorage.utils.Common;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller responsible only for working with files
 */
@Tag(name = "Files controller")
@RestController
@RequestMapping("/files")
@Slf4j
public class FilesController {

    /**
     * Loads your file with a list of targets.
     * @param file - expected file
     * @return upload result message
     */
    @Operation(
            summary = "Uploads the data file.",
            description = "Upload your file with data to be processed in the repository.",
            parameters = {
                    @Parameter(
                            name = "file",
                            description = "The file to be uploaded.",
                            required = true,
                            schema = @Schema(type = "string", format = "binary")
                    )
            }
    )
    @ApiResponse(responseCode = "200", description = "File uploaded successfully.")
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema))
    @PostMapping("/upload")
    public ResponseEntity<String> uploadAnyFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload. File is empty.");
        }

        try {
            String originalFileName = file.getOriginalFilename();
            if (originalFileName == null || originalFileName.contains("..")) {
                return ResponseEntity.badRequest().body("Invalid file name!");
            }

            String fileName = System.currentTimeMillis() + "_" + originalFileName;
            String filePath = Common.getUploadFilesPath() + fileName;

            File destinationFile = new File(filePath);
            destinationFile.getParentFile().mkdirs(); // Создаст директорию, если её нет

            file.transferTo(destinationFile);
            log.info("Saved the file: {}", filePath);

            return ResponseEntity.ok("File uploaded successfully: " + fileName);
        } catch (IOException e) {
            log.error("File upload failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload file: " + e.getMessage());
        }
    }

    @Operation(
            summary = "Clears the log storage.",
            description = "Deletes all files in the log storage folder."
    )
    @ApiResponse(responseCode = "200", description = "Successful operation. Log storage cleared.")
    @PostMapping("/clearLogStorage")
    public String clearLogStorage () {
        log.info("Clearing log storage...");
        return DirectoryManager.clearTheFilesInPath(Common.getLogPath(), "Log storage");
    }

    @Operation(
            summary = "Clears the uploaded files storage.",
            description = "Deletes all files in the uploaded files storage folder."
    )
    @ApiResponse(responseCode = "200", description = "Successful operation. Uploaded files storage cleared.")
    @PostMapping("/clearUploadFilesStorage")
    public String clearUploadFilesStorage () {
        log.info("Clearing uploaded files storage...");
        return DirectoryManager.clearTheFilesInPath(Common.getUploadFilesPath(), "Uploaded csv storage");
    }

    @PostMapping("/clearAllStorage")
    public String clearAllStorage() {
        log.info("Clearing all files storage...");
        List<String> responseMessageList = new ArrayList<>();
        responseMessageList.add(clearUploadFilesStorage());
        responseMessageList.add(clearLogStorage());
        return responseMessageList.toString();
    }
}