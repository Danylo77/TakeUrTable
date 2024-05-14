package com.example.takeurtableapp.forms;

public class ReservationForm {
    private String name;
    private String phone;

    // Конструктори, геттери та сеттери
    public ReservationForm() {}

    public ReservationForm(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

