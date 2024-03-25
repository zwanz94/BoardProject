package com.wj.board.controller;

import com.wj.board.dto.UserDTO;
import com.wj.board.service.UserService;
import com.wj.board.validator.CustomValidators;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    /*--- CustomValidator를 통해 사용자가 원하는 유효성 검사를 위한 필드 ---*/
    private final CustomValidators.CheckUsernameValidator checkUsernameValidator;
    private final CustomValidators.CheckPasswordValidator checkPasswordValidator;
    private final CustomValidators.ChangePasswordValidator changePasswordValidator;

    /*--- userRegisterDTO의 유효성 검사를 위한 필드 ---*/
    @InitBinder("userRegisterDTO")
    public void UserRegisterDTOValidatorBinder(WebDataBinder binder){
        binder.addValidators(checkUsernameValidator);
        binder.addValidators(checkPasswordValidator);
    }

    /*--- userChangePasswordDTO의 유효성 검사를 위한 필드 ---*/
    @InitBinder("userChangePasswordDTO")
    public void UserChangePasswordDTOValidatorBinder(WebDataBinder binder){
        binder.addValidators(changePasswordValidator);
    }

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @GetMapping("/register")
    public String register(Model model){
        /*--- Validation을 위한 빈 객체 생성 ---*/
        model.addAttribute("userRegisterDTO", new UserDTO.UserRegisterDTO());
        return "user/register";
    }

    @PostMapping("/register")
    public String registerForm(@Valid @ModelAttribute("userRegisterDTO") UserDTO.UserRegisterDTO userRegisterDTO,
                               Errors errors, Model model){
        /*--- Validation 로직 ---*/
        if (errors.hasErrors()) {
            /*--- 입력값 유지를 위한 객체 생성 ---*/
            model.addAttribute("userRegisterDTO",userRegisterDTO);
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for(String key : validatorResult.keySet()){
                model.addAttribute(key, validatorResult.get(key));
            }
            return "user/register";
        }
        userService.register(userRegisterDTO);
        model.addAttribute("message","회원가입이 완료됐습니다.");
        model.addAttribute("url","/users/login");
        return "message";
    }

    @GetMapping("/info")
    public String info(Authentication authentication, Model model){ // Authentication = 현재 접속된 사용자의 정보를 사용하기 위함
        UserDTO.UserInfoDTO userInfoDTO = userService.info(authentication.getName());
        model.addAttribute("userInfoDTO", userInfoDTO);
        return "user/info";
    }

    @GetMapping("/delete")
    public String delete(Authentication authentication, Model model, HttpSession httpSession){
        userService.delete(authentication.getName());
        /*--- 회원 탈퇴 후 세션 초기화(로그아웃) ---*/
        httpSession.invalidate();
        model.addAttribute("message","회원탈퇴가 완료됐습니다.");
        model.addAttribute("url","/");
        return "message";
    }

    @GetMapping("/change-password")
    public String changePassword(Authentication authentication, Model model){ // Authentication = 현재 접속된 사용자의 정보를 사용하기 위함
        /*----Validation을 위한 인증 정보를 포함한 객체 생성----*/
        model.addAttribute("userChangePasswordDTO",
                userService.findUserForChangePassword(authentication.getName()));
        return "user/changePassword";
    }

    @PostMapping("/change-password")
    public String changePasswordForm(@Valid @ModelAttribute("userChangePasswordDTO") UserDTO.UserChangePasswordDTO userChangePasswordDTO,
                                     Errors errors, Model model){
        /*----Validation 로직----*/
        if (errors.hasErrors()) {
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for(String key : validatorResult.keySet()){
                model.addAttribute(key, validatorResult.get(key));
            }
            return "user/changePassword";
        }
        userService.changePassword(userChangePasswordDTO);
        model.addAttribute("message","비밀번호가 변경됐습니다.");
        model.addAttribute("url","/users/info");
        return "message";
    }
}
