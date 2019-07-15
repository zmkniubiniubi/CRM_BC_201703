package top.zhangmingke.crm.service;

import top.zhangmingke.crm.domain.User;

import java.util.List;

/*
* 用户管理service层的接口*/
public interface UserService {
    void regist(User user);

    User login(User user);

    List<User> findAll();
}
