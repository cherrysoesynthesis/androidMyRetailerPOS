package com.dcs.myretailer.app;

class Task {
    Integer id;
    String title;
    Integer parent_id;
    Boolean completed_status;
    String created_datetime;
    String modified_datetime;

    public Task(Integer id, String title, Integer parent_id, Boolean completed_status, String created_datetime, String modified_datetime) {
        this.id = id;
        this.title = title;
        this.parent_id = parent_id;
        this.completed_status = completed_status;
        this.created_datetime = created_datetime;
        this.modified_datetime = modified_datetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Boolean getCompleted_status() {
        return completed_status;
    }

    public void setCompleted_status(Boolean completed_status) {
        this.completed_status = completed_status;
    }

    public String getCreated_datetime() {
        return created_datetime;
    }

    public void setCreated_datetime(String created_datetime) {
        this.created_datetime = created_datetime;
    }

    public String getModified_datetime() {
        return modified_datetime;
    }

    public void setModified_datetime(String modified_datetime) {
        this.modified_datetime = modified_datetime;
    }


}
