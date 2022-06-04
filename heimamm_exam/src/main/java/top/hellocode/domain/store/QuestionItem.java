package top.hellocode.domain.store;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年06月02日 15:04
 */
public class QuestionItem {
    private String id;
    private String questionId;		// 所属题目id
    private String content;			// 选项内容
    private String isRight;			// 是否是正确答案

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsRight() {
        return isRight;
    }

    public void setIsRight(String isRight) {
        this.isRight = isRight;
    }
}
