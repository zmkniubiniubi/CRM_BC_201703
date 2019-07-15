package top.zhangmingke.crm.service.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;
import top.zhangmingke.crm.dao.SaleVisitDao;
import top.zhangmingke.crm.domain.PageBean;
import top.zhangmingke.crm.domain.SaleVisit;
import top.zhangmingke.crm.service.SaleVisitService;

import javax.annotation.Resource;
import java.util.List;
@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {
    @Resource(name = "saleVisitDao")
    private SaleVisitDao saleVisitDao;

    @Override
    // 业务层分页显示拜访记录的方法:
    public PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
        PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>();
        // 设置当前页数:
        pageBean.setCurrPage(currPage);
        // 设置每页显示记录数:
        pageBean.setPageSize(pageSize);
        // 设置总记录数:
        Integer totalCount = saleVisitDao.findCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        // 设置总页数：
        double tc = totalCount;
        Double num = Math.ceil(tc / pageSize);
        pageBean.setTotalPage(num.intValue());
        // 设置每页显示的数据的集合:
        Integer begin = (currPage - 1) * pageSize;
        List<SaleVisit> list = saleVisitDao.findByPage(detachedCriteria, begin, pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public void save(SaleVisit saleVisit) {
        saleVisitDao.save(saleVisit);
    }
}
