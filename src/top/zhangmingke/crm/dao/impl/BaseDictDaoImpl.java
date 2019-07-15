package top.zhangmingke.crm.dao.impl;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import top.zhangmingke.crm.dao.BaseDictDao;
import top.zhangmingke.crm.domain.BaseDict;

import java.util.List;

/*字典Dao的实现类
* */
public class BaseDictDaoImpl extends HibernateDaoSupport implements BaseDictDao {
    //根据类型编码查询数据
    @Override
    public List<BaseDict> findByTypeCode(String dict_type_code) {
        System.out.println(dict_type_code);
        System.out.println("执行到对字典表的查询!");
        return (List<BaseDict>) this.getHibernateTemplate().find("from BaseDict where dict_type_code = ?",dict_type_code);
    }
}

