package com.sparta.board.service;

import com.sparta.board.dto.LoginRequest;
import com.sparta.board.dto.SignupRequest;
import com.sparta.board.entity.User;
import com.sparta.board.jwt.JwtUtil;
import com.sparta.board.rerpository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional
    public User signup(SignupRequest signupRequest) {
        String username = signupRequest.getUsername();
        // 회원 중복 확인
        Optional<User> found = userRepository.findByUsername(username);

        Pattern id_pattern = Pattern.compile("^[a-z0-9]{4,10}$");
        Pattern password_pattern = Pattern.compile("^[A-Za-z0-9]{8,15}$");

        Matcher matcher_id = id_pattern.matcher(signupRequest.getUsername());
        Matcher matcher_password = password_pattern.matcher(signupRequest.getPassword());

        if (!matcher_id.find()){
            throw new IllegalArgumentException("아이디를 확인해주세요.");
        }
        if (!matcher_password.find()){
            throw new IllegalArgumentException("비밀번호를 확인해주세요.");
        }
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }
        User user = new User(signupRequest);
        userRepository.save(user);

        return user;
    }

    @Transactional(readOnly = true)
    public void login(LoginRequest loginRequest, HttpServletResponse response) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // 사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        // 비밀번호 확인
        if(!user.getPassword().equals(password)){
            throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername()));
    }
}