package cn.book.bus.controller;


import cn.book.bus.domain.Fiction;
import cn.book.bus.domain.User;
import cn.book.bus.dto.FictionDto;
import cn.book.bus.service.IFictionService;
import cn.book.bus.service.IUserService;
import cn.book.bus.utils.ClassUtil;
import cn.book.bus.utils.Result;
import cn.book.bus.vo.FictionVo;
import cn.book.bus.vo.LayuiPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 追风
 * @since 2019-12-16
 */
@Controller
public class UserController {

    private final IUserService iUserService;

    @Autowired
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    /**
     * 用户登录
     */
    @RequestMapping(value = "login")
    public String Login(){

        return "login";
    }

    /**
     * 用户登录验证
     */
    @ResponseBody
    @RequestMapping(value = "loginV")
    public Result loginV(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password){
        boolean b = iUserService.verificationUser(username, password);
        if (b){
            HttpSession session = request.getSession();//创建session
            QueryWrapper<User> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("username",username);
            User user = iUserService.getOne(queryWrapper);
            session.setAttribute("id",user.getId());
            session.setAttribute("username",username);
            session.setAttribute("password",password);
            return new Result(200,"登录成功");
        }
        return new Result(-1,"账号或密码不正确");
    }


    /**
     * 用户注册
     */
    @RequestMapping(value = "signin")
    public String SignIn(){

        return "signin";
    }
    /**
     * 用户注册验证
     */
    @ResponseBody
    @RequestMapping(value = "signinV")
    public Result signinV(@RequestParam("username") String username, @RequestParam("password") String password){
        boolean b = iUserService.verificationUser(username);
        if (b){
            User user=new User();
            user.setUsername(username);
            user.setPassword(password);
            iUserService.save(user);
            return new Result(200,"注册成功");
        }
        return  new Result(-1,"用户名已存在");
    }
    /**
     * 用户注销
     */
    @RequestMapping(value = "logout")
    public ModelAndView Logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("id");
        session.removeAttribute("username");
        session.removeAttribute("password");
        return new ModelAndView("redirect:index");
    }
}

