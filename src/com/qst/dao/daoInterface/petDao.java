package com.qst.dao.daoInterface;

import com.qst.bean.Pet;

import java.util.List;

public interface petDao {
    boolean savePet(Pet pet);

/*
    Pet getPetByEmailAndPass(String userEmail, String userPwd);
*/

    Pet getPetById(int petId);

    List<Pet> getPetListByPage(int startRow, int pageSize);

    Integer selectPetNameCount(String petName);

    Integer getPetCount();
}
