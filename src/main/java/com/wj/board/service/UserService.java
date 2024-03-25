package com.wj.board.service;

import com.wj.board.dto.UserDTO;
import com.wj.board.entity.UserEntity;
import com.wj.board.repository.BoardRepository;
import com.wj.board.repository.CommentRepository;
import com.wj.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final PasswordEncoder passwordEncoder;

    /*--- CustomValidator를 통해 검사된 결과 전달을 위한 메소드 ---*/
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();
        for(FieldError error : errors.getFieldErrors()){
            validatorResult.put(error.getField(), error.getDefaultMessage());
        }
        return validatorResult;
    }

    public void register(UserDTO.UserRegisterDTO userRegisterDTO) {
        /*--- 입력받은 비밀번호를 Encoder를 통해 암호화 후 저장 ---*/
        userRegisterDTO.setPassword1(passwordEncoder.encode(userRegisterDTO.getPassword1()));
        /*--- 일반 사용자의 경우 USER권한 부여 ---*/
        userRegisterDTO.setRole("ROLE_USER");
        /*--- DTO -> Entity ---*/
        UserEntity userEntity = userRegisterDTO.toEntity();
        userRepository.save(userEntity);
    }

    public UserDTO.UserInfoDTO info(String username) {
        /*--- Entity -> DTO ---*/
        return UserDTO.UserInfoDTO.fromEntity(userRepository.findByUsername(username));
    }

    @Transactional
    public void delete(String username) {
        /*--- 삭제하고자 하는 유저가 작성한 댓글 모두 삭제 ---*/
        commentRepository.deleteAllByUserEntity(userRepository.findByUsername(username));

        /*--- 삭제하고자 하는 유저 글의 댓글 모두 삭제 ---*/
        commentRepository.deleteAllByBoardEntity(boardRepository.findByUserEntity(userRepository.findByUsername(username)));

        /*--- 삭제하고자 하는 유저가 작성한 글 모두 삭제 ---*/
        boardRepository.deleteAllByUserEntity(userRepository.findByUsername(username));

        /*--- 유저 삭제 ---*/
        userRepository.deleteByUsername(username);
    }

    public UserDTO.UserChangePasswordDTO findUserForChangePassword(String username) {
        /*--- Entity -> DTO ---*/
        return UserDTO.UserChangePasswordDTO.fromEntity(userRepository.findByUsername(username));
    }

    public void changePassword(UserDTO.UserChangePasswordDTO userChangePasswordDTO) {
        /*--- 입력받은 비밀번호를 Encoder를 통해 암호화 후 저장 ---*/
        userChangePasswordDTO.setNewPassword1(passwordEncoder.encode(userChangePasswordDTO.getNewPassword1()));
        /*--- DTO -> Entity ---*/
        UserEntity userEntity = userChangePasswordDTO.toEntity();
        userRepository.save(userEntity);
    }
}
