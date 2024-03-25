package com.wj.board.dto;

import com.wj.board.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

public class UserDTO {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserRegisterDTO {
        @NotBlank(message="아이디를 입력하세요.")
        private String username;
        @NotBlank(message="비밀번호를 입력하세요.")
        private String password1;
        @NotBlank(message="비밀번호를 입력하세요.")
        private String password2;
        private String role;

        public UserEntity toEntity() {
            return UserEntity.builder()
                    .username(username)
                    .password(password1)
                    .role(role)
                    .build();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class UserInfoDTO {
        private String username;
        private String password;
        public static UserDTO.UserInfoDTO fromEntity(UserEntity userEntity){
            return UserDTO.UserInfoDTO.builder()
                    .username(userEntity.getUsername())
                    .password(userEntity.getPassword())
                    .build();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserChangePasswordDTO {
        private Long id;
        private String username;
        private String role;

        @NotBlank(message="새로운 비밀번호를 입력하세요.")
        private String newPassword1;
        @NotBlank(message="새로운 비밀번호를 입력하세요.")
        private String newPassword2;

        public UserEntity toEntity() {
            return UserEntity.builder()
                    .id(id)
                    .username(username)
                    .password(newPassword1)
                    .role(role)
                    .build();
        }
        public static UserDTO.UserChangePasswordDTO fromEntity(UserEntity userEntity){
            return UserDTO.UserChangePasswordDTO.builder()
                    .id(userEntity.getId())
                    .username(userEntity.getUsername())
                    .role(userEntity.getRole())
                    .build();
        }
    }
}
