package com.example.crystalworld.web;

import com.example.crystalworld.model.entity.Picture;
import com.example.crystalworld.model.entity.Product;
import com.example.crystalworld.service.PictureService;
import com.example.crystalworld.service.ProductService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PictureRestController {
    private final ProductService productService;
    private final PictureService pictureService;

    public PictureRestController(ProductService productService, PictureService pictureService) {
        this.productService = productService;
        this.pictureService = pictureService;
    }


    @GetMapping("/downloadPicture/{productId}")
    public HttpEntity<byte[]> downloadPicture(@PathVariable("productId") Long id) {

        Product product = productService.findProductById(id);
        Picture picture = product.getPicture();

        if (picture == null) {
            return null;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MimeTypeUtils.parseMimeType(picture.getPictureType())));
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "atachment; filename=" + picture.getPictureName());
        headers.setContentLength(picture.getBytes().length);

        return  new HttpEntity<>(picture.getBytes(), headers);
    }

    @GetMapping("/downloadPictureById/{pictureId}")
    public HttpEntity<byte[]> downloadPictureById(@PathVariable("pictureId") Long id) {

        Picture picture = pictureService.getById(id);

        if (picture == null) {
            return null;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MimeTypeUtils.parseMimeType(picture.getPictureType())));
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "atachment; filename=" + picture.getPictureName());
        headers.setContentLength(picture.getBytes().length);

        return  new HttpEntity<>(picture.getBytes(), headers);
    }

}
