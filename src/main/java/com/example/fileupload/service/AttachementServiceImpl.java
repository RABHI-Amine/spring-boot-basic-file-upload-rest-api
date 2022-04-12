package com.example.fileupload.service;

import com.example.fileupload.entity.Attachment;
import com.example.fileupload.repository.AttachementRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AttachementServiceImpl implements AttachementService{
    private AttachementRepository attachementRepository;

    public AttachementServiceImpl(AttachementRepository attachementRepository) {
        this.attachementRepository = attachementRepository;
    }

    @Override
    public Attachment save(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Attachment attachment = null;
        try {
            attachment = new Attachment(null,fileName,file.getContentType(),file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attachementRepository.save(attachment);
    }

    @Override
    public Attachment getAttachmentById(String fileId) throws Exception {
        return attachementRepository.findById(Long.valueOf(fileId)).orElseThrow(() -> new Exception("File not found "+fileId));
    }
}
