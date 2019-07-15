package top.zhangmingke.crm.dao;

import org.hibernate.criterion.DetachedCriteria;
import top.zhangmingke.crm.domain.LinkMan;

import java.util.List;

public interface LinkManDao {
    Integer findCount(DetachedCriteria detachedCriteria);

    List<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);

    void save(LinkMan linkMan);


    LinkMan findById(Long lkm_id);

    void update(LinkMan linkMan);

    void delete(LinkMan linkMan);
}
