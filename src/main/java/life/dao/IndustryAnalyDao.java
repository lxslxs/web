package life.dao;

import java.util.List;

import life.base.mybatis.annotation.MyBatisRepository;
import life.entity.dataget.SecuAnalyEntity;

import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface IndustryAnalyDao {

	/**
	 * @Descript:保存
	 * @author xusheng.liu
	 * @since  1.0
	 * @Time 2016年6月16日下午5:23:57
	 * @param analyEntity
	 */
	void insertSecuAnaly(SecuAnalyEntity analyEntity);

	/**
	 * @Descript:主键查询
	 * @author xusheng.liu
	 * @since  1.0
	 * @Time 2016年6月16日下午5:23:48
	 * @param id
	 * @return
	 */
	SecuAnalyEntity queryByPrimaryKey(Integer id);
	
	/**
	 * @Descript:根据条件查询
	 * @author xusheng.liu
	 * @since  1.0
	 * @Time 2016年6月16日下午5:24:51
	 * @param analyEntity
	 * @return
	 */
	List<SecuAnalyEntity> queryByCondition(SecuAnalyEntity analyEntity);

	/**
	 * @Description: 分页查询
	 * @author xusheng.liu
	 * @date 2015年10月29日 下午6:11:06
	 * @version V1.0
	 * @param capr
	 * @param pageSize
	 * @param page
	 * @return
	 */
	List<SecuAnalyEntity> query(@Param(value = "pageNo") Integer pageNo,
			@Param(value = "pageSize") Integer pageSize,
			@Param(value = "cac") SecuAnalyEntity analyEntity);

	/**
	 * @Description: 查询总数（同步上面的方法）
	 * @author xusheng.liu
	 * @date 2015年10月30日 下午3:16:38 
	 * @version V1.0 
	 * @param capr
	 * @return
	 */
	long queryCount(@Param(value = "cac") SecuAnalyEntity analyEntity);

	List<SecuAnalyEntity> queryByCodeAndTime(SecuAnalyEntity entity);

}
