package life.entity.dataget;

import java.io.Serializable;
import java.util.Date;


public class DataGetEntity implements Serializable {
	private static final long serialVersionUID = 6038354518007433876L;
	private String id;//主键
	private int pages;//页数
	private Date date;//时间
	private String data;//数据
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
