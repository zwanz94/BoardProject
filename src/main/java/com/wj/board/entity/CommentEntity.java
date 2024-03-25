package com.wj.board.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name="comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @CreationTimestamp // insert쿼리 발생 시 현재 시간을 값으로 채워서 쿼리 생성
    @Column(updatable = false) // update쿼리 발생 시 관여하지 않는 옵션
    private LocalDateTime createdTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    private BoardEntity boardEntity;

    @ManyToOne(fetch = FetchType.EAGER) // 해당 게시글의 comment list 를 비동기통신으로 한번에 뿌려주기 위함
    @JoinColumn(name="user_id")
    private UserEntity userEntity;
}
