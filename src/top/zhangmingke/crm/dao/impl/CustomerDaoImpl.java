package top.zhangmingke.crm.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import top.zhangmingke.crm.dao.CustomerDao;
import top.zhangmingke.crm.domain.Customer;

import java.util.List;

/*客户管理Dao的实现类
* */
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {
    @Override

    public void save(Customer customer) {
        this.getHibernateTemplate().save(customer);
    }

    @Override
    //统计总的记录数
    public Integer findCount(DetachedCriteria detachedCriteria) {
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size()>0){
            return list.get(0).intValue();
        }
        return null;
    }

    @Override
    //分页查询客户
    public List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        detachedCriteria.setProjection(null);
        List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
/*        for (Customer customer:list
             ) {
            System.out.println(customer);

        }*/
        return list;

    }

    @Override
//    根据OID查询用户信息
    public Customer findById(Long cust_id) {
        return this.getHibernateTemplate().get(Customer.class,cust_id);
    }

    @Override
    //删除客户
    public void delete(Customer customer) {
        this.getHibernateTemplate().delete(customer);


    }

    @Override
    public void update(Customer customer) {
        this.getHibernateTemplate().update(customer);
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> list = (List<Customer>) this.getHibernateTemplate().find("from Customer ");
        return list;
    }
}
