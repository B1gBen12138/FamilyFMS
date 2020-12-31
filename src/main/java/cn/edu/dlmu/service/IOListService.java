package cn.edu.dlmu.service;

import cn.edu.dlmu.pojo.Account;
import cn.edu.dlmu.pojo.IOList;

import java.util.List;
import java.util.Map;

public interface IOListService {


    /*批量删除流水*/
    Boolean add(IOList entity) throws Exception;

    /**
     * 添加记录
     *
     * @param entity
     * @return
     * @throws Exception
     */
    Integer add(List<IOList> entity) throws Exception;

    Boolean add(IOList ioList, Account executor) throws Exception;

    /**
     * 删除记录
     *
     * @param id
     * @return
     * @throws Exception
     */
    Boolean delete(Integer id) throws Exception;

    Boolean delete(Integer id, Account executor) throws Exception;

    /**
     * 删除记录
     *
     * @param id
     * @return
     * @throws Exception
     */
    Boolean delete(List<Integer> id) throws Exception;

    Boolean delete(List<Integer> id, Account executor) throws Exception;

    /*批量删除流水*/
    Boolean deleteBatch(List<IOList> ioLists);

    /*修改记录*/
    Boolean update(IOList t) throws Exception;

    /**
     * 修改记录
     *
     * @param entity
     * @return
     * @throws Exception
     */
    Integer update(List<IOList> entity) throws Exception;

    Boolean update(IOList ioList, Account executor) throws Exception;

    /**
     * 查询记录通过id
     *
     * @param id
     * @return
     * @throws Exception
     */
    IOList queryById(Integer id) throws Exception;

    IOList queryById(Integer id, Account account) throws Exception;

    List<IOList> queryByAccount(Account account) throws Exception;

    List<IOList> queryAll() throws Exception;

    /*根据参数查询收支流水*/
    List<IOList> queryByParams(Map map) throws Exception;

    /**
     * 根据参数统计记录数
     *
     * @param map
     * @return
     * @throws Exception
     */
    Integer queryCountByParams(Map map) throws Exception;

}
