package com.revengemission.customerservice.controller.backend;

import com.revengemission.customerservice.controller.BaseController;
import com.revengemission.customerservice.domain.GlobalConstant;
import com.revengemission.customerservice.domain.JsonObjects;
import com.revengemission.customerservice.domain.ResponseResult;
import com.revengemission.customerservice.domain.UserAccount;
import com.revengemission.customerservice.service.UserAccountService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/userAccount")
public class MembersController extends BaseController {

    private final org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserAccountService userAccountService;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @GetMapping(value = {"/", ""})
    public String master(Model model,
                         @RequestParam(value = "id", required = false, defaultValue = "1") long id) {
        return "admin/userAccounts";
    }

    @PostMapping(value = "/list")
    @ResponseBody
    public JsonObjects<UserAccount> listObjects(@RequestParam(value = "searchValue", required = false) String searchValue,
                                                @RequestParam(value = "rows", defaultValue = "10") Integer pageSize,
                                                @RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                                                @RequestParam(value = "sidx", defaultValue = "id") String sortField,
                                                @RequestParam(value = "sord", defaultValue = "desc") String sortOrder) {
        return userAccountService.list(pageNum, pageSize, sortField, sortOrder);
    }

    @GetMapping(value = "/details")
    @ResponseBody
    public UserAccount setupDetails(@RequestParam(value = "id") Long id,
                                    @RequestParam(value = "additionalData", required = false) String additionalData) {
        UserAccount object = userAccountService.retrieveById(id);
        object.setAdditionalData(additionalData);
        return object;
    }

    @PostMapping(value = "/details")
    @ResponseBody
    public ResponseResult handlePost(@RequestParam(value = "id", required = false) Long id,
                                     @RequestParam(value = "deleteOperation", required = false, defaultValue = "0") boolean deleteOperation,
                                     @RequestParam(value = "username", required = false) String username,
                                     @RequestParam(value = "memberCard", required = false) String memberCard,
                                     @RequestParam(value = "realName", required = false) String realName,
                                     @RequestParam(value = "introducer", required = false) String introducer,
                                     @RequestParam(value = "address", required = false) String address,
                                     @RequestParam(value = "password", required = false) String password) {

        ResponseResult responseResult = new ResponseResult();

        if (deleteOperation && id != null && id > 0) {
            userAccountService.deleteById(id);
            responseResult.setStatus(GlobalConstant.SUCCESS);
        } else if (id != null && id > 0) {
            UserAccount object = new UserAccount();
            object.setId("" + id);
            object.setUsername(username);
            if (StringUtils.isNotEmpty(password)) {
                object.setPassword(bCryptPasswordEncoder.encode(StringUtils.trim(password)));
            }
            if (StringUtils.isNotEmpty(address)) {
                object.setAddress(address);
            }
            object.setRole(GlobalConstant.ROLE_USER);
            responseResult = userAccountService.updateById(object);
        } else {
            UserAccount object = new UserAccount();
            object.setUsername(username);
            object.setAddress(address);
            object.setPassword(bCryptPasswordEncoder.encode(StringUtils.trim(password)));
            object.setRole(GlobalConstant.ROLE_USER);
             userAccountService.create(object);
            responseResult.setStatus(GlobalConstant.SUCCESS);
        }

        return responseResult;
    }
}
