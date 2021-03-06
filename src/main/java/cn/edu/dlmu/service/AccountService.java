package cn.edu.dlmu.service;

import cn.edu.dlmu.pojo.Account;
import cn.edu.dlmu.pojo.BondAccount;
import cn.edu.dlmu.pojo.FamilyAccount;

import java.util.List;
import java.util.Map;


public interface AccountService {

    Integer add(Account entity) throws Exception;

    /**
     * 添加记录
     *
     * @param entity
     * @return
     * @throws Exception
     */
    Integer add(List<Account> entity) throws Exception;

    /**
     * 删除记录
     *
     * @param id
     * @return
     * @throws Exception
     */
    Integer delete(Integer id) throws Exception;

    /**
     * 删除记录
     *
     * @param id
     * @return
     * @throws Exception
     */
    Integer delete(List<Integer> id) throws Exception;

    /*修改记录*/
    Integer update(Account t) throws Exception;

    /**
     * 修改记录
     *
     * @param entity
     * @return
     * @throws Exception
     */
    Integer update(List<Account> entity) throws Exception;

    /**
     * 查询记录通过id
     *
     * @param id
     * @return
     * @throws Exception
     */
    Account queryById(Integer id) throws Exception;

    List<Account> queryAll() throws Exception;

    /**
     * 根据参数统计记录数
     *
     * @param map
     * @return
     * @throws Exception
     */
    Integer queryCountByParams(Map map) throws Exception;

    /*校验账户*/
    Boolean checkUser(Account account) throws Exception;

    /*获取账户的家庭组信息*/
    //FamilyAccount queryFamily(Integer id) throws Exception;

    Account queryByLoginName(String loginName) throws Exception;

    /*获取账户的证券账户*/
    List<BondAccount> queryBondAccount(Integer id) throws Exception;

    List<Account> queryByParams(Map<String, Object> map);
}
