package com.example.crystalworld.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id")
    private Long id;
    @Column(name = "picture_type")
    private String pictureType;
    @Column(name = "picture_name")
    private String pictureName;
    @Lob
    @Column(length = Integer.MAX_VALUE)
    private byte[] bytes;

    public Picture(String pictureType, String pictureName, byte[] bytes) {
        this.pictureType = pictureType;
        this.pictureName = pictureName;
        this.bytes = bytes;
    }

    public Picture() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPictureType() {
        return pictureType;
    }

    public void setPictureType(String pictureType) {
        this.pictureType = pictureType;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

}
