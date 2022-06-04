package top.hellocode.dao.system;

import org.apache.ibatis.annotations.Param;
import top.hellocode.domain.system.Role;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月29日 19:33
 */
public interface RoleDao {
    int save(Role role);		// 保存
    int delete(Role role);    // 删除
    int update(Role role);    // 修改
    Role findById(String id);    // 通过id查询
    List<Role> findAll();        // 查询全部

    void deleteRoleModule(String roleId);

    void saveRoleModule(@Param("roleId") String roleId,@Param("moduleId") String moduleId);

    List<Role> findAllRoleByUserId(String userId);
}
