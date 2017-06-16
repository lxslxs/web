/**
 * 
 */
package life.util.json;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;


/**
 * @author Navy Zhang
 * 
 */
public class DateJsonValueProcessor implements JsonValueProcessor {

	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	private DateFormat dateFormat;

	/**
	 * 构造方法.
	 * 
	 * @param datePattern
	 *            日期格式
	 */
	public DateJsonValueProcessor(String datePattern) {
		try {
			dateFormat = new SimpleDateFormat(datePattern);
		} catch (Exception ex) {
			dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		}
	}

	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return process(value);
	}

	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		return process(value);
	}

	private Object process(Object value) {
		if (value == null) { 
			return "";
		} else if (value instanceof Date) {
			return dateFormat.format((Date) value);
		} else if(value instanceof java.sql.Timestamp) {
			return convertTimestamp2String((java.sql.Timestamp)value, 19);
		} else {
			return value.toString();
		}

	}
	
	/**
     * 把Date类型的日期转换为指定格式的字符串。
     * @param date
     * @param pattern
     * @return
     */
    static public String convertTimestamp2String(java.sql.Timestamp tstamp ,int len) {
        if(tstamp == null) return null ;
        // java.sql.Timestamp返回字串格式为yyyy-mm-dd hh:mm:ss.fffffffff
        if(len < 1) return "" ;
        if(len > 29) len = 29 ;
        return tstamp.toString().substring(0 ,len) ;
    }

}
