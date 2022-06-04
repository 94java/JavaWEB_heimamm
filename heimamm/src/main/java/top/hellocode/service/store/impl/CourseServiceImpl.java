package top.hellocode.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import top.hellocode.dao.store.CourseDao;
import top.hellocode.domain.store.Course;
import top.hellocode.factory.MapperFactory;
import top.hellocode.service.store.CourseService;
import top.hellocode.utils.TransactionUtil;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月27日 20:29
 */
public class CourseServiceImpl implements CourseService {

    @Override
    public void save(Course course) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            CourseDao courseDao = MapperFactory.getMapper(sqlSession,CourseDao.class);

            // id并不在表现层接收，使用uuid自动生成
            String id = UUID.randomUUID().toString();
            course.setId(id);

            // 添加创建的时间
            course.setCreateTime(new Date());

            // 调用Dao层操作
            courseDao.save(course);
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
    public void delete(Course course) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            CourseDao courseDao = MapperFactory.getMapper(sqlSession,CourseDao.class);

            // 调用Dao层操作
            courseDao.delete(course);
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
    public void update(Course course) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            CourseDao courseDao = MapperFactory.getMapper(sqlSession,CourseDao.class);

            // 调用Dao层操作
            courseDao.update(course);
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
    public Course findById(String id) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            CourseDao courseDao = MapperFactory.getMapper(sqlSession,CourseDao.class);

            // 调用Dao层操作
            return courseDao.findById(id);
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
    public List<Course> findAll() {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            CourseDao courseDao = MapperFactory.getMapper(sqlSession,CourseDao.class);

            // 调用Dao层操作
            return courseDao.findAll();
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
            CourseDao courseDao = MapperFactory.getMapper(sqlSession,CourseDao.class);

            // 调用Dao层操作
            PageHelper.startPage(page, size);
            List<Course> all = courseDao.findAll();
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