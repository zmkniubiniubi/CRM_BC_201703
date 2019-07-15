package top.zhangmingke.crm.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;
import top.zhangmingke.crm.domain.User;
import top.zhangmingke.crm.service.UserService;

import java.io.IOException;
import java.util.List;

public class UserAction extends ActionSupport implements ModelDriven<User> {
    private User user = new User();
    @Override
    public User getModel() {
        return user;
    }

    //注入service
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String regist(){
        userService.regist(user);
        return LOGIN;
    }

    public String login(){
        //调用业务层查询数据
        User existUser = userService.login(user);
        if (existUser==null){
            //登陆失败
            //添加错误信息
            this.addActionError("用户名或密码错误!");
            return LOGIN;

        }else {
            //登陆成功,把用户信息存到session中
            ServletActionContext.getRequest().getSession().setAttribute("existUser",existUser);
            return SUCCESS;


        }

    }
    //查询所有用户并把数据打印到页面上
    public String findAllUser() throws IOException {
        List<User> list = userService.findAll();
        JSONArray jsonArray = JSONArray.fromObject(list);
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return NONE;
    }


}
