package com.kye.myboard.service;

import com.kye.myboard.model.Board;
import com.kye.myboard.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    /*****************
    * 글저장
    ******************/
    @Transactional
    public void 글저장(@RequestBody Board board){
        boardRepository.save(board);
    }

    /*****************
     * 글목록 조회
     ******************/
    @Transactional
    public List<Board> 글목록조회(){
       return boardRepository.findAll();
    }


}
