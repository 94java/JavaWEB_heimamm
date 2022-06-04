package top.hellocode.dao.store;

import top.hellocode.domain.store.Question;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年06月02日 14:52
 */
public interface QuestionDao {
    List<Question> findAll();
}
