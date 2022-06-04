package top.hellocode.service.system;



import com.github.pagehelper.PageInfo;
import top.hellocode.domain.system.Module;
import top.hellocode.domain.system.User;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月20日 22:53
 */

public interface UserService {
    /**
     * @Description:添加
     * @param user:
     * @return:
     */
    void save(User user);
    /**
     * @Description:删除
     * @param user:
     * @return: int
     */
    void delete(User user);
    /**
     * @Description:修改
     * @param user:
     * @return: int
     */
    void update(User user);
    /**
     * @Description:查询单个
     * @param id:  查询的条件
     * @return: 单个对象
     */
    User findById(String id);
    /**
     * @Description:查询全部数据
     * 无参方法
     * @return: 全部数据的列表对象
     */
    List<User> findAll();
    /**
     * @Description:分页查询数据
     * @param page: 页码
     * @param size:  每页显示的数据总量
     * @return: com.github.pagehelper.PageInfo
     */
    PageInfo findAll(int page, int size);

    void updateRole(String userId, String[] roleIds);

    User login(String email, String pwd);

    /**
     * @Description:根据用户id查询所有可以操作的菜单对象
     * @param id:  用户id
     * @return: java.util.List<top.hellocode.domain.system.Module>
     */
    List<Module> findMoudleById(String id);
}

