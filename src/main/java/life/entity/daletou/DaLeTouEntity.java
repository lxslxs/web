package life.entity.daletou;

import java.io.Serializable;


public class DaLeTouEntity implements Serializable {
	private static final long serialVersionUID = 3570996472492115748L;
	private Integer id;//主键
	private String blueBalls;//页数
	private String redBalls;//页数
	private String qi;//页数
	private String date;//时间
	
	public DaLeTouEntity(String blueBalls, String redBalls) {
		super();
		this.blueBalls = blueBalls;
		this.redBalls = redBalls;
	}
	public DaLeTouEntity() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBlueBalls() {
		return blueBalls;
	}
	public void setBlueBalls(String blueBalls) {
		this.blueBalls = blueBalls;
	}
	public String getRedBalls() {
		return redBalls;
	}
	public DaLeTouEntity(String redBalls, String blueBalls, String qi) {
		super();
		this.blueBalls = blueBalls;
		this.redBalls = redBalls;
		this.qi = qi;
	}
	public String getQi() {
		return qi;
	}
	public void setQi(String qi) {
		this.qi = qi;
	}
	public void setRedBalls(String redBalls) {
		this.redBalls = redBalls;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
