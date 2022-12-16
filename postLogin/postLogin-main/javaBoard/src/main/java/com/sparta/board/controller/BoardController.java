package com.sparta.board.controller;

import com.sparta.board.dto.BoardRequestDto;
import com.sparta.board.dto.BoardResponseDto;
import com.sparta.board.entity.Board;
import com.sparta.board.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/api/posts") //조회하기
    public List<BoardResponseDto> getBoards(){
        return boardService.getBoards();
    }

    @GetMapping("/api/posts/{id}") // 특정 글 조회하기
    public BoardResponseDto selectBoards(@PathVariable("id") Long id){return boardService.selectBoard(id);}


    @PostMapping("/api/posts") //생성하기
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto, HttpServletRequest request) {
        return boardService.createBoard(requestDto ,request);
    }

    @PutMapping("/api/posts/{id}") //변경하기
    public BoardResponseDto updateBoard(@PathVariable long id, @RequestBody BoardRequestDto requestDto, HttpServletRequest request) {
        return boardService.update(id , requestDto, request);
    }

    @DeleteMapping("/api/posts/{id}")
    public Long deleteBoard(@PathVariable Long id,HttpServletRequest request) {
        return boardService.deleteBoard(id, request);
    }
}


