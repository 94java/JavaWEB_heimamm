package top.hellocode.service.store;

import com.github.pagehelper.PageInfo;
import top.hellocode.domain.store.Company;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月16日 20:16
 */
public interface CompanyService {
    /**
     * @Description:添加
     * @param company:
     * @return:
     */
    void save(Company company);
    /**
     * @Description:删除
     * @param company:
     * @return: int
     */
    void delete(Company company);
    /**
     * @Description:修改
     * @param company:
     * @return: int
     */
    void update(Company company);
    /**
     * @Description:查询单个
     * @param id:  查询的条件
     * @return: 单个对象
     */
    Company findById(String id);
    /**
     * @Description:查询全部数据
     * 无参方法
     * @return: 全部数据的列表对象
     */
    List<Company> findAll();
    /**
     * @Description:分页查询数据
     * @param page: 页码
     * @param size:  每页显示的数据总量
     * @return: com.github.pagehelper.PageInfo
     */
    PageInfo findAll(int page, int size);
}
