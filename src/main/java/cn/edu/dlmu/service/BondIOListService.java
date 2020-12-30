package cn.edu.dlmu.service;

import cn.edu.dlmu.pojo.BondIOList;

import java.util.List;
import java.util.Map;

public interface BondIOListService {

	public Integer add(BondIOList entity) throws Exception;

	/**
	 * 添加记录
	 *
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public Integer add(List<BondIOList> entity) throws Exception;

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
	public Integer update(BondIOList t) throws Exception;

	/**
	 * 修改记录
	 *
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public Integer update(List<BondIOList> entity) throws Exception;

	/**
	 * 查询记录通过id
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public BondIOList queryById(Integer id) throws Exception;

	List<BondIOList> queryAll() throws Exception;

	/**
	 * 根据参数统计记录数
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Integer queryCountByParams(Map map) throws Exception;
	
	/*根据证券账户获取所有证券*/
	public List<BondIOList> queryByBondListId(Integer bondListId);

}
