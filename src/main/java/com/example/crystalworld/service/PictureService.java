package com.example.crystalworld.service;

import com.example.crystalworld.model.entity.Picture;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PictureService {

    Picture uploadPicture(MultipartFile multipartFile) throws IOException;

    Picture getById(Long id);
}
