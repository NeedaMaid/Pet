package com.qst.bean;

public class Pet {
    private int petId;
    private String petName;
    private String petGender;
    private float value;
    private String img;
    private int number;

    public Pet() {
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petId=" + petId +
                ", petName='" + petName + '\'' +
                ", petGender='" + petGender + '\'' +
                ", value=" + value +
                ", img='" + img + '\'' +
                ", number=" + number +
                '}';
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetGender() {
        return petGender;
    }

    public void setPetGender(String petGender) {
        this.petGender = petGender;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Pet(int petId, String petName, String petGender, float value, String img, int number) {
        this.petId = petId;
        this.petName = petName;
        this.petGender = petGender;
        this.value = value;
        this.img = img;
        this.number = number;
    }
}
