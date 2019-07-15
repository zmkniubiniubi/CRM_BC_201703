package top.zhangmingke.crm.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import top.zhangmingke.crm.domain.Customer;
import top.zhangmingke.crm.domain.LinkMan;
import top.zhangmingke.crm.domain.PageBean;
import top.zhangmingke.crm.service.CustomerService;
import top.zhangmingke.crm.service.LinkManService;

import java.util.List;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
    private  LinkMan linkMan = new LinkMan();
    @Override
    public LinkMan getModel() {
        return linkMan;
    }

    //注入service
    private LinkManService linkManService;

    public void setLinkManService(LinkManService linkManService) {
        this.linkManService = linkManService;
    }
    // 分页参数:
    private Integer currPage = 1;
    private Integer pageSize = 3;

    //注入CustomerService

    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void setCurrPage(Integer currPage) {
        if(currPage == null){
            currPage = 1;
        }
        this.currPage = currPage;
    }

    public void setPageSize(Integer pageSize) {
        if(pageSize == null){
            pageSize = 3;
        }
        this.pageSize = pageSize;
    }


    /**
     * 查询联系人列表的Action
     * @return
     */
    public String findAll(){
        // 创建离线条件查询:
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
        // 设置条件
        if(linkMan.getLkm_name()!=null){
            // 设置按名称查询的条件:模糊查询
            detachedCriteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
        }
        // 设置性别条件:
        if(linkMan.getLkm_gender() != null && !"".equals(linkMan.getLkm_gender())){
            // 设置按性别查询的条件:
            detachedCriteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
        }
        // 调用业务层
        PageBean<LinkMan> pageBean = linkManService.findAll(detachedCriteria,currPage,pageSize);

        //测试,为什么插入数据之后,查询到的只有插入的数据?
        List<LinkMan> list = pageBean.getList();
        System.out.println(list.size());

        ActionContext.getContext().getValueStack().push(pageBean);
//        System.out.println("程序执行到这了!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return "findAll";
    }
    /**
     * 跳转到添加页面的方法saveUI
     */
    public String saveUi(){
        // 查询所有客户:使用同步查询
        List<Customer> list = customerService.findAll();
        // 将list集合保存值栈中:
        ActionContext.getContext().getValueStack().set("list", list);
//        System.out.println("程序执行到这了!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return "saveUI";
    }

    public String save(){
        linkManService.save(linkMan);
        return "saveSuccess";
    }
//调转到编辑页面的方法
    public String edit(){
        // 查询某个联系人，查询所有客户。
        // 查询所有客户:
        List<Customer> list = customerService.findAll();
        // 根据id查询联系人:
        linkMan = linkManService.findById(linkMan.getLkm_id());
        // 将list和linkMan的对象带到页面上：
        ActionContext.getContext().getValueStack().set("list", list);
        // 将对象的值存入到值栈:
        ActionContext.getContext().getValueStack().push(linkMan);
        return "editSuccess";
    }
    /**
     * 修改联系人的方法:update
     */
    public String update(){
        // 调用业务层:
        linkManService.update(linkMan);
        return "updateSuccess";
    }
    public String delete(){
        //先查询在修改
        linkMan = linkManService.findById(linkMan.getLkm_id());
        //删除操作
        linkManService.delete(linkMan);

        //跳转页面
        return "deleteSuccess";

    }
}
