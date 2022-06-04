package top.hellocode.service.front;

import top.hellocode.domain.front.ExamQuestion;
import top.hellocode.domain.store.Question;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年06月02日 14:48
 */
public interface ExamService {

    List<Question> getPaper();

    boolean applyPaper(String memberId, List<ExamQuestion> examQuestionList);
}
