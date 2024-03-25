package com.wj.board.service;

import com.wj.board.dto.BoardDTO;
import com.wj.board.entity.BoardEntity;
import com.wj.board.repository.BoardRepository;
import com.wj.board.repository.CommentRepository;
import com.wj.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    public Page<BoardDTO.BoardListDTO> list(String title, Pageable pageable){
        /*----Entity -> DTO(page)----*/
        return boardRepository.findByTitleContaining(title, pageable).map(BoardDTO.BoardListDTO::fromEntity);
    }

    public BoardDTO.BoardDetailDTO detail(Long id) {
        /*----Entity -> DTO----*/
        return BoardDTO.BoardDetailDTO.fromEntity(boardRepository.findById(id).get());
    }

    public void write(BoardDTO.BoardWriteDTO boardWriteDTO, String username) {
        /*----authentication(userEntity) to boardWriteDTO(userEntity)----*/
        boardWriteDTO.setUserEntity(userRepository.findByUsername(username));
        /*----DTO -> Entity----*/
        BoardEntity boardEntity = boardWriteDTO.toEntity();
        boardRepository.save(boardEntity);
    }

    @Transactional
    public void delete(Long id) {
        /*----삭제하려는 글의 댓글 모두 삭제----*/
        commentRepository.deleteAllByBoardEntity(boardRepository.findById(id).get());
        /*----글 삭제----*/
        boardRepository.deleteById(id);
    }

    @Transactional
    public void updateHit(Long id) {
        boardRepository.updateHit(id);
    }

    public BoardDTO.BoardUpdateDTO findForUpdate(Long id) {
        /*----Entity -> DTO----*/
        return BoardDTO.BoardUpdateDTO.fromEntity(boardRepository.findById(id).get());
    }

    public void update(BoardDTO.BoardUpdateDTO boardUpdateDTO, String username) {
        /*----authentication(userEntity) to boardUpdateDTO(userEntity)----*/
        boardUpdateDTO.setUserEntity(userRepository.findByUsername(username));
        /*----DTO -> Entity----*/
        BoardEntity boardEntity = boardUpdateDTO.toEntity();
        boardRepository.save(boardEntity);
    }
}
