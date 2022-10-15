package com.pojo;

public class merchant {
    private String name;
    private String password;
    private String phone;
    private String address;
    private int id;
    public merchant() {}

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void print(){
        System.out.println(this.name+"  "+this.password+"   "+this.phone+"  "+this.address);
    }
}
