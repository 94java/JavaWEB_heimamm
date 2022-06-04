package top.hellocode.domain.system;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月20日 22:49
 */
public class Dept {
    private String id;
    private String deptName;	// 部门名称
    private String parentId;	// 所属部门id
    private Integer state;		// 状态

    private Dept parent;

    public Dept getParent() {
        return parent;
    }

    public void setParent(Dept parent) {
        this.parent = parent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
