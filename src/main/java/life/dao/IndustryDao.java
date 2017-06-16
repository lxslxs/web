package life.dao;

import java.util.List;

import life.base.mybatis.annotation.MyBatisRepository;
import life.entity.dataget.SecuAnalyFianlEntity;

import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface IndustryDao {

	void insert(SecuAnalyFianlEntity secuAnalyFianlEntity);

	SecuAnalyFianlEntity queryByPrimaryKey(Integer id);
	
	List<SecuAnalyFianlEntity> queryByCondition(SecuAnalyFianlEntity secuAnalyFianlEntity);

	List<SecuAnalyFianlEntity> query(@Param(value = "pageNo") Integer pageNo,
			@Param(value = "pageSize") Integer pageSize,
			@Param(value = "cac") SecuAnalyFianlEntity analyEntity);

	long queryCount(@Param(value = "cac") SecuAnalyFianlEntity analyEntity);

}
