package top.hellocode.web.controller.store;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import top.hellocode.domain.store.QuestionItem;
import top.hellocode.utils.BeanUtil;
import top.hellocode.web.controller.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月28日 20:53
 */

// uri:/store/questionItem?operation=list

@WebServlet("/store/questionItem")
public class QuestionItemServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");   // 操作标志
        if("list".equals(operation)){   // 进入列表页
            this.list(request,response);
        }else if("save".equals(operation)){
            this.save(request,response);
        }else if("toEdit".equals(operation)){
            this.toEdit(request,response);
        }else if("edit".equals(operation)){
            this.edit(request,response);
        }else if("delete".equals(operation)){
            this.delete(request,response);
        }else if("saveOrUpdate".equals(operation)){
            this.saveOrUpdate(request,response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId = request.getParameter("questionId");

        // 进入list页面时，添加对应的问题id，为添加操作使用
        request.setAttribute("questionId",questionId);

        PageInfo all = questionItemService.findAll(questionId, 1, 100);
        // 将数据保存到指定的位置
        request.setAttribute("page",all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/questionItem/list.jsp").forward(request,response); //将页面放到web-info下（安全）
    }


    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 获取数据并封装对象
        QuestionItem questionItem = BeanUtil.fillBean(request,QuestionItem.class,"yyyy-MM-dd");
        System.out.println(questionItem);
        // 调用业务层接口
        questionItemService.save(questionItem);
        // 跳转页面
        list(request,response);
    }

    private void saveOrUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 获取数据并封装对象
        QuestionItem questionItem = BeanUtil.fillBean(request,QuestionItem.class,"yyyy-MM-dd");
        // 如果页面传递了当前数据的id，为修改，否则为新增
        if(StringUtils.isNotBlank(questionItem.getId())){
            // 调用业务层接口
            questionItemService.update(questionItem);
        }else{
            questionItemService.save(questionItem);
        }

        // 跳转页面
        list(request,response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 获取数据并封装对象
        QuestionItem questionItem = BeanUtil.fillBean(request,QuestionItem.class,"yyyy-MM-dd");
        // 调用业务层接口
//        QuestionItemService questionItemService = new QuestionItemServiceImpl();
        questionItemService.update(questionItem);
        // 跳转页面
        list(request,response);
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 拿到要编辑的数据
        String id = request.getParameter("id");
        // 调用业务层方法查询数据
//        QuestionItemService questionItemService = new QuestionItemServiceImpl();
        QuestionItem questionItem = questionItemService.findById(id);
        request.setAttribute("questionItem",questionItem);
        // 跳转页面
        list(request,response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 获取数据并封装对象
        QuestionItem questionItem = BeanUtil.fillBean(request,QuestionItem.class);
        // 调用业务层接口
        questionItemService.delete(questionItem);
        // 跳转页面
        list(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}