package top.zhangmingke.crm.dao;

import top.zhangmingke.crm.domain.User;

import java.util.List;

/*
* 用户管理Dao接口
* */
public interface UserDao {
    void save(User user);

    User login(User user);

    List<User> findAll();
}
