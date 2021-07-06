package com.kye.myboard.controller;

import com.kye.myboard.model.Board;
import com.kye.myboard.repository.BoardRepository;
import com.kye.myboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 글쓰기 화면으로 이동
    @GetMapping("/saveform")
    public String saveform(){
        return "board/saveform";
    }

    // 목록 화면으로 이동
    @GetMapping("/list")
    public String list(Model model){
        List<Board> boards = boardService.글목록조회();
        model.addAttribute("boards", boards);
        return "board/list";
    }


}
