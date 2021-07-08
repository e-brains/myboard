package com.kye.myboard.service;

import com.kye.myboard.model.Board;
import com.kye.myboard.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     * 글수정
     ******************/
    @Transactional
    public void 글수정(@RequestBody Board reqBoard){

        // 수정하려면 먼저 기존 데이터 영속화를 시켜야 한다.(원래 저장되어 있던 보드내용을 영속화)
        Board board = boardRepository.findById(reqBoard.getId()).orElseThrow(()->{
            return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
        });

        // 클라이언트에서 올라온 수정내용을 셋팅 한다.
        board.setTitle(reqBoard.getTitle());
        board.setContent(reqBoard.getContent());

        boardRepository.save(board);
    }

    /*****************
     * 글목록 조회
     ******************/
    @Transactional
    public List<Board> 글목록조회(){
       return boardRepository.findAll();
    }

    /*****************
     * 글상세 조회
     ******************/
    @Transactional
    public Board 글상세조회(Long id) {
        Board board = boardRepository.findById(id).orElseThrow();
        return board ;
    }

    /*****************
     * 글삭제
     ******************/
    @Transactional
    public void 글삭제(Long id) {
        boardRepository.deleteById(id);
    }

    /*****************
     * 글목록 조회 (스프링부트 페이징처리)
     ******************/
    @Transactional
    public Page<Board> 글목록페이지(Pageable pageable){
        Page<Board> boards = boardRepository.findAll(pageable);
        return boards;
    }

}
