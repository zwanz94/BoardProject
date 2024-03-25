package com.wj.board.service;

import com.wj.board.dto.CommentDTO;
import com.wj.board.entity.CommentEntity;
import com.wj.board.repository.BoardRepository;
import com.wj.board.repository.CommentRepository;
import com.wj.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    public CommentDTO.CommentWriteResponseDTO writeComment(CommentDTO.CommentWriteRequestDTO commentWriteRequestDTO,
                                                           Long boardId, String username) {
        /*--- 로그인된 유저의 이름으로 userEntity 검색하여 set ---*/
        commentWriteRequestDTO.setUserEntity(userRepository.findByUsername(username));
        /*--- PathVariable 로 받은 boardId 로 boardEntity 검색하여 set ---*/
        commentWriteRequestDTO.setBoardEntity(boardRepository.findById(boardId).get());
        /*--- DTO -> Entity ---*/
        CommentEntity commentEntity = commentRepository.save(commentWriteRequestDTO.toEntity());
        return CommentDTO.CommentWriteResponseDTO.fromEntity(commentEntity);
    }

    public List<CommentDTO.CommentListDTO> list(Long boardId) {
        /*--- PathVariable 로 받은 boardId 로 boardEntity 검색하여 해당 글의 comment 리스트화 ---*/
        return commentRepository.findAllByBoardEntity(boardRepository.findById(boardId).get())
                .stream()
                .map(CommentDTO.CommentListDTO::fromEntity)
                .collect(Collectors.toList());

        /* List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntity(boardRepository.findById(boardId).get());
        List<CommentDTO.CommentListDTO> commentListDTOList =
                commentEntityList.stream()
                        .map(CommentDTO.CommentListDTO::fromEntity)
                        .collect(Collectors.toList());
        return commentListDTOList; */
    }

    public Long deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
        return commentId;
    }
}
