package com.example.pasgenap10rpl220;

public class File {
    private String name, telp, email, address, universitas, company, gender;
    private int icon;

    //referensi model menggunakan constructor agar memudahkan dalam mengolah data
    public File(int icon, String name, String telp, String email, String address, String universitas, String company,String gender) {
        setIcon(icon);
        setName(name);
        setTelp(telp);
        setEmail(email);
        setAddress(address);
        setUniversitas(universitas);
        setCompany(company);
        setGender(gender);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniversitas() {
        return universitas;
    }

    public void setUniversitas(String universitas) {
        this.universitas = universitas;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
