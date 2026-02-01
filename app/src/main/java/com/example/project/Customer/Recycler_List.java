package com.example.project.Customer;

public class Recycler_List {
    private String image;
    private String name;
    private String contact;
    private String location;

    public Recycler_List(String image, String name, String contact, String location) {
        this.image = image;
        this.name = name;
        this.contact = contact;
        this.location = location;
    }

    public Recycler_List() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
