package com.sparta.board.entity;

import com.sparta.board.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String text;
    private String title;

    public Board(BoardRequestDto requestDto,User user) {
        this.text = requestDto.getText();
        this.title = requestDto.getTitle();
        this.user = user;
    }
    public void update(BoardRequestDto boardRequestDto) {
        this.text = boardRequestDto.getText();
        this.title = boardRequestDto.getTitle();
    }



}
