package cn.edu.dlmu.service.impl;

import cn.edu.dlmu.base.AssertUtil;
import cn.edu.dlmu.dao.AccountMapper;
import cn.edu.dlmu.dao.FamilyAccountMapper;
import cn.edu.dlmu.pojo.Account;
import cn.edu.dlmu.pojo.FamilyAccount;
import cn.edu.dlmu.service.FamilyAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("familyAccountServiceImpl")
public class FamilyAccountServiceImpl implements FamilyAccountService {

    private final static Logger logger = LogManager.getLogger(FamilyAccountService.class);

    @Autowired
    FamilyAccountMapper familyAccountMapper;

    @Autowired
    AccountMapper accountMapper;

    public void setFamilyAccountMapper(FamilyAccountMapper familyAccountMapper) {
        this.familyAccountMapper = familyAccountMapper;
    }

    public Integer add(FamilyAccount t) throws Exception {
        AssertUtil.isNull(t, "实体为空");
        return familyAccountMapper.add(t);
    }

    @Override
    public Boolean add(FamilyAccount familyAccount, Account executor) throws Exception {
        if (familyAccount == null || executor == null)
            return Boolean.FALSE;

        AssertUtil.notNull(familyAccount.getId(), "参数错误");
        AssertUtil.isNull(familyAccount.getName(), "参数错误");

        if (executor.getFamilyId() != null)
            return Boolean.FALSE;

        if (familyAccountMapper.addAndReturnId(familyAccount) != 1)
            return Boolean.FALSE;

        executor.setIsSuperAccount(true);
        executor.setFamilyId(familyAccount.getId());
        return familyAccountMapper.addFamilyMember(executor) == 1;
    }

    /*批量添加记录*/
    public Integer add(List<FamilyAccount> tList) throws Exception {
        int cot = 0;
        for (FamilyAccount t : tList) {
            familyAccountMapper.add(t);
            cot++;
        }
        return cot;
    }

    /*删除记录*/
    public Boolean delete(Integer id) throws Exception {
        AssertUtil.isNull(id, "列表为空");

        // TODO: implement with SQL

        familyAccountMapper.deleteFamilyMemberAll(id);

        return familyAccountMapper.delete(id) == 1;
    }

    @Override
    public Boolean delete(Integer id, Account executor) throws Exception {
        if (executor == null)
            return null;

        if (!executor.getIsAdmin()) {
            if (executor.getIsSuperAccount()) {
                if (executor.getFamilyId() == null) {
                    logger.warn(String.format("unexpected status of executor: %s", executor.toString()));
                    return Boolean.FALSE;
                }
                if (!executor.getFamilyId().equals(id))
                    return Boolean.FALSE;
            } else
                return Boolean.FALSE;
        }

        return this.delete(id);
    }

    /*删除记录*/
    public Integer delete(List<Integer> tList) throws Exception {
        // 判断 空
        AssertUtil.isNull(tList, "列表为空");
        int cot = 0;
        for (Integer id : tList) {
            AssertUtil.isNull(queryById(id), "待删除的记录不存在!");
            cot += familyAccountMapper.delete(id);
        }
        return cot;
    }

    /*修改记录*/
    public Integer update(FamilyAccount entity) throws Exception {
        return familyAccountMapper.update(entity);
    }

    @Override
    public Boolean update(FamilyAccount familyAccount, Account executor) throws Exception {
        if (familyAccount == null || executor == null)
            return Boolean.FALSE;

        AssertUtil.isNull(familyAccount.getId(), "参数错误");
        AssertUtil.isNull(familyAccount.getName(), "参数错误");

        if (!executor.getIsAdmin()) {
            if (executor.getIsSuperAccount()) {
                if (executor.getFamilyId() == null) {
                    logger.warn(String.format("unexpected status of executor: %s", executor.toString()));
                    return Boolean.FALSE;
                }
                if (!executor.getFamilyId().equals(familyAccount.getId()))
                    return Boolean.FALSE;
            } else
                return Boolean.FALSE;
        }

        return familyAccountMapper.update(familyAccount) == 1;
    }

    public Integer update(List<FamilyAccount> tList) throws Exception {
        AssertUtil.isNull(tList, "List is Null");
        int cot = 0;
        for (FamilyAccount t : tList) {
            familyAccountMapper.update(t);
            cot++;
        }
        return cot;
    }

    /**
     * 根据参数统计记录数
     *
     * @param map
     * @return
     * @throws Exception
     */
    public Integer queryCountByParams(Map map) throws Exception {
        return familyAccountMapper.queryCountByParams(map);
    }

    /*根据id查询记录*/
    public FamilyAccount queryById(Integer id) throws Exception {
        AssertUtil.isNull(id, "记录id非空!");
        return familyAccountMapper.queryById(id);
    }

    @Override
    public FamilyAccount queryById(Integer id, Account executor) throws Exception {
        if (executor == null)
            return null;

        if (!executor.getIsAdmin()) {
            if (executor.getIsSuperAccount()) {
                if (executor.getFamilyId() == null || !executor.getFamilyId().equals(id))
                    return null;
            } else
                return null;
        }

        return familyAccountMapper.queryById(id);
    }

    @Override
    public List<FamilyAccount> queryByAccount(Account executor) throws Exception {
        if (executor == null)
            return null;

        if (!executor.getIsAdmin()) {
            if (executor.getFamilyId() == null) {
                if (executor.getIsSuperAccount())
                    logger.warn(String.format("unexpected status of family member: %s", executor.toString()));
                return null;
            }
            FamilyAccount familyAccount = familyAccountMapper.queryById(executor.getFamilyId());
            if (familyAccount == null) {
                logger.warn(String.format("unexpected status of family account: %s", familyAccount.toString()));
                return null;
            }
            List<FamilyAccount> result = new LinkedList<>();
            result.add(familyAccount);
            return result;
        } else
            return familyAccountMapper.queryAll();
    }

    public List<FamilyAccount> queryAll() throws Exception {
        return familyAccountMapper.queryAll();
    }

    @Override
    public Boolean addFamilyMember(Account member, Account executor) throws Exception {
        if (member == null || executor == null)
            return Boolean.FALSE;

        AssertUtil.isNull(member.getId(), "参数错误");
        AssertUtil.isNull(member.getFamilyId(), "参数错误");

        Integer familyId = member.getFamilyId();

        if (!executor.getIsAdmin()) {
            if (executor.getIsSuperAccount()) {
                if (executor.getFamilyId() == null) {
                    logger.warn(String.format("unexpected status of executor: %s", executor.toString()));
                    return Boolean.FALSE;
                }

                if (executor.getId().equals(member.getId()) && executor.getFamilyId().equals(familyId))
                    return Boolean.TRUE;

                if (!executor.getFamilyId().equals(familyId))
                    return Boolean.FALSE;

                Account oldMember = accountMapper.queryById(member.getId());
                if (oldMember == null || (oldMember.getFamilyId() != null && !oldMember.getFamilyId().equals(executor.getFamilyId())))
                    return Boolean.FALSE;
            } else
                return Boolean.FALSE;
        }

        logger.debug(String.format("add family member: %s", member.toString()));

        return familyAccountMapper.addFamilyMember(member) == 1;
    }

    @Override
    public Boolean deleteFamilyMember(Integer memberId, Account executor) throws Exception {
        if (memberId == null || executor == null)
            return Boolean.FALSE;

        if (!executor.getIsAdmin()) {
            if (executor.getIsSuperAccount()) {
                if (executor.getFamilyId() == null) {
                    logger.warn(String.format("unexpected status of executor: %s", executor.toString()));
                    return Boolean.FALSE;
                }
            } else
                return Boolean.FALSE;
        }

        Account member = accountMapper.queryById(memberId);
        if (member.getFamilyId() == null || (!executor.getIsAdmin() && !executor.getFamilyId().equals(member.getFamilyId())))
            return Boolean.FALSE;

        logger.debug(String.format("add family member: %s", member.toString()));

        return familyAccountMapper.deleteFamilyMember(memberId) == 1;
    }

    @Override
    public List<Map<String, String>> convertFamilyMembers(Account executor) throws Exception {
        if (executor == null)
            return null;

        List<List<Object>> familyMembers = this.getFamilyMembers(executor);
        if (familyMembers == null)
            return null;

        List<Map<String, String>> result = new LinkedList<>();
        for (List<Object> familyMember : familyMembers) {
            FamilyAccount familyAccount = (FamilyAccount) familyMember.get(0);
            Account account = (Account) familyMember.get(1);
            Map<String, String> members = new HashMap<>();

            if (familyAccount == null && account == null)
                ;
            else {
                members.put("familyId", familyAccount != null ? familyAccount.getId().toString() : "");
                members.put("familyName", familyAccount != null ? familyAccount.getName() : "");
                members.put("accountId", account != null ? account.getId().toString() : "");
                members.put("accountName", account != null ? account.getName() : "");
                members.put("isAdmin", account != null ? account.getIsSuperAccount().toString() : "");
                result.add(members);
            }

        }

        logger.debug(String.format("list size %d", result.size()));

        return result;
    }

    // TODO: implement with SQL
    @Override
    public List<List<Object>> getFamilyMembers(Account executor) throws Exception {
        if (executor == null)
            return null;

        List<FamilyAccount> familyAccounts;
        List<Account> accounts;

        if (executor.getIsAdmin()) {
            familyAccounts = familyAccountMapper.queryAll();
            accounts = accountMapper.queryAll();
        } else {
            familyAccounts = new LinkedList<>();
            familyAccounts.add(familyAccountMapper.queryById(executor.getFamilyId()));
            if (executor.getIsSuperAccount()) {
                accounts = accountMapper.querySpecificAndSingle(executor.getFamilyId());
            } else
                accounts = accountMapper.queryByFamilyId(executor.getFamilyId());
        }

        logger.debug(String.format("users' size %d, families' size %d", accounts.size(), familyAccounts.size()));

        List<List<Object>> result = new LinkedList<>();
        List<Object> members;
        Iterator<FamilyAccount> familyAccountIterator = familyAccounts.iterator();
        while (familyAccountIterator.hasNext()) {
            FamilyAccount familyAccount = familyAccountIterator.next();
            boolean hasMember = false;
            if (familyAccount == null)
                logger.warn("null item in list");
            else {
                Iterator<Account> accountIterator = accounts.iterator();
                while (accountIterator.hasNext()) {
                    Account account = accountIterator.next();
                    members = new LinkedList<>();
                    if (account == null)
                        logger.warn("null item in list");
                    else if (familyAccount.getId() != null && familyAccount.getId().equals(account.getFamilyId())) {
                        members.add(familyAccount);
                        members.add(account);
                        accountIterator.remove();
                        result.add(members);
                        hasMember = true;
                    }

                }
                if (hasMember)
                    familyAccountIterator.remove();
            }
        }

        for (FamilyAccount familyAccount : familyAccounts) {
            members = new LinkedList<>();
            members.add(familyAccount);
            members.add(null);
            result.add(members);
        }

        for (Account account : accounts) {
            members = new LinkedList<>();
            members.add(null);
            members.add(account);
            result.add(members);
        }

        return result;
    }
}
