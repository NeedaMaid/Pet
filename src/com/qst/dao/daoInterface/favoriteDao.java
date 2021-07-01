package com.qst.dao.daoInterface;

import com.qst.bean.Favorite;
import com.qst.bean.Pet;

import java.util.List;

public interface favoriteDao {
    boolean savePetId(int id, int petId);

/*
    Pet getPetByEmailAndPass(String userEmail, String userPwd);
*/

    Pet getPetById(int petId);


    List<Favorite> getPetIdListByPage(int startRow, int pageSize, int id);

    Integer selectPetIdCount(int petId);

    Integer getPetCount(int id);
}
