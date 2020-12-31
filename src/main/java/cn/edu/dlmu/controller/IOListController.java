package cn.edu.dlmu.controller;

import cn.edu.dlmu.base.AssertUtil;
import cn.edu.dlmu.pojo.Account;
import cn.edu.dlmu.pojo.IOList;
import cn.edu.dlmu.service.IOListService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/ioList")
public class IOListController {

    private final static Logger logger = LogManager.getLogger(IOListController.class);

    @Autowired
    @Qualifier("iOListServiceImpl")
    private IOListService ioListService;

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //注册自定义的编辑器
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ModelAndView list(HttpSession s) {

        try {
            Account user = (Account) s.getAttribute("loginUser");
            if (user == null || user.getId() == null)
                return new ModelAndView("/login")
                        .addObject("flag", 2);

            logger.debug(String.format("user %d try to list", user.getId()));

            List<IOList> result = ioListService.queryByAccount(user);

            return new ModelAndView("/ioList/list")
                    .addObject("ioLists", result);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("/500")
                    .addObject("ex", e);
        }
    }

    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(Integer id, HttpSession s) {
        try {
            Account user = (Account) s.getAttribute("loginUser");
            if (user == null || user.getId() == null)
                return new ModelAndView("/login")
                        .addObject("flag", 2);

            logger.debug(String.format("user %d try to edit %d", user.getId(), id));

            IOList ioList;
            if (id != null) {
                ioList = ioListService.queryById(id, user);

                AssertUtil.isNull(ioList, "收支项目不存在");
            } else
                ioList = new IOList();

            return new ModelAndView("/ioList/edit")
                    .addObject("ioList", ioList)
                    .addObject("isAdmin", user.getIsAdmin())
                    .addObject("typeMap", IOList.typeMap);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("/500")
                    .addObject("ex", e);
        }
    }

    @RequestMapping(path = "/edit", method = RequestMethod.POST)
    public ModelAndView addOrUpdate(IOList ioList, HttpSession s) {
        try {
            Account user = (Account) s.getAttribute("loginUser");
            if (user == null || user.getId() == null)
                return new ModelAndView("/login")
                        .addObject("flag", 2);

            AssertUtil.isNull(ioList, "收支项目为空");

            if (ioList.getId() != null) {
                logger.debug(String.format("user %d try to update %d: %s", user.getId(), ioList.getId(), ioList.toString()));

                ioListService.update(ioList, user);

            } else {
                logger.debug(String.format("user %d try to add ioList: %s", user.getId(), ioList.toString()));
                ioListService.add(ioList, user);
            }

            return list(s);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("/500")
                    .addObject("ex", e);
        }
    }

    @RequestMapping("/delete")
    public ModelAndView delete(Integer id, HttpSession s) {
        try {
            Account user = (Account) s.getAttribute("loginUser");
            if (user == null || user.getId() == null)
                return new ModelAndView("/login")
                        .addObject("flag", 2);

            logger.debug(String.format("user %d try to delete %d", user.getId(), id));
            ioListService.delete(id, user);

            return list(s);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("/500")
                    .addObject("ex", e);
        }
    }

}
