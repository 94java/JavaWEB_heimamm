package top.hellocode.domain.system;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月29日 19:39
 */
public class Module{
    private String id;
    private String parentId;		// 所属模块id
    private String name;		// 名称
    private Long ctype;		// 类型（1-系统菜单，2-二级菜单，3-...，4-....）
    private Long state;		// 状态(1-可用，2-不可用)
    private String curl;		// 请求url（用于权限校验）
    private String remark;		// 描述

    private Module module;		// 自连接关系

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCtype() {
        return ctype;
    }

    public void setCtype(Long ctype) {
        this.ctype = ctype;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public String getCurl() {
        return curl;
    }

    public void setCurl(String curl) {
        this.curl = curl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}
