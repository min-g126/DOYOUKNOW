package com.doyouknow.project.controller;

import com.doyouknow.project.dto.BoardDTO;
import com.doyouknow.project.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("board")
public class BoardWriteController {


    private BoardService boardService;

    public BoardWriteController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("list")
    public String list(Model model) {
        BoardDTO boardDTO = new BoardDTO();
//        List<BoardDTO> boardDTO = boardService.getBoardList(false);
        model.addAttribute("board",boardDTO);
        return "board/list";
    }

    @GetMapping("intro")
    public String intro() {
        return "board/intro";
    }

    @GetMapping("scholarship")
    public String scholarship() {

        return "board/scholarship";
    }

    @GetMapping("write")
    public String write(@RequestParam(defaultValue = "0", required = false) int type,
                        @RequestParam(defaultValue = "0", required = false) int type2) {

        return "board/write";
    }

    @PostMapping("write")
    public String writeOk(BoardDTO newBoard) {
        System.out.println(newBoard);

        boardService.registerMenu(newBoard);

        return "redirect:/board/write";
    }
}
