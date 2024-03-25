package com.wj.board.validator;

import com.wj.board.dto.UserDTO;
import com.wj.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/*--- 기본으로 제공되는 유효성 검사 외 Custom한 유효성 검사를 위한 클래스 ---*/
@RequiredArgsConstructor
@Component
public class CustomValidators {
    @RequiredArgsConstructor
    @Component
    public static class CheckUsernameValidator extends AbstractValidator<UserDTO.UserRegisterDTO>{
        private final UserRepository userRepository;
        @Override
        protected void doValidate(UserDTO.UserRegisterDTO userRegisterDTO, Errors errors) {
            if(userRepository.existsByUsername(userRegisterDTO.getUsername())) {
                errors.rejectValue("username", "아이디 중복 오류", "이미 사용중인 아이디 입니다.");
            }
        }
    }

    @RequiredArgsConstructor
    @Component
    public static class CheckPasswordValidator extends AbstractValidator<UserDTO.UserRegisterDTO>{
        @Override
        protected void doValidate(UserDTO.UserRegisterDTO userRegisterDTO, Errors errors) {
            if(!userRegisterDTO.getPassword1().equals(userRegisterDTO.getPassword2())) {
                errors.rejectValue("password2", "비밀번호 일치 오류", "2개의 비밀번호가 일치하지 않습니다.");
            }
        }
    }

    @RequiredArgsConstructor
    @Component
    public static class ChangePasswordValidator extends AbstractValidator<UserDTO.UserChangePasswordDTO>{
        @Override
        protected void doValidate(UserDTO.UserChangePasswordDTO userChangePasswordDTO ,Errors errors) {
            if(!userChangePasswordDTO.getNewPassword1().equals(userChangePasswordDTO.getNewPassword2())){
                errors.rejectValue("newPassword2", "비밀번호 일치 오류", "2개의 비밀번호가 일치하지 않습니다.");
            }
        }
    }
}
