package top.zhangmingke.crm.dao;

import top.zhangmingke.crm.domain.BaseDict;

import java.util.List;

/*字典Dao接口
* */
public interface BaseDictDao {
    List<BaseDict> findByTypeCode(String dict_type_code);
}
