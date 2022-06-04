package top.hellocode.web.controller.store;

import com.github.pagehelper.PageInfo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import top.hellocode.domain.store.Catalog;
import top.hellocode.domain.store.Company;
import top.hellocode.domain.store.Question;
import top.hellocode.utils.BeanUtil;
import top.hellocode.web.controller.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月28日 11:06
 */

// uri:/store/question?operation=list

@WebServlet("/store/question")
public class QuestionServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");   // 操作标志
        if("list".equals(operation)){   // 进入列表页
            this.list(request,response);
        }else if("toAdd".equals(operation)){
            this.toAdd(request,response);
        }else if("save".equals(operation)){
            try {
                this.save(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("toEdit".equals(operation)){
            this.toEdit(request,response);
        }else if("edit".equals(operation)){
            try {
                this.edit(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("delete".equals(operation)){
            this.delete(request,response);
        }else if("toTestUpload".equals(operation)){
            this.toTestUpload(request,response);
        }else if("testUpload".equals(operation)){
            try {
                this.testUpload(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("downloadReport".equals(operation)){
            this.downloadReport(request,response);
        }
    }


    private void toTestUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/question/testFileUpload.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void testUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. 确认该操作是否支持文件上传操作,enctype="multipart/form-data"
        if(ServletFileUpload.isMultipartContent(request)){
            // 2. 创建磁盘工厂对象
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 3. Servlet文件上传核心对象
            ServletFileUpload fileUpload = new ServletFileUpload(factory);
            // 4. 从request中读取数据
            List<FileItem> fileItems = fileUpload.parseRequest(request);
            for(FileItem item : fileItems){
                // 判断当前表单是否是文件表单
                if(!item.isFormField()){
                    item.write(new File(this.getServletContext().getRealPath("upload"),item.getName()));
                }
            }
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取数据
//        QuestionService questionService = new QuestionServiceImpl();
        int page = 1;
        int size = 5;
        if(StringUtils.isNotBlank(request.getParameter("page"))){
            page = Integer.parseInt(request.getParameter("page"));
        }
        if(StringUtils.isNotBlank(request.getParameter("size"))){
            page = Integer.parseInt(request.getParameter("size"));
        }

        PageInfo all = questionService.findAll(page, size);
        // 将数据保存到指定的位置
        request.setAttribute("page",all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/question/list.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 加载数据
        List<Company> companyList = companyService.findAll();
        List<Catalog> catalogList = catalogService.findAll();
        request.setAttribute("companyList",companyList);
        request.setAttribute("catalogList",catalogList);

        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/question/add.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. 确认该操作是否支持文件上传操作,enctype="multipart/form-data"
        if(ServletFileUpload.isMultipartContent(request)){
            // 2. 创建磁盘工厂对象
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 3. Servlet文件上传核心对象
            ServletFileUpload fileUpload = new ServletFileUpload(factory);
            // 4. 从request中读取数据
            List<FileItem> fileItems = fileUpload.parseRequest(request);

            // 创建标记位，标记是否上传图片
            boolean flag = false;
            for(FileItem item : fileItems){
                if(StringUtils.isNotBlank(item.getName())){     // 上传了文件
                    flag = true;
                    break;
                }
            }

            // 处理普通数据
            Question question = BeanUtil.fillBean(fileItems,Question.class);
            // 调用业务层接口
            String picture = questionService.save(question, flag);

            // 处理文件数据
            for(FileItem item : fileItems){
                // 判断当前表单是否是文件表单
                if(!item.isFormField()){
                    item.write(new File(this.getServletContext().getRealPath("upload"),picture));
                }
            }
        }

        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/store/question?operation=list");
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 加载数据
        List<Company> companyList = companyService.findAll();
        List<Catalog> catalogList = catalogService.findAll();
        request.setAttribute("companyList",companyList);
        request.setAttribute("catalogList",catalogList);

        // 拿到要编辑的数据
        String id = request.getParameter("id");

        Question question = questionService.findById(id);
        request.setAttribute("question",question);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/question/update.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. 确认该操作是否支持文件上传操作,enctype="multipart/form-data"
        if(ServletFileUpload.isMultipartContent(request)){
            // 2. 创建磁盘工厂对象
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 3. Servlet文件上传核心对象
            ServletFileUpload fileUpload = new ServletFileUpload(factory);
            // 4. 从request中读取数据
            List<FileItem> fileItems = fileUpload.parseRequest(request);

            // 创建标记位，标记是否上传图片
            boolean flag = false;
            for(FileItem item : fileItems){
                if(StringUtils.isNotBlank(item.getName())){     // 上传了文件
                    flag = true;
                    break;
                }
            }

            // 处理普通数据
            Question question = BeanUtil.fillBean(fileItems,Question.class);
            // 调用业务层接口
            questionService.update(question, flag);

            // 处理文件数据
            for(FileItem item : fileItems){
                // 判断当前表单是否是文件表单
                if(!item.isFormField()){
                    item.write(new File(this.getServletContext().getRealPath("upload"),question.getId()));
                }
            }
        }

        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/store/question?operation=list");
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Question question = BeanUtil.fillBean(request,Question.class);
        // 调用业务层接口
//        QuestionService questionService = new QuestionServiceImpl();
        questionService.delete(question);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/store/question?operation=list");
    }

    private void downloadReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 返回的数据类型为文件xlsx类型
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        // 修改文件名
        response.addHeader("Content-Disposition","attachment;fileName=questionReport.xlsx");
        // 生成报告的文件，传递到前端页面
        ByteArrayOutputStream os = questionService.getReport();
        // 获取产生响应的流对象
        ServletOutputStream sos = response.getOutputStream();
        // 将数据从原始的字节流对象中提取出来，写入到servlet对应的输出流中
        os.writeTo(sos);
        // 将输出流刷新
        sos.flush();
        os.close();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}