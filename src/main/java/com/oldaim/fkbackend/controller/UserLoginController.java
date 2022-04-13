package com.oldaim.fkbackend.controller;

import com.oldaim.fkbackend.controller.dto.ReissueDto;
import com.oldaim.fkbackend.controller.dto.TokenResponseDto;
import com.oldaim.fkbackend.controller.dto.UserDto;
import com.oldaim.fkbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Log4j2
@RequestMapping("/api/auth")
public class UserLoginController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserDto userDTO){

        String userId = userService.registerUser(userDTO);

        String msg =  userId +" "+ "님이 회원가입 하셨습니다.";

        return ResponseEntity.ok(msg);

    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@Valid @RequestBody UserDto userDTO){


      TokenResponseDto dto = userService.loginUser(userDTO);

      return ResponseEntity.ok(dto);

    }

    @PostMapping("/reissueToken")
    public ResponseEntity<String> reIssueAccessToken(@RequestBody ReissueDto dto) throws RuntimeException {

        log.info("ReIssueDto email="+dto.getUserId()+" refreshToken=" +dto.getRefreshToken());

        String accessToken = userService.reIssueAccessToken(dto);

        return ResponseEntity.ok(accessToken);
    }



}
