package com.oracle.xing.model;

import java.time.LocalDateTime;

public class BaseModel {

    private String id;

    private int archive;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getArchive() {
        return archive;
    }

    public void setArchive(int archive) {
        this.archive = archive;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "id='" + id + '\'' +
                ", archive=" + archive +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }

    public void print(){
        System.out.println(toString());
    }
}
