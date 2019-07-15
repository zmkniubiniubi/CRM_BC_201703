package top.zhangmingke.crm.service;

import top.zhangmingke.crm.domain.BaseDict;

import java.util.List;

public interface BaseDictService {
    List<BaseDict> findByTypeCode(String dict_type_code);
}
