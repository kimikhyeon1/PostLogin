package com.sparta.board.entity;

import com.sparta.board.dto.SignupRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;


//    @Column(nullable = false)
//    @Enumerated(value = EnumType.STRING)
//    private UserRoleEnum role;

    public User(SignupRequest signupRequest) {
        this.username = signupRequest.getUsername();
        this.password = signupRequest.getPassword();
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
//        this.role = role;
    }
}