package cn.edu.dlmu.service;

import cn.edu.dlmu.dao.BondAccountMapper;
import cn.edu.dlmu.pojo.BondAccount;

import java.util.List;
import java.util.Map;

public interface BondAccountService {

	public Integer add(BondAccount entity) throws Exception;

	/**
	 * 添加记录
	 *
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	

	/**
	 * 删除记录
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Integer delete(Integer id) throws Exception;

	/**
	 * 删除记录
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Integer delete(List<Integer> id) throws Exception;

	/*修改记录*/
	public Integer update(BondAccount t) throws Exception;

	/**
	 * 修改记录
	 *
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public Integer update(List<BondAccount> entity) throws Exception;

	/**
	 * 查询记录通过id
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public BondAccount queryById(Integer id) throws Exception;

	List<BondAccount> queryAll() throws Exception;

	/**
	 * 根据参数统计记录数
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Integer queryCountByParams(Map map) throws Exception;
	/*根据账户id查询所有证券账户*/
	public List<BondAccount> queryByAccountId(Integer accountId);



}
