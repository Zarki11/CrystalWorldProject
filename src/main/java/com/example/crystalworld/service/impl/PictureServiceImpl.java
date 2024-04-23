package com.example.crystalworld.service.impl;

import com.example.crystalworld.model.entity.Picture;
import com.example.crystalworld.repository.PictureRepository;
import com.example.crystalworld.service.PictureService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public Picture uploadPicture(MultipartFile picture) throws IOException {

        Picture pic = new Picture(picture.getContentType(), picture.getName(), picture.getBytes());
        pictureRepository.save(pic);

        return pic;
    }

    public Picture getById(Long id) {
        return pictureRepository.findById(id).get();
    }
}
