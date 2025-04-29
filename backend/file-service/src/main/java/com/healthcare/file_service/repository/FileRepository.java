package com.healthcare.file_service.repository;

import com.healthcare.file_service.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    File findByFileName(String filename);

}
