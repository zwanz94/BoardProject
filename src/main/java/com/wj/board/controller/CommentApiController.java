package com.wj.board.controller;

import com.wj.board.dto.CommentDTO;
import com.wj.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/boards")
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("/list/{boardId}/comments")
    public CommentDTO.CommentWriteResponseDTO write(@RequestBody CommentDTO.CommentWriteRequestDTO commentWriteRequestDTO,
                                                    @PathVariable Long boardId,
                                                    Authentication authentication) {
        return commentService.writeComment(commentWriteRequestDTO, boardId, authentication.getName());
    }

    @GetMapping("/list/{boardId}/comments")
    public List<CommentDTO.CommentListDTO> list(@PathVariable Long boardId){
        return commentService.list(boardId);
    }

    @DeleteMapping("/list/{boardId}/comments/{commentId}")
    public Long delete(@PathVariable Long commentId){
        return commentService.deleteComment(commentId);
    }
}
