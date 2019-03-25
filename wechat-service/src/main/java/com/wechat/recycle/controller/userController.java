package com.wechat.recycle.controller;

import com.github.pagehelper.PageInfo;
import com.wechat.recycle.common.utils.Result;
import com.wechat.recycle.common.utils.ResultUtil;
import com.wechat.recycle.common.utils.StatusCodeEnum;
import com.wechat.recycle.entity.Admin;
import com.wechat.recycle.entity.User;
import com.wechat.recycle.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class userController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public Result getAllUser(Integer pageNum, Integer pageSize, String nickName) {
        PageInfo<User> userPageInfo = userService.selectAllUser(pageNum, pageSize, nickName);
        return ResultUtil.pageResult(userPageInfo);
    }

    @RequestMapping(value = "/changeUserRole", method = RequestMethod.GET)
    public Result changeUserRole(String openId, String roleType) {
        User user = new User();
        user.setOpenId(openId);
        user.setRoleType(roleType);
        int count = userService.updateUser(user);
        if (count <= 0) return ResultUtil.error("1002","修改用户类别失败");
        return ResultUtil.success();
    }

    @RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
    public Result adminLogin(String mobile, String password) {
        Admin admin = userService.selectAdmin(mobile);
        if (admin == null) return ResultUtil.error("1005","账户不存在！");
        if (password.equals(admin.getPassword())) {
            return ResultUtil.success(admin);
        }
        return ResultUtil.error("1005","密码错误！");
    }

    @RequestMapping(value = "/getAllAdmin", method = RequestMethod.GET)
    public Result getAllAdmin(Integer pageNum, Integer pageSize, String username) {
        PageInfo<Admin> adminPageInfo = userService.selectAllAdmin(pageNum, pageSize, username);
        return ResultUtil.pageResult(adminPageInfo);
    }

    @RequestMapping(value = "/deleteAdmin", method = RequestMethod.GET)
    public Result deleteAdmin(Integer id) {
        int count = userService.deleteOne(id);
        if (count > 0) {
            return ResultUtil.success();
        }
        return ResultUtil.error(StatusCodeEnum.FAILED);
    }

    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    public Result addAdmin(Admin admin) {
        if (StringUtils.isEmpty(admin.getMobile()) || StringUtils.isEmpty(admin.getUsername()) || StringUtils.isEmpty(admin.getPassword())) {
            return ResultUtil.error(StatusCodeEnum.PARAMS_EXCEPTION);
        }
        userService.addAdmin(admin);
        return ResultUtil.error(StatusCodeEnum.FAILED);
    }

}
