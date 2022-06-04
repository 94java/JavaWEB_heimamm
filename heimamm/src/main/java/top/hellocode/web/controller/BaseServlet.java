package top.hellocode.web.controller;

import top.hellocode.service.store.*;
import top.hellocode.service.store.impl.*;
import top.hellocode.service.system.DeptService;
import top.hellocode.service.system.ModuleService;
import top.hellocode.service.system.RoleService;
import top.hellocode.service.system.UserService;
import top.hellocode.service.system.impl.DeptServiceImpl;
import top.hellocode.service.system.impl.ModuleServiceImpl;
import top.hellocode.service.system.impl.RoleServiceImpl;
import top.hellocode.service.system.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月19日 22:04
 */
public class BaseServlet extends HttpServlet {
    protected CompanyService companyService;
    protected DeptService deptService;
    protected UserService userService;
    protected CourseService courseService;
    protected CatalogService catalogService;
    protected QuestionService questionService;
    protected QuestionItemService questionItemService;
    protected RoleService roleService;
    protected ModuleService moduleService;

    @Override
    public void init() throws ServletException {
        companyService = new CompanyServiceImpl();
        deptService = new DeptServiceImpl();
        userService = new UserServiceImpl();
        courseService = new CourseServiceImpl();
        catalogService = new CatalogServiceImpl();
        questionService = new QuestionServiceImpl();
        questionItemService = new QuestionItemServiceImpl();
        roleService = new RoleServiceImpl();
        moduleService = new ModuleServiceImpl();
    }
}
