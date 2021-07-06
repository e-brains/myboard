package com.kye.myboard.api;

import com.kye.myboard.dto.ResponseDto;
import com.kye.myboard.model.Board;
import com.kye.myboard.repository.BoardRepository;
import com.kye.myboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    // 글쓰기 저장
    @PostMapping("/board/save")
    public ResponseDto<Integer> save(@RequestBody Board board) {
        boardService.글저장(board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1) ;
    }
}
