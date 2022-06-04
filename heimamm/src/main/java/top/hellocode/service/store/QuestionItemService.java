package top.hellocode.service.store;

import com.github.pagehelper.PageInfo;
import top.hellocode.domain.store.QuestionItem;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月28日 20:49
 */
public interface QuestionItemService {
    /**
     * @Description:添加
     * @param questionItem:
     * @return:
     */
    void save(QuestionItem questionItem);
    /**
     * @Description:删除
     * @param questionItem:
     * @return: int
     */
    void delete(QuestionItem questionItem);
    /**
     * @Description:修改
     * @param questionItem:
     * @return: int
     */
    void update(QuestionItem questionItem);
    /**
     * @Description:查询单个
     * @param id:  查询的条件
     * @return: 单个对象
     */
    QuestionItem findById(String id);

    /**
     * @Description:分页查询数据
     * @param page: 页码
     * @param size:  每页显示的数据总量
     * @return: com.github.pagehelper.PageInfo
     */
    PageInfo findAll(String questionId, int page, int size);
}
