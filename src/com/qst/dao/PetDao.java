package com.qst.dao;

import com.qst.bean.Pet;
import com.qst.dao.daoInterface.petDao;
import com.qst.utils.DBUtils;

import java.util.ArrayList;
import java.util.List;

public class PetDao implements petDao {

    //改变number
    public boolean updateNumberByPetId(int number, int petId) {
        String sql = "update pet set number = ? where petId = ?";
        return DBUtils.update(sql, number, petId);
    }

    public List<Pet> getPetByName(String name) {
        String sql = "select * from pet where petName like  \'%?%\'";
        return DBUtils.getList(Pet.class, name);
    }

    @Override
    public boolean savePet(Pet pet) {
        return false;
    }

    @Override
    public Pet getPetById(int petId) {
        String sql = "select * from pet where petId = ?";
        return DBUtils.getSingleObj(Pet.class, sql, petId);
    }

    public List<Pet> getPetListNameByPage(int startRow, int pageSize, String petName) {
        String sql = "select * from pet where petName like ?  limit  ? , ?";
        List<Pet> pet = DBUtils.getListLike(Pet.class, sql, petName, startRow, pageSize);
        return pet;
    }


    @Override
    public List<Pet> getPetListByPage(int startRow, int pageSize) {
        String sql = "select * from pet limit ? , ?";
        List<Pet> pet = DBUtils.getList(Pet.class, sql, startRow, pageSize);
        return pet;
    }

    @Override
    public Integer selectPetNameCount(String petName) {
        return null;
    }

    @Override
    public Integer getPetCount() {
        String sql = "select  count(*) from pet";
        Integer count = DBUtils.getCount(sql);
        return count;
    }

    public Integer getPetCountByName(String name) {
        String sql = "select count(*) from pet where petName like ?";
        Integer count = DBUtils.getCountLike(sql, name);
        return count;
    }
}
