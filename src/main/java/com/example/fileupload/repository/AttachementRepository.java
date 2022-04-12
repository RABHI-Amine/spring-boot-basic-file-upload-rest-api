package com.example.fileupload.repository;

import com.example.fileupload.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachementRepository extends JpaRepository<Attachment,Long> {
}
