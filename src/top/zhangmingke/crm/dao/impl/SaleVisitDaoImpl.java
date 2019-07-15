package top.zhangmingke.crm.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import top.zhangmingke.crm.dao.SaleVisitDao;
import top.zhangmingke.crm.domain.Customer;
import top.zhangmingke.crm.domain.SaleVisit;

import java.util.List;

public class SaleVisitDaoImpl extends HibernateDaoSupport implements SaleVisitDao {
    @Override
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
    public List<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        detachedCriteria.setProjection(null);
        List<SaleVisit> list = (List<SaleVisit>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
/*        for (Customer customer:list
             ) {
            System.out.println(customer);

        }*/
        return list;

    }

    @Override
    public void save(SaleVisit saleVisit) {
        this.getHibernateTemplate().save(saleVisit);
    }
}
