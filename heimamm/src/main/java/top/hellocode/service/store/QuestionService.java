package top.hellocode.service.store;

import com.github.pagehelper.PageInfo;
import top.hellocode.domain.store.Question;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月28日 11:04
 */
public interface QuestionService {
    /**
     * @Description:添加
     * @param question:
     *        flag:是否有上传文件的操作
     * @return:保存的图片名称
     */
    String save(Question question, boolean flag);
    /**
     * @Description:删除
     * @param question:
     * @return: int
     */
    void delete(Question question);
    /**
     * @Description:修改
     * @param question:
     * @return: int
     */
    void update(Question question,boolean flag);
    /**
     * @Description:查询单个
     * @param id:  查询的条件
     * @return: 单个对象
     */
    Question findById(String id);
    /**
     * @Description:查询全部数据
     * 无参方法
     * @return: 全部数据的列表对象
     */
    List<Question> findAll();
    /**
     * @Description:分页查询数据
     * @param page: 页码
     * @param size:  每页显示的数据总量
     * @return: com.github.pagehelper.PageInfo
     */
    PageInfo findAll(int page, int size);

    ByteArrayOutputStream getReport() throws IOException;
}
