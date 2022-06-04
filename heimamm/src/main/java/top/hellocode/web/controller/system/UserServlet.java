package top.hellocode.web.controller.system;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import top.hellocode.domain.system.Dept;
import top.hellocode.domain.system.Module;
import top.hellocode.domain.system.Role;
import top.hellocode.domain.system.User;
import top.hellocode.utils.BeanUtil;
import top.hellocode.web.controller.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月20日 22:56
 */


// uri:/system/user?operation=list

@WebServlet("/system/user")
public class UserServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");   // 操作标志
        if ("list".equals(operation)) {   // 进入列表页
            this.list(request, response);
        } else if ("toAdd".equals(operation)) {
            this.toAdd(request, response);
        } else if ("save".equals(operation)) {
            this.save(request, response);
        } else if ("toEdit".equals(operation)) {
            this.toEdit(request, response);
        } else if ("edit".equals(operation)) {
            this.edit(request, response);
        } else if ("delete".equals(operation)) {
            this.delete(request, response);
        } else if ("userRoleList".equals(operation)) {
            this.userRoleList(request, response);
        } else if ("updateRole".equals(operation)) {
            this.updateRole(request, response);
        } else if ("login".equals(operation)) {
            this.login(request, response);
        } else if ("logout".equals(operation)) {
            this.logout(request, response);
        }else if ("home".equals(operation)) {
            this.home(request, response);
        }
    }


    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取数据
//        UserService userService = new UserServiceImpl();
        int page = 1;
        int size = 5;
        if (StringUtils.isNotBlank(request.getParameter("page"))) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (StringUtils.isNotBlank(request.getParameter("size"))) {
            page = Integer.parseInt(request.getParameter("size"));
        }

        PageInfo all = userService.findAll(page, size);
        // 将数据保存到指定的位置
        request.setAttribute("page", all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/user/list.jsp").forward(request, response); //将页面放到web-info下（安全）
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 加载数据
        List<Dept> all = deptService.findAll();
        request.setAttribute("deptList", all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/user/add.jsp").forward(request, response); //将页面放到web-info下（安全）
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        User user = BeanUtil.fillBean(request, User.class, "yyyy-MM-dd");
        // 调用业务层接口
//        UserService userService = new UserServiceImpl();
        userService.save(user);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/user?operation=list");
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 拿到要编辑的数据
        String id = request.getParameter("id");

        // 加载数据
        List<Dept> all = deptService.findAll();
        request.setAttribute("deptList", all);
        // 调用业务层方法查询数据
        User user = userService.findById(id);
        request.setAttribute("user", user);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/user/update.jsp").forward(request, response); //将页面放到web-info下（安全）
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        User user = BeanUtil.fillBean(request, User.class, "yyyy-MM-dd");
        // 调用业务层接口
//        UserService userService = new UserServiceImpl();
        userService.update(user);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/user?operation=list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        User user = BeanUtil.fillBean(request, User.class);
        // 调用业务层接口
//        UserService userService = new UserServiceImpl();
        userService.delete(user);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/user?operation=list");
    }

    private void userRoleList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");

        // 调用业务层方法查询数据
        User user = userService.findById(userId);
        request.setAttribute("user", user);
        // 获取角色列表
        List<Role> all = roleService.findAllRoleByUserId(userId);
        request.setAttribute("roleList", all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/user/role.jsp").forward(request, response); //将页面放到web-info下（安全）
    }

    private void updateRole(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        String[] roleIds = request.getParameterValues("roleIds");
        userService.updateRole(userId, roleIds);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/user?operation=list");
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pwd = request.getParameter("password");
        User user = userService.login(email, pwd);
        if (user != null) {
            request.getSession().setAttribute("loginUser", user);
            // 如果登录成功，加载该用户对应的角色对应的所有模块
            List<Module> moduleList = userService.findMoudleById(user.getId());
            request.setAttribute("moduleList",moduleList);

            // 当前登录用户对应的可操作模块的所有url拼接成一个大的字符串
            StringBuilder sb = new StringBuilder();
            for(Module m :moduleList){
                sb.append(m.getCurl());
                sb.append(',');
            }
            request.getSession().setAttribute("authorStr",sb.toString());
            // 跳转页面
            request.getRequestDispatcher("/WEB-INF/pages/home/main.jsp").forward(request, response); //将页面放到web-info下（安全）
        } else {
            // 跳转页面
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("loginUser");
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

    private void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/home/home.jsp").forward(request, response); //将页面放到web-info下（安全）
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

