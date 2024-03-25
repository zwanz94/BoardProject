package com.wj.board.controller;

import com.wj.board.dto.BoardDTO;
import com.wj.board.service.BoardService;
import com.wj.board.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(@PageableDefault(page=0, size=5, sort="id", direction= Sort.Direction.DESC) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchKeyword,
                       Model model){

        Page<BoardDTO.BoardListDTO> boardListDTO = boardService.list(searchKeyword, pageable);

        /*----검색 결과 없을 경우 처리 로직----*/
        if(!searchKeyword.isEmpty() && !boardListDTO.hasContent()){
            model.addAttribute("message","게시물이 존재하지 않습니다.");
            model.addAttribute("url","/boards/list");
            return "message";
        }

        /*----페이징 처리 로직----*/
        int currentPage = boardListDTO.getPageable().getPageNumber();
        int totalPage = (boardListDTO.getTotalPages() == 0) ? 1 : boardListDTO.getTotalPages();
        int pageGroup = (int) Math.ceil((double) (currentPage + 1) / 5);
        int firstPage = ((pageGroup - 1) * 5) + 1;
        int lastPage = Math.min(totalPage, pageGroup * 5);
        int lastPageGroup = (int) Math.ceil((double) (totalPage) / 5);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("pageGroup", pageGroup);
        model.addAttribute("firstPage", firstPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("lastPageGroup", lastPageGroup);

        model.addAttribute("boardListDTO", boardListDTO);
        return "board/list";
    }

    @GetMapping("/list/{id}")
    public String detail(@PathVariable Long id, Model model){
        /*----조회수 증가----*/
        boardService.updateHit(id);
        BoardDTO.BoardDetailDTO boardDetailDTO = boardService.detail(id);
        model.addAttribute("boardDetailDTO",boardDetailDTO);
        return "board/detail";
    }

    @GetMapping("/write")
    public String write(Model model){
        /*----Validation을 위한 빈 객체 생성----*/
        model.addAttribute("boardWriteDTO",new BoardDTO.BoardWriteDTO());
        return "board/write";
    }

    @PostMapping("/write")
    public String writeForm(@Valid @ModelAttribute("boardWriteDTO") BoardDTO.BoardWriteDTO boardWriteDTO,
                            BindingResult bindingResult, Model model, Authentication authentication){
        /*----Validation 로직----*/
        if (bindingResult.hasErrors()) {
            return "board/write";
        }

        boardService.write(boardWriteDTO, authentication.getName());
        model.addAttribute("message","글 작성이 완료됐습니다.");
        model.addAttribute("url","/boards/list");
        return "message";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,Model model){
        boardService.delete(id);
        model.addAttribute("message","글 삭제가 완료됐습니다.");
        model.addAttribute("url","/boards/list");
        model.addAttribute("id", null);
        return "message";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model){
        BoardDTO.BoardUpdateDTO boardUpdateDTO = boardService.findForUpdate(id);
        /*----Validation을 위한 빈 객체 생성----*/
        model.addAttribute("boardUpdateDTO", boardUpdateDTO);
        return "board/update";
    }

    @PostMapping("update")
    public String updateForm(@Valid @ModelAttribute("boardUpdateDTO") BoardDTO.BoardUpdateDTO boardUpdateDTO,
                             BindingResult bindingResult, Model model, Authentication authentication){
        /*----Validation 로직----*/
        if (bindingResult.hasErrors()) {
            return "board/update";
        }

        boardService.update(boardUpdateDTO, authentication.getName());
        model.addAttribute("message","글 수정이 완료됐습니다.");
        model.addAttribute("url","/boards/list/");
        model.addAttribute("id", boardUpdateDTO.getId());
        return "message";
    }
}
