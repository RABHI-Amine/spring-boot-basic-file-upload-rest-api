package com.example.fileupload.service;


import com.example.fileupload.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

public interface AttachementService {
    Attachment save(MultipartFile file);

    Attachment getAttachmentById(String fileId) throws Exception;
}
