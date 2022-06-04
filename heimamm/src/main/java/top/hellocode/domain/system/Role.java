package top.hellocode.domain.system;

import java.util.Date;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月29日 19:32
 */
public class Role{
    private String id;
    private String name;		// 名称
    private String remark;		// 描述
    private Date createTime;		// 创建时间

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
