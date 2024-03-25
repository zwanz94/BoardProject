package com.wj.board.dto;

import com.wj.board.entity.BoardEntity;
import com.wj.board.entity.CommentEntity;
import com.wj.board.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class CommentDTO {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CommentWriteRequestDTO {
        private String content;
        private BoardEntity boardEntity;
        private UserEntity userEntity;

        public CommentEntity toEntity() {
            return CommentEntity.builder()
                    .content(content)
                    .boardEntity(boardEntity)
                    .userEntity(userEntity)
                    .build();
        }
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CommentWriteResponseDTO {
        private Long id;
        private String content;
        private LocalDateTime createdTime;
        private Long boardId;
        private String username;

        public static CommentWriteResponseDTO fromEntity(CommentEntity commentEntity){
            return CommentWriteResponseDTO.builder()
                    .id(commentEntity.getId())
                    .content(commentEntity.getContent())
                    .createdTime(commentEntity.getCreatedTime())
                    .boardId(commentEntity.getBoardEntity().getId())
                    .username(commentEntity.getUserEntity().getUsername())
                    .build();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CommentListDTO {
        private Long id;
        private String content;
        private LocalDateTime createdTime;
        private Long boardId;
        private String username;

        public static CommentDTO.CommentListDTO fromEntity(CommentEntity commentEntity){
            return CommentDTO.CommentListDTO.builder()
                    .id(commentEntity.getId())
                    .content(commentEntity.getContent())
                    .createdTime(commentEntity.getCreatedTime())
                    .boardId(commentEntity.getBoardEntity().getId())
                    .username(commentEntity.getUserEntity().getUsername())
                    .build();
        }
    }
}
