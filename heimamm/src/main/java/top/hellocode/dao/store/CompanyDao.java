package top.hellocode.dao.store;

import top.hellocode.domain.store.Company;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月16日 19:25
 */
public interface CompanyDao {
    int save(Company company);		// 保存
    int delete(Company company);    // 删除
    int update(Company company);    // 修改
    Company findById(String id);    // 通过id查询
    List<Company> findAll();        // 查询全部
}
