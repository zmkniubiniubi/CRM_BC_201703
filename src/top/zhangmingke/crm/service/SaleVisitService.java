package top.zhangmingke.crm.service;

import org.hibernate.criterion.DetachedCriteria;
import top.zhangmingke.crm.dao.SaleVisitDao;
import top.zhangmingke.crm.domain.PageBean;
import top.zhangmingke.crm.domain.SaleVisit;

public interface SaleVisitService {


    PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

    void save(SaleVisit saleVisit);
}
