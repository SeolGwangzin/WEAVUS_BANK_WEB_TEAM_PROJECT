package com.weavus.weavus_bankweb.dto.users;

public class UserUpdateForm {
    private String first_name;
    private String last_name;
    private String postal_code;
    private String prefecture;
    private String city;
    private String address_detail;
    private String phone_number;

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }

    public String getPrefecture() {
        return prefecture;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setAddress_detail(String address_detail) {
        this.address_detail = address_detail;
    }

    public String getAddress_detail() {
        return address_detail;
    }
}
