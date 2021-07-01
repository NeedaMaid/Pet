package com.qst.dao.daoInterface;

import com.qst.bean.User;

public interface userDao {
    boolean saveUser(User user);

    User getUserByEmailAndPass(String userEmail, String userPwd);

     User getUserByEmail(String userEmail);
     Integer selectUserEmailCount(String userEmail);
}
