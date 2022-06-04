package top.hellocode.service.store;

import com.github.pagehelper.PageInfo;
import top.hellocode.domain.store.Course;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月16日 20:16
 */
public interface CourseService {
    /**
     * @Description:添加
     * @param course:
     * @return:
     */
    void save(Course course);
    /**
     * @Description:删除
     * @param course:
     * @return: int
     */
    void delete(Course course);
    /**
     * @Description:修改
     * @param course:
     * @return: int
     */
    void update(Course course);
    /**
     * @Description:查询单个
     * @param id:  查询的条件
     * @return: 单个对象
     */
    Course findById(String id);
    /**
     * @Description:查询全部数据
     * 无参方法
     * @return: 全部数据的列表对象
     */
    List<Course> findAll();
    /**
     * @Description:分页查询数据
     * @param page: 页码
     * @param size:  每页显示的数据总量
     * @return: com.github.pagehelper.PageInfo
     */
    PageInfo findAll(int page, int size);
}
