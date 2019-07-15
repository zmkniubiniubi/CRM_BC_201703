package top.zhangmingke.crm.service.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;
import top.zhangmingke.crm.dao.CustomerDao;
import top.zhangmingke.crm.domain.Customer;
import top.zhangmingke.crm.domain.PageBean;
import top.zhangmingke.crm.service.CustomerService;

import java.util.List;

/*客户管理service的实现类*/
@Transactional
public class CustomerServiceImpl implements CustomerService {
    //注入Dao
    private CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public void save(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    //业务层分页查询客户的方法
    public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage,Integer pageSize) {
        PageBean<Customer> pageBean = new PageBean<Customer>();
        //封装当前页数
        pageBean.setCurrPage(currPage);
        //封装每页显示记录数
        pageBean.setPageSize(pageSize);
        //封装总记录数:需要从dao中查询
        Integer totalCount = customerDao.findCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        //封装整页数
        Double tc = totalCount.doubleValue();
        Double num = Math.ceil(tc / pageSize);
        pageBean.setTotalPage(num.intValue());
        //封装每页显示数据的集合
        Integer begin = (currPage - 1) * pageSize;
        List<Customer> list = customerDao.findByPage(detachedCriteria,begin,pageSize);
        pageBean.setList(list);

        return pageBean;
    }
    @Override
    // 业务层根据ID查询客户的方法
    public Customer findById(Long cust_id) {
        return customerDao.findById(cust_id);
    }

    @Override
    // 业务层删除客户的方法
    public void delete(Customer customer) {
        customerDao.delete(customer);
    }

    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }
}
