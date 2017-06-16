package life.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

import life.dao.SecretDao;
import life.entity.SecretEntity;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 功能： 数据来源
 * 
 * @author xusheng.liu
 * @since 1.0
 * @version 2016年6月16日
 */
public class DataGet {
	private static Logger logger = LogManager.getLogger(DataGet.class);
	public static int pageNo = 1;
	public static int pageSize = 200;
	@Autowired
	private SecretDao secretDao;
	public static String url = "http://60.173.202.219/wssb/admin/grpass.jsp?xingm=刘旭升&AtAction=Logon&sfz=341221199010170816&password="
			+ 123456;

	public static String getDataUrl(int pageN, int pageSiz) {
		return "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx/JS.aspx?"
				+ "type=ct&st=(BalFlowMain)&sr=-1&p="
				+ pageN
				+ "&ps="
				+ pageSiz
				+ "&js=var%20XvGwtNuM={pages:(pc),date:%222014-10-22%22,data:[(x)]}"
				+ "&token=894050c76af8597a853f5b408b759f5d&cmd=C._AB&sty=DCFFITA&rt=48868279";
	}

	public static String getFianlUrl(int pageN, int pageSiz) {
		return "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx/JS.aspx?"
				+ "type=ct&st=(FFRank)&sr=1&p="
				+ pageN
				+ "&ps="
				+ pageSiz
				+ "&js=var%20lASmdfCN={pages:(pc),data:[(x)]}&token=894050c76af8597a853f5b408b759f5d&cmd=C._AB&sty=DCFFITAM&rt=48906236";
	}

	public static void setUrl(String url) {
		DataGet.url = url;
	}

	public static String getOneHtml(String htmlurl, String encoding)
			throws IOException {
		// 检查网址的有效性
		if (htmlurl == null || htmlurl.trim().length() <= 0) {
			return null;
		}
		URL url;
		String temp;
		StringBuffer sb = new StringBuffer();
		BufferedReader in = null;
		InputStream is = null;
		url = new URL(htmlurl);
		HttpURLConnection http = (HttpURLConnection) url.openConnection(); // http请求
		try {
			// 提取字符编码
			String contentType = http.getContentType();
			String myencoding = null;
			if (contentType != null) {
				int index = contentType.indexOf("charset=");
				if (index != -1) {
					myencoding = contentType.substring(index + 8).trim();
				}
			}
			// 如果在HTTP链接中找到字符集信息，则优先使用
			if (myencoding != null && myencoding.trim().length() > 0) {
				encoding = myencoding;
			}
			// System.out.println("编码：" + encoding);
			if (encoding == null || encoding.trim().length() <= 0) {
				encoding = "gb2312";
			}
			is = http.getInputStream(); // http流
			in = new BufferedReader(new InputStreamReader(is, encoding));// 读取网页全部内容
			while ((temp = in.readLine()) != null) {
				sb.append(temp);
			}
		} catch (MalformedURLException me) {
			me.getMessage();
			throw me;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				in.close();
				is.close();
			} catch (Exception e) {
			}
			if (http != null) {
				http.disconnect();
			}
		}
		return sb.toString();
	}

	@Test
	public void duwenjian() {
		File file = new File("D:\\1111111111\\1pass00.txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
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

	public static String urls = "http://60.173.202.219/wssb/admin/grpass.jsp?"
			+ "xingm=www"
			+ "&AtAction=Logon"
			+ "&sfz=341221199010170816"
			+ "&password=222ww2";
	public static void main(String[] args) throws Exception {
		String html = getOneHtml(urls, "uft-8");
		logger.info("--->"+html);
	}

	static String email = "8952598@qq.com";
	static String password = "1233456";// 密码长度6-20位
	static String captcha = "bgds";

	public static void maina(String[] args) throws IOException, ParseException {
		// String checkName = "https://h5.qzone.qq.com/mqzone/index?eas_sid=";
		String checkName = "http://ptlogin2.qzone.com/ho_cross_domain?tourl=https%3A%2F%2Fh5.qzone.qq.com%2Fmqzone%2Findex";
		String content = getOneHtml(checkName, "gb2312");
		// Document check = Jsoup.connect(checkName).get();
		logger.info("-->" + content);
	}
	/*
	 * static String ChekcSuc = "通过"; static String username = "111";// 2~40
	 * public static void main(String[] args) throws IOException, ParseException
	 * { String checkName = "http://www.mba518.com/users/checkreg.asp?username="
	 * + username; Document check = Jsoup.connect(checkName).get(); String html
	 * = check.getElementById("jump").parent().parent().parent()
	 * .child(0).text(); String password = ""; if (html.contains(ChekcSuc)) {
	 * logger.info("-->不可用"); } else { logger.info("-->已经注册，开始模拟测试密码"); String s
	 * = "http://www.mba518.com/users/login.asp?action=login&password=" +
	 * password + "&username=" + username; // String content = getOneHtml(s,
	 * "gb2312"); Document doc = Jsoup.connect(s).get(); Element element =
	 * doc.getElementById("qqkf"); if (element != null) { logger.info("-->通过！");
	 * File file = new File("D:\\1111111111\\shell.txt"); String content =
	 * username+"---->"+password; try (FileOutputStream fop = new
	 * FileOutputStream(file)) { if (!file.exists()) { file.createNewFile(); }
	 * byte[] contentInBytes = content.getBytes(); fop.write(contentInBytes);
	 * fop.flush(); fop.close(); } catch (IOException e) { e.printStackTrace();
	 * } } else { logger.info("-->密码错误，继续测试"); } logger.info(doc); } }
	 */
}
