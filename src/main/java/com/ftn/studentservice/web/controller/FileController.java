package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.service.implementation.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("files")
public class FileController {

    private final FileService fileService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("users")
    public void uploadUserFile(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "userId") Long userId
    ) throws IOException {
        fileService.UploadFile(file, userId);
    }
}
