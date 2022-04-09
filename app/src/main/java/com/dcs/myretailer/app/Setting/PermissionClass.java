package com.dcs.myretailer.app.Setting;

class PermissionClass {
    Integer permission_group_id;
    String permission_group_name;

    public PermissionClass(Integer permission_group_id, String permission_group_name) {
        this.permission_group_id = permission_group_id;
        this.permission_group_name = permission_group_name;
    }

    public Integer getPermission_group_id() {
        return permission_group_id;
    }

    public void setPermission_group_id(Integer permission_group_id) {
        this.permission_group_id = permission_group_id;
    }

    public String getPermission_group_name() {
        return permission_group_name;
    }

    public void setPermission_group_name(String permission_group_name) {
        this.permission_group_name = permission_group_name;
    }
}
