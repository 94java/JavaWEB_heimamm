package top.hellocode.service.store.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import top.hellocode.dao.store.QuestionItemDao;
import top.hellocode.domain.store.QuestionItem;
import top.hellocode.factory.MapperFactory;
import top.hellocode.service.store.QuestionItemService;
import top.hellocode.utils.TransactionUtil;

import java.util.List;
import java.util.UUID;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月28日 20:50
 */

public class QuestionItemServiceImpl implements QuestionItemService {

    @Override
    public void save(QuestionItem questionItem) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession,QuestionItemDao.class);

            // id并不在表现层接收，使用uuid自动生成
            String id = UUID.randomUUID().toString();
            questionItem.setId(id);

            // 调用Dao层操作
            questionItemDao.save(questionItem);
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
    public void delete(QuestionItem questionItem) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession,QuestionItemDao.class);

            // 调用Dao层操作
            questionItemDao.delete(questionItem);
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
    public void update(QuestionItem questionItem) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession,QuestionItemDao.class);

            // 调用Dao层操作
            questionItemDao.update(questionItem);
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
    public QuestionItem findById(String id) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession,QuestionItemDao.class);

            // 调用Dao层操作
            return questionItemDao.findById(id);
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
    public PageInfo findAll (String questionId , int page, int size) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession,QuestionItemDao.class);

            // 调用Dao层操作
            PageHelper.startPage(page, size);
            List<QuestionItem> all = questionItemDao.findAll(questionId);
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