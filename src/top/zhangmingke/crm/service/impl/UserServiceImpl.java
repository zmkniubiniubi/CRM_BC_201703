package top.zhangmingke.crm.service.impl;

import org.springframework.transaction.annotation.Transactional;
import top.zhangmingke.crm.dao.UserDao;
import top.zhangmingke.crm.domain.User;
import top.zhangmingke.crm.service.UserService;
import top.zhangmingke.crm.utils.MD5Utils;

import java.util.List;

/*
* 用户管理service的实现类*/
@Transactional
public class UserServiceImpl implements UserService {
    //注入Dao
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
//    注册用户的方法
    public void regist(User user) {
        //对密码进行加密处理
        String password = MD5Utils.md5(user.getUser_password());
        user.setUser_password(password);
        user.setUser_state("1");

        //调用dao
        userDao.save(user);
    }

    @Override
    public User login(User user) {
        //对登陆的密码进行加密
        String password = MD5Utils.md5(user.getUser_password());
        user.setUser_password(password);

        return userDao.login(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
