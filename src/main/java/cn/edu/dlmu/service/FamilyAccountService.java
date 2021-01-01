package cn.edu.dlmu.service;

import cn.edu.dlmu.pojo.Account;
import cn.edu.dlmu.pojo.FamilyAccount;

import java.util.List;
import java.util.Map;

public interface FamilyAccountService {

    Integer add(FamilyAccount entity) throws Exception;

    Boolean add(FamilyAccount familyAccount, Account executor) throws Exception;

    /**
     * 添加记录
     *
     * @param entity
     * @return
     * @throws Exception
     */
    Integer add(List<FamilyAccount> entity) throws Exception;

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
    Integer delete(List<Integer> id) throws Exception;

    /*修改记录*/
    Integer update(FamilyAccount t) throws Exception;

    Boolean update(FamilyAccount familyAccount, Account executor) throws Exception;

    /**
     * 修改记录
     *
     * @param entity
     * @return
     * @throws Exception
     */
    Integer update(List<FamilyAccount> entity) throws Exception;

    /**
     * 查询记录通过id
     *
     * @param id
     * @return
     * @throws Exception
     */
    FamilyAccount queryById(Integer id) throws Exception;

    FamilyAccount queryById(Integer id, Account executor) throws Exception;

    List<FamilyAccount> queryByAccount(Account executor) throws Exception;

    List<FamilyAccount> queryAll() throws Exception;

    /**
     * 根据参数统计记录数
     *
     * @param map
     * @return
     * @throws Exception
     */
    Integer queryCountByParams(Map map) throws Exception;

    /*增加一个用户到家庭组*/
    Boolean addFamilyMember(Account member, Account executor) throws Exception;

    Boolean deleteFamilyMember(Integer memberId, Account executor) throws Exception;

    List<Map<String, String>> convertFamilyMembers(Account executor) throws Exception;

    List<List<Object>> getFamilyMembers(Account executor) throws Exception;
}
