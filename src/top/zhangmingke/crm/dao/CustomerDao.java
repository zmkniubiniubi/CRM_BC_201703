package top.zhangmingke.crm.dao;

import org.hibernate.criterion.DetachedCriteria;
import top.zhangmingke.crm.domain.Customer;

import java.util.List;

/*
* 客户管理Dao接口*/
public interface CustomerDao {
    void save(Customer customer);

    Integer findCount(DetachedCriteria detachedCriteria);

    List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);

    Customer findById(Long cust_id);

    void delete(Customer customer);

    void update(Customer customer);

    List<Customer> findAll();
}
