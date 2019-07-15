package top.zhangmingke.crm.dao;

import org.hibernate.criterion.DetachedCriteria;
import top.zhangmingke.crm.domain.SaleVisit;

import java.util.List;

/*客户拜访记录Dao
* */
public interface SaleVisitDao  {
    Integer findCount(DetachedCriteria detachedCriteria);

    List<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);

    void save(SaleVisit saleVisit);
}
