package top.hellocode.web.controller;

import com.alibaba.fastjson.JSON;
import top.hellocode.service.front.ExamService;
import top.hellocode.service.front.MemberService;
import top.hellocode.service.front.impl.ExamServiceImpl;
import top.hellocode.service.front.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年06月01日 11:07
 */
public class BaseServlet extends HttpServlet {
    protected MemberService memberService;
    protected ExamService examService;

    @Override
    public void init() throws ServletException {
        memberService = new MemberServiceImpl();
        examService = new ExamServiceImpl();
    }

    protected  <T> T getData(HttpServletRequest request, Class<T> clazz) throws IOException {
        // 收集数据
        String json = JSON.parseObject(request.getInputStream(), String.class);
        // 组织成一个实体类（Member）
        return JSON.parseObject(json, clazz);
    }

    protected void returnData(HttpServletResponse response, Result result) throws IOException {
        // 返回结果
        response.setContentType("application/json;charset=utf-8");
        JSON.writeJSONString(response.getOutputStream(),result);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        int lastIndex = url.lastIndexOf('/');
        String methodName = url.substring(lastIndex + 1, url.length());

        // 获取到了方法名叫做url的方法，利用反射执行，传递参数即可
        Class clazz = this.getClass();
        try {
            Method method = clazz.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            Result ret = (Result) method.invoke(this, request, response);
            returnData(response,ret);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
