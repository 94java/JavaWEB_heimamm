package top.hellocode.service.store;

import com.github.pagehelper.PageInfo;
import top.hellocode.domain.store.Catalog;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月27日 21:15
 */

public interface CatalogService {
    /**
     * @Description:添加
     * @param catalog:
     * @return:
     */
    void save(Catalog catalog);
    /**
     * @Description:删除
     * @param catalog:
     * @return: int
     */
    void delete(Catalog catalog);
    /**
     * @Description:修改
     * @param catalog:
     * @return: int
     */
    void update(Catalog catalog);
    /**
     * @Description:查询单个
     * @param id:  查询的条件
     * @return: 单个对象
     */
    Catalog findById(String id);
    /**
     * @Description:查询全部数据
     * 无参方法
     * @return: 全部数据的列表对象
     */
    List<Catalog> findAll();
    /**
     * @Description:分页查询数据
     * @param page: 页码
     * @param size:  每页显示的数据总量
     * @return: com.github.pagehelper.PageInfo
     */
    PageInfo findAll(int page, int size);
}

