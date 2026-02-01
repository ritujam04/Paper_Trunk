package com.example.project.Customer;

public class Location {
    String name,contact,location,bindingtype,imup,Id;

    public Location(String name, String contact, String location, String bindingtype, String imup) {
        this.name = name;
        this.contact = contact;
        this.location = location;
        this.bindingtype = bindingtype;
        this.imup = imup;
    }
    public Location(){
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

    public String getBindingtype() {
        return bindingtype;
    }

    public void setBindingtype(String bindingtype) {
        this.bindingtype = bindingtype;
    }

    public String getImup() {
        return imup;
    }

    public void setImup(String imup) {
        this.imup = imup;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}



