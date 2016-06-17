package com.jasonxu.recyclerview.Entity;

import java.io.Serializable;

/**
 * Created by t_xuz on 6/17/16.
 */
public class ItemData implements Serializable{

    private String name;
    private int id;
    private int imgId;//本地资源

    public ItemData( int id,String name, int imgId) {
        this.name = name;
        this.id = id;
        this.imgId = imgId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
