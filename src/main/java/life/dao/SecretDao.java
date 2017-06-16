package life.dao;

import java.util.List;

import life.base.mybatis.annotation.MyBatisRepository;
import life.entity.SecretEntity;

@MyBatisRepository
public interface SecretDao {
	void insert(SecretEntity secretEntity);
	List<SecretEntity> query(SecretEntity secretEntity);
}
