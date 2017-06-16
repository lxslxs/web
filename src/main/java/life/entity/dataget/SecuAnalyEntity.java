package life.entity.dataget;

import java.io.Serializable;

import life.util.json.JsonMapper;

/**
 * 功能：股市数据封装对象
 * 
 * @author xusheng.liu
 * @since 1.0
 * @version 2016年6月16日
 */
public class SecuAnalyEntity implements Serializable {
	private static final long serialVersionUID = -8942052074925747199L;
	private Integer id;// 主键
	private int code;// 代码
	private String name;// 名称
	private String lastPrice;// 最新价格
	private Double todayUpOrDown;// 今日涨跌幅

	private int pageNo;
	private int pageSize;
	private int pageBeg;
	private int pageEnd;
	private String time;
	
	public int getPageBeg() {
		return pageBeg;
	}

	public void setPageBeg(int pageBeg) {
		this.pageBeg = pageBeg;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(String lastPrice) {
		this.lastPrice = lastPrice;
	}

	public Double getTodayUpOrDown() {
		return todayUpOrDown;
	}

	public void setTodayUpOrDown(Double todayUpOrDown) {
		this.todayUpOrDown = todayUpOrDown;
	}

	public SecuAnalyEntity(int code, String time) {
		super();
		this.code = code;
		this.time = time;
	}

	public SecuAnalyEntity() {
		super();
	}

	@Override
	public String toString() {
		return JsonMapper.toJson(this);
	}
	
}
