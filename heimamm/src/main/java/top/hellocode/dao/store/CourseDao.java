package top.hellocode.dao.store;

import top.hellocode.domain.store.Course;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月27日 20:16
 */
public interface CourseDao {
    int save(Course course);		// 保存
    int delete(Course course);    // 删除
    int update(Course course);    // 修改
    Course findById(String id);    // 通过id查询
    List<Course> findAll();        // 查询全部
}
