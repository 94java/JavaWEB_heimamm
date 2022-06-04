package top.hellocode.dao.store;

import top.hellocode.domain.store.Question;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月28日 11:02
 */
public interface QuestionDao {
    int save(Question question);		// 保存
    int delete(Question question);    // 删除
    int update(Question question);    // 修改
    Question findById(String id);    // 通过id查询
    List<Question> findAll();        // 查询全部
}
