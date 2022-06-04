package top.hellocode.dao.front;

import org.apache.ibatis.annotations.Param;
import top.hellocode.domain.front.Member;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年06月01日 11:24
 */
public interface MemberDao {
    int save(Member member);

    Member findByEmailAndPwd(@Param("email") String email, @Param("password") String password);
}
