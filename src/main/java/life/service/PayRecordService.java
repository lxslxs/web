package life.service;

import java.io.IOException;
import java.util.Map;

import life.entity.CollectAuditPayRecord;
import life.entity.Pagination;
import life.entity.ResultEntity;

import org.springframework.stereotype.Service;


/**
 * 审核系统配置管理
 * @author chunsheng.zhang
 *
 */
@Service
public interface PayRecordService {

	/**
	 * @Description: 查询
	 * @author xusheng.liu
	 * @date 2015年10月29日 下午6:09:16 
	 * @version V1.0 
	 * @param pageSize 
	 * @param pageNo 
	 * @param capr 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public Pagination query(Integer pageNo, Integer pageSize,
			CollectAuditPayRecord capr) throws Exception;

	/**
	 * @Description: 执行支付
	 * @author xusheng.liu
	 * @date 2015年10月30日 下午5:25:08 
	 * @version V1.0 
	 * @param bsTaskId
	 * @return 
	 * @throws IOException
	 */
	public ResultEntity doPay(String id) throws IOException ;

	/**
	 * @Description: 封装接口参数
	 * @author xusheng.liu
	 * @date 2015年10月30日 下午5:21:05 
	 * @version V1.0 
	 * @param bsTaskId
	 * @param map
	 * @return
	 */
	public String packageContentForPay(String bsTaskId, Map<?, ?> map) ;
}
