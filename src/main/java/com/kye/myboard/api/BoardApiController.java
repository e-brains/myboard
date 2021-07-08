package com.kye.myboard.api;

import com.kye.myboard.dto.ResponseDto;
import com.kye.myboard.model.Board;
import com.kye.myboard.service.BoardService;
import com.kye.myboard.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/board")
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardValidator boardValidator;

    // 글쓰기 저장
    @PostMapping("/save")
    public ResponseDto<Integer> save(@Valid @RequestBody Board board, BindingResult bindingResult) {

        boardValidator.validate(board, bindingResult);

        if (bindingResult.hasErrors()){
            return new ResponseDto<Integer>(HttpStatus.BAD_REQUEST.value(), -1) ;
        }

        boardService.글저장(board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1) ;
    }

    // 글수정 저장
    @PutMapping("/update")
    public ResponseDto<Integer> update(@Valid @RequestBody Board board, BindingResult bindingResult) {

        boardValidator.validate(board, bindingResult);

        if (bindingResult.hasErrors()){
            return new ResponseDto<Integer>(HttpStatus.BAD_REQUEST.value(), -1) ;
        }

        boardService.글수정(board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1) ;
    }

    // 글 삭제
    @DeleteMapping("/deleteById/{id}")
    public ResponseDto<Integer> delete(@PathVariable Long id){

        boardService.글삭제(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1) ;
    }


}
