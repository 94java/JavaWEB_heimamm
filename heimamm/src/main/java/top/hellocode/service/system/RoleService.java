package top.hellocode.service.system;

import com.github.pagehelper.PageInfo;
import top.hellocode.domain.system.Role;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月29日 19:35
 */
public interface RoleService {
    /**
     * @Description:添加
     * @param role:
     * @return:
     */
    void save(Role role);
    /**
     * @Description:删除
     * @param role:
     * @return: int
     */
    void delete(Role role);
    /**
     * @Description:修改
     * @param role:
     * @return: int
     */
    void update(Role role);
    /**
     * @Description:查询单个
     * @param id:  查询的条件
     * @return: 单个对象
     */
    Role findById(String id);
    /**
     * @Description:查询全部数据
     * 无参方法
     * @return: 全部数据的列表对象
     */
    List<Role> findAll();
    /**
     * @Description:分页查询数据
     * @param page: 页码
     * @param size:  每页显示的数据总量
     * @return: com.github.pagehelper.PageInfo
     */
    PageInfo findAll(int page, int size);

    /**
     * @Description:建立角色与模块之间的关联
     * @param roleId: 角色id
     * @param moduleIds:  模块id（多个）
     * @return: void
     */
    void updateRoleModule(String roleId, String moduleIds);

    List<Role> findAllRoleByUserId(String userId);
}