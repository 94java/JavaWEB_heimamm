package top.hellocode.web.controller.front;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import top.hellocode.domain.front.ExamQuestion;
import top.hellocode.domain.front.Member;
import top.hellocode.domain.store.Question;
import top.hellocode.web.controller.BaseServlet;
import top.hellocode.web.controller.Code;
import top.hellocode.web.controller.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年06月02日 14:45
 */

@WebServlet("/exam/*")
public class ExamServlet extends BaseServlet {
    public Result getPaper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Question> questionList = examService.getPaper();
        // 返回的数据封装成一个对象
        return new Result("试卷生成成功！", questionList);
    }

    public Result applyPaper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 得到全部请求的json数据
        String json = JSONObject.parseObject(request.getInputStream(), String.class);
        // 2. 将json数据转换为json对象
        JSONObject jsonObject = JSONObject.parseObject(json);
        // 3. 获取当前提交试卷人的id
        String memberId = jsonObject.getObject("memberId", String.class);
        // 4. 获取当前提交的试卷信息
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        List<ExamQuestion> examQuestionList = jsonArray.toJavaList(ExamQuestion.class);

        boolean flag = examService.applyPaper(memberId,examQuestionList);
        // 返回的数据封装成一个对象
        return new Result("试卷提交成功！", flag);
    }
}