package top.hellocode.web.controller.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import top.hellocode.domain.system.Role;
import top.hellocode.utils.BeanUtil;
import top.hellocode.web.controller.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月29日 19:37
 */

// uri:/system/role?operation=list

@WebServlet("/system/role")
public class RoleServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");   // 操作标志
        if("list".equals(operation)){   // 进入列表页
            this.list(request,response);
        }else if("toAdd".equals(operation)){
            this.toAdd(request,response);
        }else if("save".equals(operation)){
            this.save(request,response);
        }else if("toEdit".equals(operation)){
            this.toEdit(request,response);
        }else if("edit".equals(operation)){
            this.edit(request,response);
        }else if("delete".equals(operation)){
            this.delete(request,response);
        }else if("author".equals(operation)){
            this.author(request,response);
        }else if("updateRoleModule".equals(operation)){
            this.updateRoleModule(request,response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取数据
//        RoleService roleService = new RoleServiceImpl();
        int page = 1;
        int size = 5;
        if(StringUtils.isNotBlank(request.getParameter("page"))){
            page = Integer.parseInt(request.getParameter("page"));
        }
        if(StringUtils.isNotBlank(request.getParameter("size"))){
            page = Integer.parseInt(request.getParameter("size"));
        }

        PageInfo all = roleService.findAll(page, size);
        // 将数据保存到指定的位置
        request.setAttribute("page",all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/role/list.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 加载数据
        List<Role> all = roleService.findAll();
        request.setAttribute("roleList",all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/role/add.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Role role = BeanUtil.fillBean(request,Role.class,"yyyy-MM-dd");
        // 调用业务层接口
//        RoleService roleService = new RoleServiceImpl();
        roleService.save(role);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/role?operation=list");
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 拿到要编辑的数据
        String id = request.getParameter("id");

        // 加载数据
        List<Role> all = roleService.findAll();
        request.setAttribute("roleList",all);
        // 调用业务层方法查询数据
        Role role = roleService.findById(id);
        request.setAttribute("role",role);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/role/update.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Role role = BeanUtil.fillBean(request,Role.class,"yyyy-MM-dd");
        // 调用业务层接口
//        RoleService roleService = new RoleServiceImpl();
        roleService.update(role);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/role?operation=list");
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Role role = BeanUtil.fillBean(request,Role.class);
        // 调用业务层接口
//        RoleService roleService = new RoleServiceImpl();
        roleService.delete(role);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/role?operation=list");
    }

    private void author(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取要授权的角色id
        String roleId = request.getParameter("id");
        // 使用id查询对应的数据（角色对应的模块信息）
        Role role = roleService.findById(roleId);
        request.setAttribute("role",role);
        // 根据当前角色id获取所有的模块数据，并加载关系数据
        List<Map> map = moduleService.findAuthorDataByRoleId(roleId);
        // map转json
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(map);
        request.setAttribute("roleModuleJson",json);
        // TODO 数据未查询
        // 跳转到树页面中
        request.getRequestDispatcher("/WEB-INF/pages/system/role/author.jsp").forward(request,response);
    }

    private void updateRoleModule(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String roleId = request.getParameter("roleId");
        String moduleIds = request.getParameter("moduleIds");
        roleService.updateRoleModule(roleId, moduleIds);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/role?operation=list");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}

