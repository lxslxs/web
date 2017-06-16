package life.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import life.entity.dataget.SecuAnalyEntity;
import life.service.IndustryService;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 数据分析接口
 * @author  xusheng.liu
 * @since   1.0
 * @version 2016年8月16日
 */
@Controller
@RequestMapping("data")
public class DataShowController {

	@Autowired
	private IndustryService analyService;
	private Logger logger = LogManager.getLogger(getClass());

	/**
	 * 查询数据
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("show")
	@ResponseBody
	public List<SecuAnalyEntity> show(String pageNo,String pageSize,String today) throws Exception {
		logger.info("-->数据开始");
		SecuAnalyEntity sae = new SecuAnalyEntity();
		Date date = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		String datestring = s.format(date);
		sae.setTime(StringUtils.isBlank(today)?datestring:today);
		sae.setPageNo(Integer.valueOf(pageNo));
		sae.setPageSize(Integer.valueOf(pageSize));
		List<SecuAnalyEntity> list = this.analyService.querySecuAnaly(sae);
		return list;
	}
}
