package com.wj.board.repository;

import com.wj.board.entity.BoardEntity;
import com.wj.board.entity.CommentEntity;
import com.wj.board.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {


    // select * from comment where board_id=?;
    List<CommentEntity> findAllByBoardEntity(BoardEntity boardEntity);
    void deleteAllByUserEntity(UserEntity userEntity);
    void deleteAllByBoardEntity(BoardEntity boardEntity);
}
