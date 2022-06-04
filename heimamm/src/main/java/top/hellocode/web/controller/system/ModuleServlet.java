package top.hellocode.web.controller.system;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import top.hellocode.domain.system.Module;
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
 * @date 2022年05月29日 19:48
 */
// uri:/system/module?operation=list

@WebServlet("/system/module")
public class ModuleServlet extends BaseServlet {
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
//        ModuleService moduleService = new ModuleServiceImpl();
        int page = 1;
        int size = 5;
        if(StringUtils.isNotBlank(request.getParameter("page"))){
            page = Integer.parseInt(request.getParameter("page"));
        }
        if(StringUtils.isNotBlank(request.getParameter("size"))){
            page = Integer.parseInt(request.getParameter("size"));
        }

        PageInfo all = moduleService.findAll(page, size);
        // 将数据保存到指定的位置
        request.setAttribute("page",all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/module/list.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 加载数据
        List<Module> all = moduleService.findAll();
        request.setAttribute("moduleList",all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/module/add.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Module module = BeanUtil.fillBean(request,Module.class,"yyyy-MM-dd");
        // 调用业务层接口
//        ModuleService moduleService = new ModuleServiceImpl();
        moduleService.save(module);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/module?operation=list");
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 拿到要编辑的数据
        String id = request.getParameter("id");

        // 加载数据
        List<Module> all = moduleService.findAll();
        request.setAttribute("moduleList",all);
        // 调用业务层方法查询数据
        Module module = moduleService.findById(id);
        request.setAttribute("module",module);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/module/update.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Module module = BeanUtil.fillBean(request,Module.class,"yyyy-MM-dd");
        // 调用业务层接口
//        ModuleService moduleService = new ModuleServiceImpl();
        moduleService.update(module);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/module?operation=list");
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Module module = BeanUtil.fillBean(request,Module.class);
        // 调用业务层接口
//        ModuleService moduleService = new ModuleServiceImpl();
        moduleService.delete(module);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/module?operation=list");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}

