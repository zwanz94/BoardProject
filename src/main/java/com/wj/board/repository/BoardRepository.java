package com.wj.board.repository;

import com.wj.board.entity.BoardEntity;
import com.wj.board.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    @Modifying // @Modifying: non-select 쿼리, @Query를 통해 작성된 변경이 일어나는 쿼리를 실행할 때 사용
    @Query(value = "update BoardEntity set hit=hit+1 where id=:id") // JPQL
    void updateHit(@Param("id")Long id); // @Param: @Query에 전달할 변수 지정
    Page<BoardEntity> findByTitleContaining(String title, Pageable pageable);
    void deleteAllByUserEntity(UserEntity userEntity);
    BoardEntity findByUserEntity(UserEntity userEntity);
}
