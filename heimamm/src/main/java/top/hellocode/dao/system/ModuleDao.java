package top.hellocode.dao.system;


import top.hellocode.domain.system.Module;

import java.util.List;
import java.util.Map;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月29日 19:40
 */
public interface ModuleDao {
    int save(Module module);		// 保存
    int delete(Module module);    // 删除
    int update(Module module);    // 修改
    Module findById(String id);    // 通过id查询
    List<Module> findAll();        // 查询全部

    List<Map> findAuthorDataByRoleId(String roleId);

    List<Module> findModuleByUserId(String id);
}
