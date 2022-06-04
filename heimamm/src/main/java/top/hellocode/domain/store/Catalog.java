package top.hellocode.domain.store;

import java.util.Date;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月27日 21:13
 */
public class Catalog {
    private String id;
    private String name;		// 名称
    private String remark;		// 描述
    private String state;		// 状态
    private Date createTime;	// 创建时间
    private String courseId;	// 学科id

    private Course course;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
