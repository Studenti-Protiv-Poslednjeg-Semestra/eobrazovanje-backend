package com.ftn.studentservice.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    private String pathString = System.getProperty("user.dir")+"\\src\\main\\resources\\files\\";

    public void UploadFile(MultipartFile file, Long userId) throws IllegalStateException, IOException {
        File directory = new File(pathString+userId.toString());
        if (! directory.exists()){
            directory.mkdirs();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }



        file.transferTo(new File(pathString+"\\"+ userId +"\\"+file.getOriginalFilename()));
    }
}
