package com.kye.myboard.api;

import com.kye.myboard.dto.ResponseDto;
import com.kye.myboard.model.Board;
import com.kye.myboard.service.BoardService;
import com.kye.myboard.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
//@RequestMapping("/api/board")
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardValidator boardValidator;

    // 글쓰기 저장
    @PostMapping("/api/board/save")
    public ResponseDto<Integer> save(@Valid @RequestBody Board board, BindingResult bindingResult, Authentication authentication) {

        boardValidator.validate(board, bindingResult);

        if (bindingResult.hasErrors()){
            return new ResponseDto<Integer>(HttpStatus.BAD_REQUEST.value(), -1) ;
        }

        // 클라이언트에서 올라온 사용자 인증정보를 믿지 말고 서버에서 가지고 있는 인증자료를 최대한 활용해서
        // 사용자 정보를 만들어 준다. (시큐리티 정보 활용 Authentication)
        String username = authentication.getName();

        boardService.글저장(username, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1) ;
    }

    // 글수정 저장
    @PutMapping("/api/board/update")
    public ResponseDto<Integer> update(@Valid @RequestBody Board board, BindingResult bindingResult) {

        boardValidator.validate(board, bindingResult);

        if (bindingResult.hasErrors()){
            return new ResponseDto<Integer>(HttpStatus.BAD_REQUEST.value(), -1) ;
        }

        boardService.글수정(board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1) ;
    }

    // 글 삭제
    @DeleteMapping("/api/board/deleteById/{id}")
    public ResponseDto<Integer> delete(@PathVariable Long id){

        boardService.글삭제(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1) ;
    }


}
