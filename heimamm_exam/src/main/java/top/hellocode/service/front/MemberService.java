package top.hellocode.service.front;

import top.hellocode.domain.front.Member;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年06月01日 19:23
 */
public interface MemberService {
    /**
     * @Description:注册新用户
     * @param member:  用户对象
     * @return: boolean
     */
    boolean register(Member member);

    /**
     * @Description:根据email和密码登录
     * @param email:
     * @param password:
     * @return: top.hellocode.domain.front.Member
     */
    Member login(String email, String password);

    /**
     * @Description:根据登陆人id获取对应的昵称，从redis获取
     * @param id:
     * @return: java.lang.String
     */
    String getLoginInfo(String id);

    boolean logout(String id);
}
