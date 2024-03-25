package com.wj.board.dto;

import com.wj.board.entity.BoardEntity;
import com.wj.board.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

public class BoardDTO {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class BoardListDTO{
        private Long id;
        private String title;
        private int hit;
        private LocalDateTime createdTime;
        private String username;
        public static BoardListDTO fromEntity(BoardEntity boardEntity){
            return BoardListDTO.builder()
                    .id(boardEntity.getId())
                    .title(boardEntity.getTitle())
                    .hit(boardEntity.getHit())
                    .createdTime(boardEntity.getCreatedTime())
                    .username(boardEntity.getUserEntity().getUsername())
                    .build();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class BoardDetailDTO{
        private Long id;
        private String title;
        private String content;
        private LocalDateTime createdTime;
        private String username;
        public static BoardDetailDTO fromEntity(BoardEntity boardEntity){
            return BoardDetailDTO.builder()
                    .id(boardEntity.getId())
                    .title(boardEntity.getTitle())
                    .content(boardEntity.getContent())
                    .createdTime(boardEntity.getCreatedTime())
                    .username(boardEntity.getUserEntity().getUsername())
                    .build();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class BoardWriteDTO{
        @NotBlank(message="제목을 입력하세요.")
        private String title;
        @NotBlank(message="내용을 입력하세요.")
        private String content;
        private LocalDateTime createdTime;
        private UserEntity userEntity;
        public BoardEntity toEntity() {
            return BoardEntity.builder()
                    .title(title)
                    .content(content)
                    .createdTime(createdTime)
                    .userEntity(userEntity)
                    .build();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class BoardUpdateDTO{
        private Long id;
        @NotBlank(message="제목을 입력하세요.")
        private String title;
        @NotBlank(message="내용을 입력하세요.")
        private String content;
        private int hit;
        private UserEntity userEntity;
        public BoardEntity toEntity() {
            return BoardEntity.builder()
                    .id(id)
                    .title(title)
                    .content(content)
                    .hit(hit)
                    .userEntity(userEntity)
                    .build();
        }
        public static BoardDTO.BoardUpdateDTO fromEntity(BoardEntity boardEntity){
            return BoardUpdateDTO.builder()
                    .id(boardEntity.getId())
                    .title(boardEntity.getTitle())
                    .content(boardEntity.getContent())
                    .hit(boardEntity.getHit())
                    .userEntity(boardEntity.getUserEntity())
                    .build();
        }
    }
}
