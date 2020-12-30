package cn.edu.dlmu.service;

import cn.edu.dlmu.base.AssertUtil;
import cn.edu.dlmu.base.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public interface IBaseService<T> {

	public void setBaseMapper(BaseMapper<T> baseMapper);

	/**
	 * 添加记录
	 *
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public Integer add(T entity) throws Exception;

	/**
	 * 添加记录
	 *
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public Integer add(List<T> entity) throws Exception;

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
	public Integer update(T t) throws Exception;

	/**
	 * 修改记录
	 *
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public Integer update(List<T> entity) throws Exception;

	/**
	 * 查询记录通过id
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public T queryById(Integer id) throws Exception;

	List<T> queryAll() throws Exception;

	/**
	 * 根据参数统计记录数
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Integer queryCountByParams(Map map) throws Exception;
}

///**
// * 分页查询
// * @param baseQuery
// * @return
// * @throws Exception
// */
//public PageInfo<T> queryForPage(BaseQuery baseQuery)throws Exception{
//	PageHelper.startPage(baseQuery.getPageNum(),baseQuery.getPageSize());
//	List<T> list= baseMapper.queryForPage(baseQuery);
//	PageInfo<T> pageInfo=new PageInfo<T>(list);
//	return pageInfo;
//}
//
///** 查询总数量
// *
// * @param map
// * @return
// * @throws Exception
// */
//@SuppressWarnings("rawtypes")
//List<T> queryByParams(Map map)throws Exception;
//
///**
// * 批量添加记录
// * @param entities
// * @return
// * @throws Exception
// */
//Integer insertBatch(List<T> entities) throws Exception;
//
///**
// * 批量删除
// * @param ids
// * @return
// */
//public Integer deleteBatch(Integer[] ids) throws Exception;
//
///**
// * 批量更新
// * @param map
// * @return
// * @throws Exception
// */
//@SuppressWarnings("rawtypes")
//public Integer updateBatch(Map map) throws Exception;