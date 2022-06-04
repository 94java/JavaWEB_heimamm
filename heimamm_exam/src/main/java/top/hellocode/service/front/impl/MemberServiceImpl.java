package top.hellocode.service.front.impl;

import org.apache.ibatis.session.SqlSession;
import redis.clients.jedis.Jedis;
import top.hellocode.dao.front.MemberDao;
import top.hellocode.domain.front.Member;
import top.hellocode.factory.MapperFactory;
import top.hellocode.service.front.MemberService;
import top.hellocode.utils.JedisUtils;
import top.hellocode.utils.MD5Util;
import top.hellocode.utils.TransactionUtil;

import java.util.Date;
import java.util.UUID;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年06月01日 19:23
 */
public class MemberServiceImpl implements MemberService {
    @Override
    public boolean register(Member member) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            MemberDao memberDao = MapperFactory.getMapper(sqlSession,MemberDao.class);

            // id并不在表现层接收，使用uuid自动生成
            String id = UUID.randomUUID().toString();
            member.setId(id);
            member.setRegisterDate(new Date());
            member.setState("1");

            member.setPassword(MD5Util.md5(member.getPassword()));

            // 调用Dao层操作
            int row = memberDao.save(member);
            // 提交事务
            TransactionUtil.commit(sqlSession);
            return row > 0;
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
    public Member login(String email, String password) {
        SqlSession sqlSession = null;
        try {
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            MemberDao memberDao = MapperFactory.getMapper(sqlSession, MemberDao.class);
            password = MD5Util.md5(password);
            Member member = memberDao.findByEmailAndPwd(email, password);

            // 将登陆人的信息保存到redis中
            Jedis jedis = JedisUtils.getResource();
            // 使用登陆人的id作为key，设置3600秒的过期时间，value待定
            jedis.setex(member.getId(),3600,member.getNickName());
            jedis.close();

            return member;
        } catch (Exception e) {
            e.printStackTrace();
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
    public String getLoginInfo(String id) {
        // 使用给定的id去redis查找当前数据
        Jedis jedis = JedisUtils.getResource();
        // 使用登陆人的id作为key，设置3600秒的过期时间，value待定
        String nickName = jedis.get(id);
        jedis.close();
        return nickName;
    }

    @Override
    public boolean logout(String id) {
        // 使用给定的id去redis查找当前数据
        Jedis jedis = JedisUtils.getResource();
        Long row = jedis.del(id);
        jedis.close();
        return row > 0;
    }
}
