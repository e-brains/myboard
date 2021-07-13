package com.kye.myboard.controller;

import com.kye.myboard.model.Board;
import com.kye.myboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 글쓰기 화면으로 이동
    @GetMapping("/saveform")
    public String saveform() {
        return "board/saveform";
    }

    // 목록 화면으로 이동
    @GetMapping("/list")
    public String list(Model model) {
        List<Board> boards = boardService.글목록조회();
        model.addAttribute("boards", boards);
        return "board/list";
    }

    // 목록 화면으로 이동 (페이징 처리)
    // 검색기능 추가 : searchText
    @GetMapping("/listpage")
    public String listPage(Model model, @PageableDefault(size=4, sort = "id",
            direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(required=false, defaultValue="") String searchText) {

        //Page<Board> boards = boardService.글목록페이지(pageable);
        Page<Board> boards = boardService.글검색조회(searchText, pageable); // 제목과 내용을 검색하는 기능 추가

        // 시작 페이지가 음수 또는 1보다 크면 안되기 때문에 최대 크기를 1로 고정
        int startPage = Math.max(1, boards.getPageable().getPageNumber()-3);

        // 끝 페이지는 현재 페이지 보다 작을 수 없으므로 min을 써서 최소 크기 고정
        // boards.getPageable().getPageNumber()이 0부터 이므로 여기서는 +1을 해야 startPage와 동일해 짐
        int endPage = Math.min(boards.getTotalPages(), startPage+3+1);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);

        return "board/listpage";
    }

    // 상세 화면으로 이동
    @GetMapping("/form")
    public String detailform(@RequestParam(required = false) Long id , Model model) {

        // 최초 글쓰기에는 null이기 때문에 form으로 화면을 합쳐서 사용할 경우 발생 할 수 있음
        if (id == null){
            model.addAttribute("board", new Board());
        }else{
            Board board = boardService.글상세조회(id);
            model.addAttribute("board", board);
        }

        return "board/form";
    }

    // Jquery + RestController를 사용하지 않고 여기서 글쓰기 저장을 하는 경우
    // redirect를 사용하여 Controller의 메서드를 바로 호출한다.
    /*
    @PostMapping("/board/save")
    public String save(@RequestBody Board board) {
        boardService.글저장(board);
        return "redirect:/board/list" ;
    }
    */

}
