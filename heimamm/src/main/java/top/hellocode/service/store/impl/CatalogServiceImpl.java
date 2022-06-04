package top.hellocode.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import top.hellocode.dao.store.CatalogDao;
import top.hellocode.domain.store.Catalog;
import top.hellocode.factory.MapperFactory;
import top.hellocode.service.store.CatalogService;
import top.hellocode.utils.TransactionUtil;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月27日 21:16
 */

public class CatalogServiceImpl implements CatalogService {

    @Override
    public void save(Catalog catalog) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            CatalogDao catalogDao = MapperFactory.getMapper(sqlSession,CatalogDao.class);

            // id并不在表现层接收，使用uuid自动生成
            String id = UUID.randomUUID().toString();
            catalog.setId(id);

            // 添加创建的时间
            catalog.setCreateTime(new Date());


            // 调用Dao层操作
            catalogDao.save(catalog);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Catalog catalog) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            CatalogDao catalogDao = MapperFactory.getMapper(sqlSession,CatalogDao.class);

            // 调用Dao层操作
            catalogDao.delete(catalog);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Catalog catalog) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            CatalogDao catalogDao = MapperFactory.getMapper(sqlSession,CatalogDao.class);

            // 调用Dao层操作
            catalogDao.update(catalog);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public Catalog findById(String id) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            CatalogDao catalogDao = MapperFactory.getMapper(sqlSession,CatalogDao.class);

            // 调用Dao层操作
            return catalogDao.findById(id);
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Catalog> findAll() {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            CatalogDao catalogDao = MapperFactory.getMapper(sqlSession,CatalogDao.class);

            // 调用Dao层操作
            return catalogDao.findAll();
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public PageInfo findAll(int page, int size) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            CatalogDao catalogDao = MapperFactory.getMapper(sqlSession,CatalogDao.class);

            // 调用Dao层操作
            PageHelper.startPage(page, size);
            List<Catalog> all = catalogDao.findAll();
            PageInfo pageInfo = new PageInfo<>(all);
            return pageInfo;
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}