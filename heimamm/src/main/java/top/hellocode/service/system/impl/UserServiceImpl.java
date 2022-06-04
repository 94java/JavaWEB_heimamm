package top.hellocode.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import top.hellocode.dao.system.ModuleDao;
import top.hellocode.dao.system.UserDao;
import top.hellocode.domain.system.Module;
import top.hellocode.domain.system.User;
import top.hellocode.factory.MapperFactory;
import top.hellocode.service.system.UserService;
import top.hellocode.utils.MD5Util;
import top.hellocode.utils.TransactionUtil;

import java.util.List;
import java.util.UUID;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月20日 22:54
 */

public class UserServiceImpl implements UserService {

    @Override
    public void save(User user) {
        SqlSession sqlSession = null;
        try {
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);

            // id并不在表现层接收，使用uuid自动生成
            String id = UUID.randomUUID().toString();
            user.setId(id);
            // 密码加密
            user.setPassword(MD5Util.md5(user.getPassword()));

            // 调用Dao层操作
            userDao.save(user);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(User user) {
        SqlSession sqlSession = null;
        try {
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);

            // 调用Dao层操作
            userDao.delete(user);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(User user) {
        SqlSession sqlSession = null;
        try {
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);

            // 调用Dao层操作
            userDao.update(user);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User findById(String id) {
        SqlSession sqlSession = null;
        try {
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);

            // 调用Dao层操作
            return userDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> findAll() {
        SqlSession sqlSession = null;
        try {
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);

            // 调用Dao层操作
            return userDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public PageInfo findAll(int page, int size) {
        SqlSession sqlSession = null;
        try {
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);

            // 调用Dao层操作
            PageHelper.startPage(page, size);
            List<User> all = userDao.findAll();
            PageInfo pageInfo = new PageInfo<>(all);
            return pageInfo;
        } catch (Exception e) {
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateRole(String userId, String[] roleIds) {
        SqlSession sqlSession = null;
        try {
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);

            userDao.deleteRole(userId);
            for (String roleId : roleIds) {
                userDao.updateRole(userId, roleId);
            }
            // 提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User login(String email, String pwd) {
        SqlSession sqlSession = null;
        try {
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);

            // 调用Dao层操作
            // 密码加密
            pwd = MD5Util.md5(pwd);

            return userDao.findByEmailAndPwd(email, pwd);
        } catch (Exception e) {
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Module> findMoudleById(String id) {
        SqlSession sqlSession = null;
        try {
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            ModuleDao moduleDao =  MapperFactory.getMapper(sqlSession, ModuleDao.class);

            // 调用Dao层操作

            return moduleDao.findModuleByUserId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}