package top.hellocode.domain.front;

import java.util.Date;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年06月04日 20:57
 */
public class ExamPaper {
    private String id;
    private String memberId;
    private Date applyTime;
    private String state;       // 1-可用   0-不可用
    private String score;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
