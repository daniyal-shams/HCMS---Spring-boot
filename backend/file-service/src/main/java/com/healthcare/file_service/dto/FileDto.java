package com.healthcare.file_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class FileDto {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Doctor ID (auto-generated)")
    private Long id;
    @Schema(example = "patient record")
    private String fileName;
    @Schema(example = "PDF")
    private String fileType;
    @Schema(example = "/patients")
    private String filePath;
    @Schema(example = "5")
    private Long fileSize;

    public FileDto() {
    }

    public FileDto(Long id, String fileName, String fileType, String filePath, Long fileSize) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
    @Override
    public String toString() {
        return "FileDto{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }

}
