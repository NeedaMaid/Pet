package com.qst.bean;

public class Favorite {
    private int id;
    private int petId;
    private int commodityId;
    private int articleId;
    private int number;

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", petId=" + petId +
                ", commodityId=" + commodityId +
                ", articleId=" + articleId +
                ", number=" + number +
                '}';
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public Favorite() {
    }

    public Favorite(int id, int petId, int commodityId, int articleId, int number) {
        this.id = id;
        this.petId = petId;
        this.commodityId = commodityId;
        this.articleId = articleId;
        this.number = number;
    }

    public Favorite(int id, int petId, int commodityId, int articleId) {
        this.id = id;
        this.petId = petId;
        this.commodityId = commodityId;
        this.articleId = articleId;
    }
}
