package com.qst.dao;

import com.qst.bean.User;
import com.qst.dao.daoInterface.userDao;
import com.qst.utils.DBUtils;

public class UserDAO implements userDao {

    //保存注册对象
    @Override
    public boolean saveUser(User user) {
        String sql = "insert into user(email,password,gender,telephone) " +
                "values(?,?,?,?)";
        return DBUtils.save(sql, user.getemail(), user.getPassword(), user.getGender(), user.getTelephone());
    }

    //根据email和密码查询用户
    @Override
    public User getUserByEmailAndPass(String userEmail, String userPwd) {

        String sql = "select * " +
                "from user where email=? AND password=?";
        return DBUtils.getSingleObj(User.class, sql, userEmail, userPwd);

    }

    //根据email查询用户
    @Override
    public User getUserByEmail(String userEmail) {

        String sql = "select * " +
                "from user where email=?";
        return DBUtils.getSingleObj(User.class, sql, userEmail);

    }


    //查询email是否已经注册
    @Override
    public Integer selectUserEmailCount(String userEmail) {
        String sql = "select count(*) from user where email=?";
        Integer count = DBUtils.getCount(sql, userEmail);
        return count;

    }

    //判断当前用户是否有简历
    public Integer isExistResume(int userId) {
        String sql = "select id from tb_resume_basicinfo b where b.user_ID=?";
        Integer resumeId = DBUtils.getCount(sql, userId);
        return resumeId;
    }
}
