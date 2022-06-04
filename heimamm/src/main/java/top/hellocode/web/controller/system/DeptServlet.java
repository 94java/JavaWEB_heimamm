package top.hellocode.web.controller.system;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import top.hellocode.domain.system.Dept;
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


// uri:/system/dept?operation=list

@WebServlet("/system/dept")
public class DeptServlet extends BaseServlet {
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
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取数据
//        DeptService deptService = new DeptServiceImpl();
        int page = 1;
        int size = 5;
        if(StringUtils.isNotBlank(request.getParameter("page"))){
            page = Integer.parseInt(request.getParameter("page"));
        }
        if(StringUtils.isNotBlank(request.getParameter("size"))){
            page = Integer.parseInt(request.getParameter("size"));
        }

        PageInfo all = deptService.findAll(page, size);
        // 将数据保存到指定的位置
        request.setAttribute("page",all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/dept/list.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 加载数据
        List<Dept> all = deptService.findAll();
        request.setAttribute("deptList",all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/dept/add.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Dept dept = BeanUtil.fillBean(request,Dept.class,"yyyy-MM-dd");
        // 调用业务层接口
//        DeptService deptService = new DeptServiceImpl();
        deptService.save(dept);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/dept?operation=list");
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 拿到要编辑的数据
        String id = request.getParameter("id");

        // 加载数据
        List<Dept> all = deptService.findAll();
        request.setAttribute("deptList",all);
        // 调用业务层方法查询数据
        Dept dept = deptService.findById(id);
        request.setAttribute("dept",dept);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/dept/update.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Dept dept = BeanUtil.fillBean(request,Dept.class,"yyyy-MM-dd");
        // 调用业务层接口
//        DeptService deptService = new DeptServiceImpl();
        deptService.update(dept);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/dept?operation=list");
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Dept dept = BeanUtil.fillBean(request,Dept.class);
        // 调用业务层接口
//        DeptService deptService = new DeptServiceImpl();
        deptService.delete(dept);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/dept?operation=list");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}

