package top.hellocode.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年06月01日 9:47
 */

@WebFilter(value = "/*")
public class AuthorFilter implements Filter {

    private FilterConfig filterConfig;

    /**
     * 初始化方法，获取过滤器的配置对象
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        //1.定义和协议相关的请求和响应对象
        HttpServletRequest request ;
        HttpServletResponse response;
        HttpSession session;
        try{
            //2.把参数转换成协议相关的对象
            request = (HttpServletRequest)req;
            response = (HttpServletResponse)resp;
            session = request.getSession();

            // 获取本次操作
            String url = request.getRequestURI();
            // 提前放行一些文件
            // .css .js .png .jpg .index
            if(url.endsWith(".css")
                    || url.endsWith(".js")
                    || url.endsWith(".png")
                    || url.endsWith(".jpg")
                    || url.endsWith("index.jsp")
                    || url.endsWith("login.jsp")
                    || url.endsWith("unauthorized.jsp")
            ){
                chain.doFilter(request,response);
                return;
            }

            String queryString = request.getQueryString();

            if(queryString.endsWith("operation=login")
                    || queryString.endsWith("operation=home")
                    || queryString.endsWith("operation=logout")
            ){
                chain.doFilter(request,response);
                return;
            }

            // 当前获取到的uri: /system.dept
            url = url.substring(1);     // 去掉开头/
            // 当前获取到的查询参数：operation=list          operation=toEdit&id=100
            int index = queryString.indexOf('&');
            if (index != -1){
                queryString = queryString.substring(0, index);
            }
            url = url + "?" + queryString;

            // 获取当前登陆人允许的操作
            String authorStr = (String) session.getAttribute("authorStr");


            // 比对本次操作是否在当前登陆人允许的操作范围内
            // 如果允许就放行，否则跳转到非法访问页
            if(authorStr.contains(url)){
                //6.放行
                chain.doFilter(request,response);
                return;
            }else {
                // 跳转错误页面
                response.sendRedirect(request.getContextPath() + "/unauthorized.jsp");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        //可以做一些清理操作
    }
}
