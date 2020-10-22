package com.example.activiti_customlist;

public class LS {
    String title;
    String descrip;
    int image;

    public LS(String title, String descrip, int image) {
        this.title = title;
        this.descrip = descrip;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
