package life.entity;

import java.io.Serializable;
import java.util.Date;

import life.util.json.JsonMapper;

/**
 * 功能：密码对象
 * 
 * @author xusheng.liu
 * @since 1.0
 * @version 2016年6月16日
 */
public class SecretEntity implements Serializable {
	private static final long serialVersionUID = 2412502706063595893L;
	private Integer id;// 主键
	private Integer count;// 主键
	private String content;// 名称
	private String md5Content;// 名称
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMd5Content() {
		return md5Content;
	}

	public void setMd5Content(String md5Content) {
		this.md5Content = md5Content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return JsonMapper.toJson(this);
	}
	
}
