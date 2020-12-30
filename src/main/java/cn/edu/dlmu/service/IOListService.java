package cn.edu.dlmu.service;

import cn.edu.dlmu.dao.IOListMapper;
import cn.edu.dlmu.pojo.FamilyAccount;
import cn.edu.dlmu.pojo.IOList;

import java.util.List;
import java.util.Map;

public interface IOListService{

	public Integer add(IOList entity) throws Exception;

	/**
	 * 添加记录
	 *
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public Integer add(List<IOList> entity) throws Exception;

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
	public Integer update(IOList t) throws Exception;

	/**
	 * 修改记录
	 *
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public Integer update(List<IOList> entity) throws Exception;

	/**
	 * 查询记录通过id
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public IOList queryById(Integer id) throws Exception;

	List<IOList> queryAll() throws Exception;

	/**
	 * 根据参数统计记录数
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Integer queryCountByParams(Map map) throws Exception;
	
	/*批量删除流水*/
	public Boolean deleteBatch(List<IOList> ioLists);

	/*根据家庭统计流水*/
	public List<IOList> queryByFamily(FamilyAccount familyAccount);

}
