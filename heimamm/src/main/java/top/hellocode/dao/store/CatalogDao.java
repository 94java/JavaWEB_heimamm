package top.hellocode.dao.store;

import top.hellocode.domain.store.Catalog;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月27日 21:13
 */
public interface CatalogDao {
    int save(Catalog catalog);		// 保存
    int delete(Catalog catalog);    // 删除
    int update(Catalog catalog);    // 修改
    Catalog findById(String id);    // 通过id查询
    List<Catalog> findAll();        // 查询全部
}
