package top.zhangmingke.crm.service.impl;

import top.zhangmingke.crm.dao.BaseDictDao;
import top.zhangmingke.crm.domain.BaseDict;
import top.zhangmingke.crm.service.BaseDictService;

import java.util.List;

public class BaseDictServiceImpl implements BaseDictService {
    private BaseDictDao baseDictDao;

    public void setBaseDictDao(BaseDictDao baseDictDao) {
        this.baseDictDao = baseDictDao;
    }

    @Override
    public List<BaseDict> findByTypeCode(String dict_type_code) {
        return baseDictDao.findByTypeCode(dict_type_code);
    }
}
