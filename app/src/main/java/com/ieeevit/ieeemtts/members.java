package com.ieeevit.ieeemtts;

public class members {
    String name,pos,contact,image;

    public members() {
    }

    public members(String name, String pos, String contact, String image) {
        this.name = name;
        this.pos = pos;
        this.contact = contact;
        this.image = image;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPos() {
        return pos;
    }
    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
