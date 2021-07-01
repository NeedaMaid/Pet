package com.qst.dao;

import com.qst.bean.Favorite;
import com.qst.bean.Pet;
import com.qst.dao.daoInterface.favoriteDao;
import com.qst.utils.DBUtils;

import java.util.List;

public class FavoriteDao implements favoriteDao {

    //改变number
    public boolean updateNumberByPetIdAndId(int number, int petId, int id) {
        String sql = "update favorites set number = ? where petId = ? and id=?";
        return DBUtils.update(sql, number, petId, id);
    }

    //改变number
    public boolean deleteAll(int id) {
        String sql = "delete from favorites  where id=?";
        return DBUtils.delete(sql, id);
    }

    public boolean delete(int id, int petId) {
        String sql = "delete from favorites  where id=? and petId=?";
        return DBUtils.delete(sql, id, petId);
    }

    @Override
    //保存到购物车
    public boolean savePetId(int id, int petId) {
        String sql = "insert into favorites (id,petId) values(?,?)";
        return DBUtils.save(sql, id, petId);
    }

    @Override
    public Pet getPetById(int petId) {
        return null;
    }

    @Override
    public List<Favorite> getPetIdListByPage(int startRow, int pageSize, int id) {
        String sql = "select * from favorites where id=? limit ?,?";
        return DBUtils.getList(Favorite.class, sql, id, startRow, pageSize);
    }

    @Override
    public Integer selectPetIdCount(int petId) {
        String sql = "select count(*) from favorites where petId = ?";
        return DBUtils.getCount(sql, petId);
    }

    public Integer selectNumberBypetIdAndId(int petId, int id) {
        String sql = "select number from favorites where petId = ? and Id=?";
        return DBUtils.getCount(sql, petId, id);
    }

    @Override
    public Integer getPetCount(int id) {
        String sql = "select count(*) from favorites where id=?";
        return DBUtils.getCount(sql, id);
    }

    public boolean savePetIdAndNumber(int id, int petId, int number) {
        String sql = "insert into favorites (id,petId,number) values(?,?,?)";
        return DBUtils.save(sql, id, petId, number);
    }
}
