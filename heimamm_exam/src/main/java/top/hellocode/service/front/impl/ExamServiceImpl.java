package top.hellocode.service.front.impl;

import org.apache.ibatis.session.SqlSession;
import redis.clients.jedis.Jedis;
import top.hellocode.dao.front.ExamPaperDao;
import top.hellocode.dao.front.ExamQuestionDao;
import top.hellocode.dao.front.MemberDao;
import top.hellocode.dao.store.QuestionDao;
import top.hellocode.domain.front.ExamPaper;
import top.hellocode.domain.front.ExamQuestion;
import top.hellocode.domain.front.Member;
import top.hellocode.domain.store.Question;
import top.hellocode.factory.MapperFactory;
import top.hellocode.service.front.ExamService;
import top.hellocode.utils.JedisUtils;
import top.hellocode.utils.MD5Util;
import top.hellocode.utils.TransactionUtil;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年06月02日 14:49
 */
public class ExamServiceImpl implements ExamService {
    @Override
    public List<Question> getPaper() {
        SqlSession sqlSession = null;
        try {
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            QuestionDao questionDao = MapperFactory.getMapper(sqlSession, QuestionDao.class);
            List<Question> questionList = questionDao.findAll();

            return questionList;
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
    public boolean applyPaper(String memberId, List<ExamQuestion> examQuestionList) {
        SqlSession sqlSession = null;
        try {
            boolean flag = true;
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            ExamPaperDao examPaperDao = MapperFactory.getMapper(sqlSession, ExamPaperDao.class);
            ExamQuestionDao examQuestionDao = MapperFactory.getMapper(sqlSession, ExamQuestionDao.class);

            // 提交保存的试卷信息
            ExamPaper examPaper = new ExamPaper();
            String paperId = UUID.randomUUID().toString();
            examPaper.setId(paperId);
            examPaper.setApplyTime(new Date());
            examPaper.setMemberId(memberId);
            examPaper.setState("1");
            flag = flag && examPaperDao.save(examPaper) > 0;
            // 提交保存到试卷
            for(ExamQuestion eq : examQuestionList){
                eq.setId(UUID.randomUUID().toString());
                eq.setExamPaperId(paperId);

                flag = flag && examQuestionDao.save(eq) > 0;
            }

            TransactionUtil.commit(sqlSession);
            return flag;
        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);
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
