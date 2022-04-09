package com.dcs.myretailer.app.Cashier;

import java.io.Serializable;

public class YoutuberModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String emailId;

    public YoutuberModel(String name, String emailId) {
        this.name = name;
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}