package top.hellocode.domain.front;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年06月04日 20:58
 */
public class ExamQuestion {
    private String id;
    private String examPaperId;
    private String questionId;
    private String answer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExamPaperId() {
        return examPaperId;
    }

    public void setExamPaperId(String examPaperId) {
        this.examPaperId = examPaperId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
