package com.example.fileupload.controller;

import com.example.fileupload.entity.Attachment;
import com.example.fileupload.model.ResponseData;
import com.example.fileupload.service.AttachementService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class AttachementController {
    private AttachementService attachementService;

    public AttachementController(AttachementService attachementService) {
        this.attachementService = attachementService;
    }

    @PostMapping("/upload")
    public ResponseData uploadFile(@RequestParam("file")MultipartFile file){
        Attachment attachement = attachementService.save(file);
        //ServletUriComponentsBuilder.fromCurrentContextPath().toUriString() ==> http://localhost:8080
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/download/")
                        .path(String.valueOf(attachement.getId()))
                        .toUriString();
        return new ResponseData(attachement.getFileName(),downloadURL,attachement.getFileType(),file.getSize());
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
         Attachment attachement = null;
         attachement = attachementService.getAttachmentById(fileId);
         return ResponseEntity.ok()
                 .contentType(MediaType.parseMediaType(attachement.getFileType()))
                 .header(HttpHeaders.CONTENT_DISPOSITION,
                         "atachment;filename=\""+attachement.getFileName()+"\""
                 ).body(new ByteArrayResource(attachement.getData()));
    }
}
