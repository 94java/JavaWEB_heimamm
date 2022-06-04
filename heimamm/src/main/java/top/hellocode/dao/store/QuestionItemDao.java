package top.hellocode.dao.store;

import top.hellocode.domain.store.QuestionItem;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月28日 20:47
 */
public interface QuestionItemDao {
    int save(QuestionItem questionItem);		// 保存
    int delete(QuestionItem questionItem);    // 删除
    int update(QuestionItem questionItem);    // 修改
    QuestionItem findById(String id);    // 通过id查询
    /**
     * @Description:根据题目id查询所有选项
     * @param questionId:  题目id
     * @return: java.util.List<top.hellocode.domain.store.QuestionItem>
     */
    List<QuestionItem> findAll(String questionId);        // 查询全部
}
