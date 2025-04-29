package com.healthcare.file_service.controller;

import com.healthcare.file_service.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam String fileName,
                              @RequestParam String fileType,
                              @RequestParam String filePath,
                              @RequestParam Long fileSize) {
        return fileService.uploadFile(fileName, fileType, filePath, fileSize);
    }

    @GetMapping("/download/{fileId}")
    public String downloadFile(@PathVariable Long fileId) {
        return fileService.downloadFile(fileId);
    }

    @DeleteMapping("/{fileId}")
    public String deleteFile(@PathVariable Long fileId) {
        return fileService.deleteFile(fileId);
    }

    @GetMapping("/{fileId}")
    public String getFileDetails(@PathVariable Long fileId) {
        return fileService.getFileDetails(fileId);
    }

    @GetMapping("/all")
    public String listAllFiles() {
        return fileService.listAllFiles();
    }

    @PutMapping("/update/{fileId}")
    public String updateFile(@PathVariable Long fileId,
                             @RequestParam String fileName,
                             @RequestParam String fileType,
                             @RequestParam String filePath,
                             @RequestParam Long fileSize) {
        return fileService.updateFile(fileId, fileName, fileType, filePath, fileSize);
    }

    @GetMapping("/search")
    public String getFileByName(@RequestParam String fileName) {
        return fileService.getFileByName(fileName);
    }

    @GetMapping("/pagination")
    public String listFilesWithPaginationAndSorting(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     @RequestParam(defaultValue = "id") String sortBy,
                                                     @RequestParam(defaultValue = "asc") String sortOrder) {
        return fileService.listFilesWithPaginationAndSorting(page, size, sortBy, sortOrder);
    }
}
