package com.jasonxu.searchviewdemo.Entity;

import java.io.Serializable;

/**
 * Created by jason_000 on 2016/8/15.
 * 联想词bean
 */
public class AssociationData implements Serializable{

    private int id;
    private String associate;

    public AssociationData(int id, String associate) {
        this.id = id;
        this.associate = associate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssociate() {
        return associate;
    }

    public void setAssociate(String associate) {
        this.associate = associate;
    }

    @Override
    public String toString() {
        return "AssociationData{" +
                "id=" + id +
                ", associate='" + associate + '\'' +
                '}';
    }
}
