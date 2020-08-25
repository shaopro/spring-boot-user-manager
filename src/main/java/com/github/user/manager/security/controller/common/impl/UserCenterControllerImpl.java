package com.github.user.manager.security.controller.common.impl;

import com.github.user.manager.security.controller.common.IUserCenterController;
import com.github.user.manager.security.pojo.dto.ChangePasswordDTO;
import com.github.user.manager.security.pojo.dto.SystemUserDTO;
import com.github.user.manager.security.pojo.vo.ISystemUserVO;
import com.github.user.manager.security.pojo.vo.ResultVO;
import com.github.user.manager.security.service.common.IUserCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 石少东
 * @date 2020-08-24 16:10
 * @since 1.0
 */



@RestController
@RequestMapping("/user/center")
@RequiredArgsConstructor
public class UserCenterControllerImpl implements IUserCenterController<ISystemUserVO, SystemUserDTO<Void>,Void> {

    private final IUserCenterService service;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    @Override
    public ResultVO<ISystemUserVO> me() {
        return ResultVO.success(service.me());
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/info")
    @Override
    public ResultVO<Void> updateUserInfo(@RequestBody SystemUserDTO<Void> systemUser) {
        return ResultVO.success(service.updateUserInfo(systemUser));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/password/change")
    @Override
    public ResultVO<Void> changePassword(@RequestBody @Validated ChangePasswordDTO changePassword) {
        return ResultVO.success(service.changePassword(changePassword));
    }

}