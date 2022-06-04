package top.hellocode.dao.system;


import org.apache.ibatis.annotations.Param;
import top.hellocode.domain.system.User;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月20日 22:50
 */
public interface UserDao {
    int save(User user);		// 保存
    int delete(User user);    // 删除
    int update(User user);    // 修改
    User findById(String id);    // 通过id查询
    List<User> findAll();        // 查询全部

    void deleteRole(String userId);

    void updateRole(@Param("userId") String userId, @Param("roleId") String roleId);

    User findByEmailAndPwd(@Param("email") String email, @Param("password") String pwd);
}
