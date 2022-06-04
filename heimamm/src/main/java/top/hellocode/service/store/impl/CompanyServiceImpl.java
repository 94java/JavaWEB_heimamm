package top.hellocode.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import top.hellocode.dao.store.CompanyDao;
import top.hellocode.domain.store.Company;
import top.hellocode.factory.MapperFactory;
import top.hellocode.service.store.CompanyService;
import top.hellocode.utils.TransactionUtil;

import java.util.List;
import java.util.UUID;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月16日 20:20
 */
public class CompanyServiceImpl implements CompanyService {

    @Override
    public void save(Company company) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            CompanyDao companyDao = MapperFactory.getMapper(sqlSession,CompanyDao.class);

            // id并不在表现层接收，使用uuid自动生成
            String id = UUID.randomUUID().toString();
            company.setId(id);

            // 调用Dao层操作
            companyDao.save(company);
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
    public void delete(Company company) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            CompanyDao companyDao = MapperFactory.getMapper(sqlSession,CompanyDao.class);

            // 调用Dao层操作
            companyDao.delete(company);
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
    public void update(Company company) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            CompanyDao companyDao = MapperFactory.getMapper(sqlSession,CompanyDao.class);

            // 调用Dao层操作
            companyDao.update(company);
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
    public Company findById(String id) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            CompanyDao companyDao = MapperFactory.getMapper(sqlSession,CompanyDao.class);

            // 调用Dao层操作
            return companyDao.findById(id);
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
    public List<Company> findAll() {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            CompanyDao companyDao = MapperFactory.getMapper(sqlSession,CompanyDao.class);

            // 调用Dao层操作
            return companyDao.findAll();
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
            CompanyDao companyDao = MapperFactory.getMapper(sqlSession,CompanyDao.class);

            // 调用Dao层操作
            PageHelper.startPage(page, size);
            List<Company> all = companyDao.findAll();
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
