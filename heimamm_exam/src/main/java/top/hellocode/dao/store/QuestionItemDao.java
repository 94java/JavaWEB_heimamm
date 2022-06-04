package top.hellocode.dao.store;

import top.hellocode.domain.store.QuestionItem;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年06月02日 15:09
 */
public interface QuestionItemDao {
    List<QuestionItem> findByQuestionId(String questionId);
}
