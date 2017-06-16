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
public class SecuAnalyFianlEntity implements Serializable {
	private static final long serialVersionUID = -6620826981088614975L;
	private Integer id;// 主键
	private int code;// 代码
	private String name;// 名称
	private String typeName;// 名称
	private Integer typeId;
	private Date time;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeName() {
		return typeName;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@Override
	public String toString() {
		return JsonMapper.toJson(this);
	}
	
}
