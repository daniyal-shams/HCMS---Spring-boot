package com.healthcare.file_service.service;

import org.springframework.stereotype.Service;

@Service 
public interface FileService {

    String uploadFile(String fileName, String fileType, String filePath, Long fileSize);

    String downloadFile(Long fileId);

    String deleteFile(Long fileId);

    String getFileDetails(Long fileId);

    String listAllFiles();

    String updateFile(Long fileId, String fileName, String fileType, String filePath, Long fileSize);

    String getFileByName(String fileName);

    // pagination and sorting
    String listFilesWithPaginationAndSorting(int page, int size, String sortBy, String sortOrder);

}
