package top.zhangmingke.crm.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import top.zhangmingke.crm.domain.BaseDict;
import top.zhangmingke.crm.service.BaseDictService;

import java.io.IOException;
import java.util.List;

public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict> {
    private BaseDict baseDict = new BaseDict();

    @Override
    public BaseDict getModel() {
        return baseDict;
    }

    //注入service
    private BaseDictService baseDictService;

    public void setBaseDictService(BaseDictService baseDictService) {
        this.baseDictService = baseDictService;
    }
    /*根据类型名称查询字典的方法findByTypeCode*/

    public String findByTypeCode() throws IOException {
        System.out.println("findByTypeCode方法执行了");
        //调用业务层进行查询
       List<BaseDict> list = baseDictService.findByTypeCode(baseDict.getDict_type_code());
        //将list转成json   常用的工具Jsonlib和fastjson
        /*jsonlib常用对象
        * JSONArray:将数组和list集合转成json
        * JSONObject:将对象和map集合转成json
        * JSONConfig:转json的配置对象,也就是你不想把那些属性转换成json,使用该对象去掉*/
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"dict_sort","dict_enable","dict_memo"});
        JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
        System.out.println(jsonArray.toString());
        //将json打印到页面
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return NONE;
    }

}
