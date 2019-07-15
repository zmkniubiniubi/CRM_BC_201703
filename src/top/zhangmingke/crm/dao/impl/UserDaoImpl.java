package top.zhangmingke.crm.dao.impl;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import top.zhangmingke.crm.dao.UserDao;
import top.zhangmingke.crm.domain.User;

import java.util.List;

/*用户管理的Dao的实现类
* */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
    @Override
    public void save(User user) {
        this.getHibernateTemplate().save(user);

    }

    @Override
    //根据用户名和密码进行查询
    public User login(User user) {
        List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code=? and user_password=?", user.getUser_code(), user.getUser_password());
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return (List<User>) this.getHibernateTemplate().find("from User");
    }
}
