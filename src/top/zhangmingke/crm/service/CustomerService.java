package top.zhangmingke.crm.service;

import org.hibernate.criterion.DetachedCriteria;
import top.zhangmingke.crm.domain.Customer;
import top.zhangmingke.crm.domain.PageBean;

import java.util.List;

/*客户管理Service的接口
* */
public interface CustomerService {
    void save(Customer customer);

    PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage,Integer pageSize);

    Customer findById(Long cust_id);

    void delete(Customer customer);

    void update(Customer customer);

    List<Customer> findAll();
}
