package com.healthcare.file_service.service;

import com.healthcare.file_service.model.File;
import com.healthcare.file_service.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public String uploadFile(String fileName, String fileType, String filePath, Long fileSize) {
        File file = new File();
        file.setFileName(fileName);
        file.setFileType(fileType);
        file.setFilePath(filePath);
        file.setFileSize(fileSize);
        fileRepository.save(file);
        return "File uploaded successfully with ID: " + file.getId();
    }

    @Override
    public String downloadFile(Long fileId) {
        Optional<File> file = fileRepository.findById(fileId);
        if (file.isPresent()) {
            return "Download URL: " + file.get().getFilePath();
        } else {
            return "File not found with ID: " + fileId;
        }
    }

    @Override
    public String deleteFile(Long fileId) {
        if (fileRepository.existsById(fileId)) {
            fileRepository.deleteById(fileId);
            return "File deleted successfully with ID: " + fileId;
        } else {
            return "File not found with ID: " + fileId;
        }
    }

    @Override
    public String getFileDetails(Long fileId) {
        Optional<File> file = fileRepository.findById(fileId);
        if (file.isPresent()) {
            File f = file.get();
            return "File Details -> Name: " + f.getFileName() + ", Type: " + f.getFileType() +
                    ", Size: " + f.getFileSize() + " bytes, Path: " + f.getFilePath();
        } else {
            return "File not found with ID: " + fileId;
        }
    }

    @Override
    public String listAllFiles() {
        List<File> files = fileRepository.findAll();
        if (files.isEmpty()) {
            return "No files found!";
        }
        StringBuilder sb = new StringBuilder();
        files.forEach(file -> sb.append("ID: ").append(file.getId())
                .append(", Name: ").append(file.getFileName())
                .append(", Type: ").append(file.getFileType())
                .append(", Size: ").append(file.getFileSize())
                .append(" bytes\n"));
        return sb.toString();
    }

    @Override
    public String updateFile(Long fileId, String fileName, String fileType, String filePath, Long fileSize) {
        Optional<File> optionalFile = fileRepository.findById(fileId);
        if (optionalFile.isPresent()) {
            File file = optionalFile.get();
            file.setFileName(fileName);
            file.setFileType(fileType);
            file.setFilePath(filePath);
            file.setFileSize(fileSize);
            fileRepository.save(file);
            return "File updated successfully with ID: " + fileId;
        } else {
            return "File not found with ID: " + fileId;
        }
    }

    @Override
    public String getFileByName(String fileName) {
        File file = fileRepository.findByFileName(fileName);
        if (file != null) {
            return "File found: " + file.getFileName() + ", ID: " + file.getId();
        } else {
            return "File not found with name: " + fileName;
        }
    }

    @Override
    public String listFilesWithPaginationAndSorting(int page, int size, String sortBy, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, 
                sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
        Page<File> filePage = fileRepository.findAll(pageable);

        if (filePage.isEmpty()) {
            return "No files found!";
        }

        StringBuilder sb = new StringBuilder();
        filePage.getContent().forEach(file -> sb.append("ID: ").append(file.getId())
                .append(", Name: ").append(file.getFileName())
                .append(", Type: ").append(file.getFileType())
                .append(", Size: ").append(file.getFileSize())
                .append(" bytes\n"));
        return sb.toString();
    }
}
