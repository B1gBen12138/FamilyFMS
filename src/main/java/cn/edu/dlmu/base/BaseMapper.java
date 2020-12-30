package cn.edu.dlmu.base;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

@Mapper
public interface BaseMapper<T> {

	/**
	 * 添加记录不返回主键
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 */
	public Integer add(T entity) throws DataAccessException, Exception;

	/**
	 * 删除记录
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id) throws DataAccessException, Exception;

	/**
	 * 更新记录
	 * @param entity
	 * @return
	 */
	public Integer update(T entity) throws DataAccessException, Exception;

	/**
	 * 查询总记录数
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer queryCountByParams(Map map) throws DataAccessException;

	/**
	 * 查询记录 通过id
	 * @param id
	 * @return
	 */
	public T queryById(Integer id) throws DataAccessException, Exception;

	public List<T> queryAll() throws Exception;

 	/**
	 * 分页查询记录
	 * @param baseQuery
	 * @return
	 */
	//public List<T> queryForPage(BaseQuery baseQuery) throws DataAccessException;

	/**
	 * 查询记录不带分页情况
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<T> queryByParams(Map map) throws DataAccessException;

	/**
	 *
	 * @param entities
	 * @return
	 * @throws DataAccessException
	 */
	public Integer addBatch(List<T> entities) throws DataAccessException;
	/**
	 * 批量更新
	 * @param map
	 * @return
	 * @throws DataAccessException
	 */
	public Integer updateBatch(Map map) throws DataAccessException;
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public Integer deleteBatch(Integer[] ids) throws DataAccessException;
}
