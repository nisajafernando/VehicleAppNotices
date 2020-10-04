// IT19170176
// FERNANDO W.N.D
// CarMart Notices

package com.example.vehicleappnotices;

public class Model {

    String id, image, heading, name, mobile, email,province, notice_info, add_notice, update_notice;

    public Model(String id, String image, String heading, String name, String mobile, String email, String notice_info, String add_notice, String update_notice) {
        this.id = id;
        this.image = image;
        this.heading = heading;
        this.name = name;
        this.mobile = mobile;
        this.email = email;


        this.notice_info = notice_info;
        this.add_notice = add_notice;
        this.update_notice = update_notice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getNotice_info() {
        return notice_info;
    }

    public void setNotice_info(String notice_info) {
        this.notice_info = notice_info;
    }

    public String getAdd_notice() {
        return add_notice;
    }

    public void setAdd_notice(String add_notice) {
        this.add_notice = add_notice;
    }

    public String getUpdate_notice() {
        return update_notice;
    }

    public void setUpdate_notice(String update_notice) {
        this.update_notice = update_notice;
    }

    public void toLowerCase() {
    }
}


