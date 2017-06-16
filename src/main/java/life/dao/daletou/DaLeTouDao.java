package life.dao.daletou;

import java.util.List;

import life.base.mybatis.annotation.MyBatisRepository;
import life.entity.daletou.DaLeTouEntity;

@MyBatisRepository
public interface DaLeTouDao {
	void insert(DaLeTouEntity daLeTouEntity);
	List<DaLeTouEntity> query(DaLeTouEntity daLeTouEntity);
}
