package life.entity.dataget;

import java.io.Serializable;
import java.util.Date;

import life.util.json.JsonMapper;

/**
 * 功能：股市数据封装对象
 * 
 * @author xusheng.liu
 * @since 1.0
 * @version 2016年6月16日
 */
public class IndustryTypeEntity implements Serializable {
	private static final long serialVersionUID = -1109351568507856347L;
	private Integer id;// 主键
	private String name;// 名称
	private Date cteateTime;
	
	public IndustryTypeEntity(String name) {
		this.name=name;
	}

	public IndustryTypeEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCteateTime() {
		return cteateTime;
	}

	public void setCteateTime(Date cteateTime) {
		this.cteateTime = cteateTime;
	}

	@Override
	public String toString() {
		return JsonMapper.toJson(this);
	}
	
}
