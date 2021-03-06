package com.github.user.manager.security.controller.common.impl;

import com.github.user.manager.security.constant.RetrieveType;
import com.github.user.manager.security.controller.common.IUserPublicController;
import com.github.user.manager.security.pojo.dto.PasswordResetDTO;
import com.github.user.manager.security.pojo.dto.PasswordRetrieveDTO;
import com.github.user.manager.security.pojo.dto.UserRegisterDTO;
import com.github.user.manager.security.pojo.vo.ResultVO;
import com.github.user.manager.security.pojo.vo.RetrieveMessageVO;
import com.github.user.manager.security.service.common.IUserPublicService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@PreAuthorize("permitAll()")
@RequestMapping("/public")
@RequiredArgsConstructor
public class UserPublicControllerImpl implements IUserPublicController<UserRegisterDTO> {

    private final IUserPublicService service;

    @GetMapping("index")
    public ResultVO<String> index() {
        return ResultVO.success("index");
    }

    @Override
    @PostMapping("/register")
    public ResultVO<Void> register(@RequestBody @Validated UserRegisterDTO register) {
        return ResultVO.success(service.register(register));
    }

    @Override
    @PostMapping("/password/retrieve/{type}")
    public ResultVO<RetrieveMessageVO> retrievePassword(@PathVariable @RetrieveType String type, @RequestBody PasswordRetrieveDTO retrieve) {
        return ResultVO.success(service.retrievePassword(type, retrieve));
    }

    @Override
    @PutMapping("/password/reset/{randomString}")
    public ResultVO<Void> resetPassword(@PathVariable String randomString, @RequestBody PasswordResetDTO resetPassword) {
        return null;
    }
}
