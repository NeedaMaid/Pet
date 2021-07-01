package com.qst.bean;

public class order {
    private int id;
    private int petid;
    private int number;

    @Override
    public String toString() {
        return "order{" +
                "id=" + id +
                ", petid=" + petid +
                ", number=" + number +
                '}';
    }

    public order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPetid() {
        return petid;
    }

    public void setPetid(int petid) {
        this.petid = petid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public order(int id, int petid, int number) {
        this.id = id;
        this.petid = petid;
        this.number = number;
    }
}
