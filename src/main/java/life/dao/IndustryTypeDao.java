package life.dao;

import java.util.List;

import life.base.mybatis.annotation.MyBatisRepository;
import life.entity.dataget.IndustryTypeEntity;

import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface IndustryTypeDao {
	Integer insert(IndustryTypeEntity typeEntity);
	IndustryTypeEntity queryByPrimaryKey(Integer id);
	List<IndustryTypeEntity> queryByCondition(IndustryTypeEntity typeEntity);
	List<IndustryTypeEntity> query(@Param(value = "pageNo") Integer pageNo,
			@Param(value = "pageSize") Integer pageSize,
			@Param(value = "cac") IndustryTypeEntity typeEntity);
	long queryCount(@Param(value = "cac") IndustryTypeEntity typeEntity);
}
