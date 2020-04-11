package io.qkits.fileservice.repository;

import io.qkits.fileservice.domain.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepo extends JpaRepository<FileUpload,Integer> {
}