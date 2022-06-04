package top.hellocode.dao.system;


import top.hellocode.domain.system.Dept;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月20日 22:50
 */
public interface DeptDao {
    int save(Dept dept);		// 保存
    int delete(Dept dept);    // 删除
    int update(Dept dept);    // 修改
    Dept findById(String id);    // 通过id查询
    List<Dept> findAll();        // 查询全部
}
