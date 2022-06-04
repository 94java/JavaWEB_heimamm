package top.hellocode.service.system;

import com.github.pagehelper.PageInfo;
import top.hellocode.domain.system.Module;

import java.util.List;
import java.util.Map;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月29日 19:41
 */
public interface ModuleService {
    /**
     * @Description:添加
     * @param module:
     * @return:
     */
    void save(Module module);
    /**
     * @Description:删除
     * @param module:
     * @return: int
     */
    void delete(Module module);
    /**
     * @Description:修改
     * @param module:
     * @return: int
     */
    void update(Module module);
    /**
     * @Description:查询单个
     * @param id:  查询的条件
     * @return: 单个对象
     */
    Module findById(String id);
    /**
     * @Description:查询全部数据
     * 无参方法
     * @return: 全部数据的列表对象
     */
    List<Module> findAll();
    /**
     * @Description:分页查询数据
     * @param page: 页码
     * @param size:  每页显示的数据总量
     * @return: com.github.pagehelper.PageInfo
     */
    PageInfo findAll(int page, int size);

    /**
     * @Description:根据角色id获取所有模块关联数据
     * @param roleId:  角色id
     * @return: void
     */
    List<Map> findAuthorDataByRoleId(String roleId);
}