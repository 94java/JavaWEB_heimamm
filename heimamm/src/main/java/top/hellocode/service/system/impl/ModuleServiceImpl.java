package top.hellocode.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import top.hellocode.dao.system.ModuleDao;
import top.hellocode.domain.system.Module;
import top.hellocode.factory.MapperFactory;
import top.hellocode.service.system.ModuleService;
import top.hellocode.utils.TransactionUtil;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月29日 19:42
 */
public class ModuleServiceImpl implements ModuleService {

    @Override
    public void save(Module module) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession, ModuleDao.class);

            // id并不在表现层接收，使用uuid自动生成
            String id = UUID.randomUUID().toString();
            module.setId(id);

            // 调用Dao层操作
            moduleDao.save(module);
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
    public void delete(Module module) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession,ModuleDao.class);

            // 调用Dao层操作
            moduleDao.delete(module);
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
    public void update(Module module) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession,ModuleDao.class);

            // 调用Dao层操作
            moduleDao.update(module);
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
    public Module findById(String id) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession,ModuleDao.class);

            // 调用Dao层操作
            return moduleDao.findById(id);
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
    public List<Module> findAll() {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession,ModuleDao.class);

            // 调用Dao层操作
            return moduleDao.findAll();
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
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession,ModuleDao.class);

            // 调用Dao层操作
            PageHelper.startPage(page, size);
            List<Module> all = moduleDao.findAll();
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

    @Override
    public List<Map> findAuthorDataByRoleId(String roleId) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession,ModuleDao.class);

            // 调用Dao层操作
            return moduleDao.findAuthorDataByRoleId(roleId);
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