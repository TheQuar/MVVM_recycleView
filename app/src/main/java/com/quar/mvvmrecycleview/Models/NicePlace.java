package com.quar.mvvmrecycleview.Models;

import android.widget.ImageView;

public class NicePlace {
    private String todo;
    private String time;
    private String money;
    private String number;

    public NicePlace(String todo, String time, String money, String number) {
        this.todo = todo;
        this.time = time;
        this.money = money;
        this.number = number;
    }

    public NicePlace(){

    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
