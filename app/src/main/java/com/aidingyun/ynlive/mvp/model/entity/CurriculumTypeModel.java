package com.aidingyun.ynlive.mvp.model.entity;

/**
 * Created by Administrator on 2015/11/24.
 */
public class CurriculumTypeModel {
    private int id;
    private int imgSrc;
    private String img;
    private String title;

    public CurriculumTypeModel() {
    }

    public CurriculumTypeModel(int id, String img, String title) {
        this.id = id;
        this.img = img;
        this.title = title;
    }

    public CurriculumTypeModel(int imgSrc, String title) {
        this.title = title;
        this.imgSrc = imgSrc;
    }

    public CurriculumTypeModel(String img, String title) {
        this.img = img;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
    }
}
