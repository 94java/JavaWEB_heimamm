package top.hellocode.web.controller.front;

import top.hellocode.domain.front.Member;
import top.hellocode.web.controller.BaseServlet;
import top.hellocode.web.controller.Code;
import top.hellocode.web.controller.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年06月01日 11:07
 */

@WebServlet("/member/*")
public class MemberServlet extends BaseServlet {

    public Result login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member member = getData(request,Member.class);
        // 调用逻辑层API
        member = memberService.login(member.getEmail(), member.getPassword());

        if(member != null){
            // 查询到了结果
            return new Result("登录成功！", member);
        }else{
            // 登录失败
            return new Result("用户名或密码错误，请重试",false, null, Code.LOGIN_FAIL);
        }
    }

    public Result logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member member = getData(request,Member.class);
        // 调用逻辑层API
        boolean flag = memberService.logout(member.getId());

        if(flag){
            // 成功
            return new Result("退出成功！", flag);
        }else{
            // 登录失败
            return new Result("",false, flag, Code.LOGOUT_FAIL);
        }
    }

    public Result register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member member = getData(request,Member.class);
        // 调用逻辑层API
        boolean flag = memberService.register(member);
        // 返回的数据封装成一个对象
        return new Result("注册成功！", null);
    }

    public Result checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member member = getData(request,Member.class);
        // 根据获取到的id去redis查找，判断是否存在
        String nickName = memberService.getLoginInfo(member.getId());
        // 返回的数据封装成一个对象
        return new Result("", nickName);
    }

}
