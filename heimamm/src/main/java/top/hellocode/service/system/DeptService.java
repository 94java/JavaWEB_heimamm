package top.hellocode.service.system;



import com.github.pagehelper.PageInfo;
import top.hellocode.domain.system.Dept;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月20日 22:53
 */

public interface DeptService {
    /**
     * @Description:添加
     * @param dept:
     * @return:
     */
    void save(Dept dept);
    /**
     * @Description:删除
     * @param dept:
     * @return: int
     */
    void delete(Dept dept);
    /**
     * @Description:修改
     * @param dept:
     * @return: int
     */
    void update(Dept dept);
    /**
     * @Description:查询单个
     * @param id:  查询的条件
     * @return: 单个对象
     */
    Dept findById(String id);
    /**
     * @Description:查询全部数据
     * 无参方法
     * @return: 全部数据的列表对象
     */
    List<Dept> findAll();
    /**
     * @Description:分页查询数据
     * @param page: 页码
     * @param size:  每页显示的数据总量
     * @return: com.github.pagehelper.PageInfo
     */
    PageInfo findAll(int page, int size);
}

