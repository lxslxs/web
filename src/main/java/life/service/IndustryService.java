package life.service;

import java.util.List;
import java.util.Map;

import life.entity.ResultEntity;
import life.entity.SecretEntity;
import life.entity.dataget.SecuAnalyEntity;
import life.entity.dataget.SecuAnalyFianlEntity;

import org.springframework.stereotype.Service;

/**
 * @Descript:股票信息展示
 * @author xusheng.liu
 * @since  1.0
 * @version 2016年6月17日
 */
@Service
public interface IndustryService {

	/**
	 * @Description: 保存【新】
	 * @author xusheng.liu
	 * @date 2015年11月17日 上午11:47:49 
	 * @version V1.0 
	 * @throws Exception
	 */
	public ResultEntity insert(SecuAnalyEntity analyEntity) throws Exception;
	
	public ResultEntity insertFianl(List<String> l,int count) throws Exception;

	/**
	 * @Descript:查询【新】
	 * @author xusheng.liu
	 * @since  1.0
	 * @Time 2016年6月17日下午3:43:42
	 * @param analyEntity
	 * @return
	 */
	public List<SecuAnalyEntity> querySecuAnaly(SecuAnalyEntity analyEntity);
	public List<SecuAnalyFianlEntity> querySecuAnalyFianl(SecuAnalyFianlEntity fianlEntity);
	public Map<Integer,Object> findCountByDateType(Integer dateType);
	public void duwenjian(SecretEntity s) throws Exception;
	public List<SecretEntity> query(SecretEntity secretEntity);
	public List<SecuAnalyEntity> querySecuAnalyByCodeAndTime(
			SecuAnalyEntity entity);
}
