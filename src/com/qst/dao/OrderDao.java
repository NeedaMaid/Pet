package com.qst.dao;

import com.qst.bean.Pet;
import com.qst.bean.order;
import com.qst.utils.DBUtils;

import java.util.List;

public class OrderDao {
    public boolean saveOrder(int id, int petId, int number) {
        String sql = "insert into myOrder (id,petid,number) values(?,?,?)";
        return DBUtils.save(sql, id, petId, number);
    }

    public Integer getOrderCount(int id) {
        String sql = "select  count(*) from myOrder where id=?";
        Integer count = DBUtils.getCount(sql, id);
        return count;
    }

    public List<order> getPetIdListByPage(int startRow, int pageSize, int id) {
        String sql = "select * from myOrder where id=? limit ?,?";
        return DBUtils.getList(order.class, sql, id, startRow, pageSize);
    }
}
