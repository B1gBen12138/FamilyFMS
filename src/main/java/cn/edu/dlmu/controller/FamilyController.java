package cn.edu.dlmu.controller;

import cn.edu.dlmu.base.AssertUtil;
import cn.edu.dlmu.pojo.Account;
import cn.edu.dlmu.pojo.FamilyAccount;
import cn.edu.dlmu.service.AccountService;
import cn.edu.dlmu.service.FamilyAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/family")
public class FamilyController {

    private final static Logger logger = LogManager.getLogger(FamilyController.class);

    @Autowired
    @Qualifier("familyAccountServiceImpl")
    private FamilyAccountService familyAccountService;

    @Autowired
    @Qualifier("accountServiceImpl")
    private AccountService accountService;

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ModelAndView list(HttpSession s) {

        try {
            Account user = (Account) s.getAttribute("loginAccount");
            if (user == null || user.getId() == null)
                return new ModelAndView("/tologin")
                        .addObject("flag", 2);

            logger.debug(String.format("user %s try to list", user.toString()));

            List<Map<String, String>> familyMemberList = familyAccountService.convertFamilyMembers(user);
            if (familyMemberList == null || familyMemberList.isEmpty())
                familyMemberList = null;

            return new ModelAndView("/family/list")
                    .addObject("isAdmin", user.getIsAdmin())
                    .addObject("isSuperAccount", user.getIsSuperAccount())
                    .addObject("familyMemberList", familyMemberList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("/500")
                    .addObject("ex", e);
        }
    }

    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(Integer familyId, Integer accountId, HttpSession s) {
        try {
            Account user = (Account) s.getAttribute("loginAccount");
            if (user == null || user.getId() == null)
                return new ModelAndView("/tologin")
                        .addObject("flag", 2);

            if (familyId != null) {

                if (!user.getIsAdmin() && !user.getIsSuperAccount())
                    return new ModelAndView("/403");


                logger.debug(String.format("user %s try to edit family %d", user.toString(), familyId));
                FamilyAccount familyAccount = familyAccountService.queryById(familyId, user);
                AssertUtil.isTrue(familyAccount == null, "家庭信息不存在");

                logger.debug(String.format("target family: %s", familyAccount.toString()));

                return new ModelAndView("/family/edit")
                        .addObject("familyAccount", familyAccount)
                        .addObject("isAdmin", user.getIsAdmin())
                        .addObject("editType", 0);
            } else if (accountId != null) {

                if (!user.getIsAdmin() && !user.getIsSuperAccount())
                    return new ModelAndView("/403");


                logger.debug(String.format("user %s try to edit family member %d", user.toString(), accountId));

                Account account = accountService.queryById(accountId);
                AssertUtil.isNull(account, "家庭成员不存在");

                logger.debug(String.format("target family member: %s", account.toString()));

                List<FamilyAccount> familyAccounts = null;
                familyAccounts = familyAccountService.queryByAccount(user);

                FamilyAccount familyAccount = null;
                if (familyAccounts != null && account.getFamilyId() != null)
                    familyAccount = familyAccounts.stream().filter(f -> f.getId().equals(account.getFamilyId())).collect(Collectors.toList()).get(0);

                return new ModelAndView("/family/edit")
                        .addObject("account", account)
                        .addObject("familyAccount", familyAccount)
                        .addObject("familyAccounts", familyAccounts)
                        .addObject("isAdmin", user.getIsAdmin())
                        .addObject("editType", 1);
            } else {

                if (!user.getIsAdmin() && user.getFamilyId() != null)
                    return new ModelAndView("/403");

                logger.debug(String.format("user %s try to new family", user.toString()));
                FamilyAccount familyAccount = new FamilyAccount();

                return new ModelAndView("/family/edit")
                        .addObject("familyAccount", familyAccount)
                        .addObject("isAdmin", user.getIsAdmin())
                        .addObject("editType", 2);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("/500")
                    .addObject("ex", e);
        }
    }

    @RequestMapping(path = "/editMember", method = RequestMethod.POST)
    public ModelAndView editMember(Account account, HttpSession s) {
        try {
            Account user = (Account) s.getAttribute("loginAccount");
            if (user == null || user.getId() == null)
                return new ModelAndView("/tologin")
                        .addObject("flag", 2);

            if (!user.getIsAdmin() && !user.getIsSuperAccount())
                return new ModelAndView("/403");

            // 添加家庭成员
            if (account != null) {
                logger.debug(String.format("user %s try to update account %s", user.toString(), account.toString()));
                familyAccountService.addFamilyMember(account, user);
            } else
                return new ModelAndView("/500");

            return list(s);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("/500")
                    .addObject("ex", e);
        }
    }

    @RequestMapping(path = "/editFamily", method = RequestMethod.POST)
    public ModelAndView editFamily(FamilyAccount familyAccount, HttpSession s) {
        try {
            Account user = (Account) s.getAttribute("loginAccount");
            if (user == null || user.getId() == null)
                return new ModelAndView("/tologin")
                        .addObject("flag", 2);

            if (familyAccount != null) {
                // 新建家庭
                if (familyAccount.getId() == null) {
                    if (!user.getIsAdmin() && user.getFamilyId() != null)
                        return new ModelAndView("/403");

                    logger.debug(String.format("user %s try to add family account %s", user.toString(), familyAccount.toString()));
                    AssertUtil.isNull(familyAccount.getName(), "参数错误");
                    if (familyAccountService.add(familyAccount, user)) {
                        user = accountService.queryById(user.getId());
                        s.setAttribute("loginAccount", user);
                    }
                }
                // 更新家庭
                else {
                    if (!user.getIsAdmin() && user.getIsSuperAccount())
                        return new ModelAndView("/403");

                    logger.debug(String.format("user %s try to update family account %s", user.toString(), familyAccount.toString()));
                    AssertUtil.isNull(familyAccount.getId(), "参数错误");
                    AssertUtil.isNull(familyAccount.getName(), "参数错误");
                    familyAccountService.update(familyAccount, user);
                }
            } else
                return new ModelAndView("/500");

            return list(s);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("/500")
                    .addObject("ex", e);
        }
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(Integer familyId, Integer accountId, HttpSession s) {
        try {
            Account user = (Account) s.getAttribute("loginAccount");
            if (user == null || user.getId() == null)
                return new ModelAndView("/tologin")
                        .addObject("flag", 2);

            Boolean result = Boolean.FALSE;
            if (familyId != null) {
                logger.debug(String.format("user %s try to delete family %d", user.toString(), familyId));
                result = familyAccountService.delete(familyId, user);
                if (result && user.getFamilyId().equals(familyId))
                    result = Boolean.TRUE;
            } else if (accountId != null) {
                logger.debug(String.format("user %s try to delete family member %d", user.toString(), accountId));
                result = familyAccountService.deleteFamilyMember(accountId, user);

                if (result && user.getId().equals(accountId))
                    result = Boolean.TRUE;
            }

            if (result) {
                user = accountService.queryById(user.getId());
                s.setAttribute("loginAccount", user);
            }

            return list(s);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("/500")
                    .addObject("ex", e);
        }
    }
}
