package com.dcs.myretailer.app;

public class UserAccess {
    int UserAccessID;
    String UserAccessUser;
    String UserAccessPassword;
    String UserAccessSLCode;
    String UserAccessSLName;
    String UserAccessaccessable;
    long UserAccessDateTime;


    public UserAccess(int userAccessID, String user,  String password, String slCode, String slName, String accessable_, long userAccessDateTime) {
        UserAccessID = userAccessID;
        UserAccessUser = user;
        UserAccessPassword = password;
        UserAccessSLCode = slCode;
        UserAccessSLName = slName;
        UserAccessaccessable = accessable_;
        UserAccessDateTime = userAccessDateTime;
    }

    public int getUserAccessID() {
        return UserAccessID;
    }

    public void setUserAccessID(int userAccessID) {
        UserAccessID = userAccessID;
    }

    public String getUserAccessUser() {
        return UserAccessUser;
    }

    public String getUserAccessPassword() {
        return UserAccessPassword;
    }

    public void setUserAccessPassword(String userAccessPassword) {
        UserAccessPassword = userAccessPassword;
    }

    public void setUserAccessUser(String userAccessUser) {
        UserAccessUser = userAccessUser;
    }

    public String getUserAccessSLCode() {
        return UserAccessSLCode;
    }

    public void setUserAccessSLCode(String userAccessSLCode) {
        UserAccessSLCode = userAccessSLCode;
    }

    public String getUserAccessSLName() {
        return UserAccessSLName;
    }

    public void setUserAccessSLName(String userAccessSLName) {
        UserAccessSLName = userAccessSLName;
    }

    public String getUserAccessaccessable() {
        return UserAccessaccessable;
    }

    public void setUserAccessaccessable(String userAccessaccessable) {
        UserAccessaccessable = userAccessaccessable;
    }

    public long getUserAccessDateTime() {
        return UserAccessDateTime;
    }

    public void setUserAccessDateTime(long userAccessDateTime) {
        UserAccessDateTime = userAccessDateTime;
    }
}