package life.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import life.dao.SecretDao;
import life.entity.SecretEntity;
import life.entity.daletou.DaLeTouEntity;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 大乐透数据收集分析
 * @author  xusheng.liu
 * @since   1.0
 * @version 2016年10月18日
 */
public class DLTDataGet {
	private static Logger logger = LogManager.getLogger(DLTDataGet.class);
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
	public static int pageNo = 1;
	public static int pageSize = 200;
	public static String urlAll = "http://baidu.lecai.com/lottery/draw/list/1/?type=range&start=14141&end=16121";
	public static String urlOne = "http://baidu.lecai.com//lottery/draw/ajax_get_detail.php?lottery_type=1&phase=";
	public static void main(String[] args) throws Exception {
//		Document doc = Jsoup.connect(urlOne).get();
//		String num = "16122";
//		saveDaLeTou(num);
//		writeResultToTxt(obj);
	}
	@Test
	public void ssss(){
		String num = "16122";
		saveDaLeTou(num);
	}

	public static DaLeTouEntity saveDaLeTou(String num) {
		String html = "";
		try {
			html = DataGet.getOneHtml(urlOne+num, null);
		} catch (IOException e) {
			logger.error("e:"+e);
		}
		if(html.contains("彩期不存在")){
			logger.info("---->彩期不存在："+num);
			return null;
		}
		logger.info("-->开始保存："+num);
		JSONObject obj = JSONObject.fromObject(html);
		JSONArray obj1 = obj.getJSONObject("data").getJSONObject("result").getJSONArray("result");
		StringBuilder redBalls = new StringBuilder();
		StringBuilder bullBalls = new StringBuilder();
		if(obj1.size()>0){
			for (int i = 0; i < obj1.size(); i++) {
				Object red = obj1.getJSONObject(i).get("key");
				if("red".equals(red.toString())){
					JSONArray data = obj1.getJSONObject(i).getJSONArray("data");
					for (Object o : data) {
						redBalls.append(o.toString()+",");
					}
				}
				if("blue".equals(red.toString())){
					JSONArray data = obj1.getJSONObject(i).getJSONArray("data");
					for (Object o : data) {
						bullBalls.append(o.toString()+",");
					}
				}
			}
		}
		return new DaLeTouEntity(redBalls.toString(),bullBalls.toString(),num);
	}

	/**
	 * 把链接返回的结果写在txt文件中
	 * @param doc
	 */
	@SuppressWarnings("unused")
	private static void writeResultToTxt(Document doc) {
		File file = new File("D:\\1111111111\\shell_"+ sdf.format(new Date()) +".txt");
		try (FileOutputStream fop = new FileOutputStream(file)) {
			if (!file.exists()) {
				file.createNewFile();
			}
			byte[] contentInBytes = doc.toString().getBytes();
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	@Test
	public void duwenjian() {
		File file = new File("D:\\1111111111\\1pass00.txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {
				logger.info("line " + line + ": " + tempString);
				line++;
				SecretEntity se = new SecretEntity();
				se.setContent(tempString);
				se.setMd5Content(MD5.getMD5String(tempString));
				secretDao.insert(se);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Autowired
	private SecretDao secretDao;
}
