package life.service.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import life.dao.IndustryAnalyDao;
import life.dao.IndustryDao;
import life.dao.IndustryTypeDao;
import life.dao.SecretDao;
import life.entity.ResultEntity;
import life.entity.SecretEntity;
import life.entity.dataget.IndustryTypeEntity;
import life.entity.dataget.SecuAnalyEntity;
import life.entity.dataget.SecuAnalyFianlEntity;
import life.service.IndustryService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("secuAnalyService")
public class IndustryServiceImpl implements IndustryService {

	private Logger logger = LogManager.getLogger(getClass());
	@Autowired
	private IndustryAnalyDao analyDao;
	@Autowired
	private IndustryTypeDao typeDao;
	@Autowired
	private SecretDao secretDao;
	@Autowired
	private IndustryDao industryDao;
	@Override
	public ResultEntity insert(SecuAnalyEntity analyEntity) throws Exception {
		ResultEntity re = new ResultEntity();
		if(analyEntity!=null){
			if(analyEntity.getId()!=null && analyEntity.getId()!=0){
			}else{//保存
				analyDao.insertSecuAnaly(analyEntity);
				re.setSuccess(true);
			}
		}
		return re;
	}
	@Override
	public List<SecuAnalyEntity> querySecuAnaly(
			SecuAnalyEntity analyEntity) {
		int b = (analyEntity.getPageNo()-1)*analyEntity.getPageSize();
		int e = analyEntity.getPageNo()*analyEntity.getPageSize();
		analyEntity.setPageBeg(b);
		analyEntity.setPageEnd(e==0?2:e);
		logger.info("查询参数="+analyEntity);
		List<SecuAnalyEntity> list = this.analyDao.queryByCondition(analyEntity);
		return list;
	}
	@Override
	public ResultEntity insertFianl(List<String> l,int count)
			throws Exception {
		if (l != null && l.size() > 0) {
			SecuAnalyFianlEntity entity = null;
			for (String i : l) {
				entity = packageFianlEntity(i);
				if (entity != null) {
					List<SecuAnalyFianlEntity> secuAnaly = this.industryDao.queryByCondition(entity);
					if (CollectIsNotBlank(secuAnaly)) {
						logger.info("-->已存在：" + secuAnaly);
					} else {
						this.industryDao.insert(entity);
						count++;
						logger.info("-->保存成功：entity=" + entity);
					}
				}
			}
		}
		return null;
	}
	/**
	 * @Descript:封装数据
	 * @author xusheng.liu
	 * @since 1.0
	 * @Time 2016年6月17日下午3:34:15
	 * @param string
	 * @return
	 * @throws ParseException
	 */
	private SecuAnalyFianlEntity packageFianlEntity(String string) throws ParseException {
//		
//		2,002801,微光股份,45.24,100.00,1,9.99,68.05,9,61.05,60.23,11,-,机械行业,BK05451,2016-06-29 15:00:00
		SecuAnalyFianlEntity entity;
		String[] split = string.split(",");
		entity = new SecuAnalyFianlEntity();
		entity.setCode(Integer.parseInt(split[1]));
		entity.setName(split[2]);
//		保存行业，没有就新增。
		List<IndustryTypeEntity> condition = typeDao.queryByCondition(new IndustryTypeEntity(split[13]));
		if(condition!=null && condition.size()>0){
			entity.setTypeId(condition.get(0).getId());
		}else{
			Integer typeId = typeDao.insert(new IndustryTypeEntity(split[13]));
			entity.setTypeId(typeId);
		}
		return entity;
	}
//	@Test
	public void duwenjian(SecretEntity s) throws Exception{
		secretDao.insert(s);
	}
	@Override
	public List<SecretEntity> query(SecretEntity secretEntity) {
		return secretDao.query(secretEntity);
	}
	@Override
	public List<SecuAnalyFianlEntity> querySecuAnalyFianl(
			SecuAnalyFianlEntity fianlEntity) {
		// TODO
		return null;
	}
	public boolean CollectIsNotBlank(List<?> list) {
		return list != null && list.size() > 0;
	}
	@Override
	public Map<Integer, Object> findCountByDateType(Integer dateType) {
		// TODO
		return null;
	}
	@Override
	public List<SecuAnalyEntity> querySecuAnalyByCodeAndTime(
			SecuAnalyEntity entity) {
		List<SecuAnalyEntity> list = this.analyDao.queryByCodeAndTime(entity);
		return list;
	}
}
