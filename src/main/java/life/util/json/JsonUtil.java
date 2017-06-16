package life.util.json;

import java.util.Collection;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 功能： 把对象转化为JSON格式字符串
 * @author  xusheng.liu
 * @since   1.0
 * @version 2016年6月12日
 */
public class JsonUtil {
	
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public static String toJsonString(Object obj, String datePattern) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor(datePattern));
		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor(datePattern));
		if (obj instanceof Collection<?> || obj instanceof Object[]) {
			return JSONArray.fromObject(obj, jsonConfig).toString();
		} else {
			return JSONObject.fromObject(obj, jsonConfig).toString();
		}
	}
	
	public static String toJsonString(Object obj) {
		return toJsonString(obj, DEFAULT_DATE_PATTERN);
	}
}
