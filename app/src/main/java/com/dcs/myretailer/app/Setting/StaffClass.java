package com.dcs.myretailer.app.Setting;

class StaffClass {
    int staff_id;
    String name;
    String permission_group;
    String PIN;
    String commission;

    public StaffClass(int staff_id, String name, String permission_group, String PIN, String commission) {
        this.staff_id = staff_id;
        this.name = name;
        this.permission_group = permission_group;
        this.PIN = PIN;
        this.commission = commission;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission_group() {
        return permission_group;
    }

    public void setPermission_group(String permission_group) {
        this.permission_group = permission_group;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }
}
