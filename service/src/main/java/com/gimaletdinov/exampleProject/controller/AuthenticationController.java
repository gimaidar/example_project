package com.gimaletdinov.exampleProject.controller;

import com.gimaletdinov.exampleProject.dao.AuthorizationUserRepository;
import com.gimaletdinov.exampleProject.dto.request.AuthenticationRequestDto;
import com.gimaletdinov.exampleProject.dto.response.ObjectErrorResponseDto;
import com.gimaletdinov.exampleProject.dto.response.ObjectSuccessResponseDto;
import com.gimaletdinov.exampleProject.model.security.AuthorizationUser;
import com.gimaletdinov.exampleProject.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private AuthorizationUserRepository authorizationUserRepository;
    private JwtTokenProvider jwtTokenProvider;

    public AuthenticationController(AuthenticationManager authenticationManager, AuthorizationUserRepository authorizationUserRepository, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.authorizationUserRepository = authorizationUserRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public Object authenticate(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword()));
            AuthorizationUser user = authorizationUserRepository.findByEmail(requestDto.getEmail()).orElseThrow(
                    () -> new UsernameNotFoundException("User doesn't exist"));
            String token = jwtTokenProvider.createToken(requestDto.getEmail(), user.getRole().name());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", requestDto.getEmail());
            response.put("token", token);
            return response;
        } catch (AuthenticationException e) {
            ObjectErrorResponseDto responseDto = new ObjectErrorResponseDto();
            responseDto.setError("Invalid email/password combination");
            return responseDto;
        }
    }

    @PostMapping("/logout")
    public ObjectSuccessResponseDto logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);

        ObjectSuccessResponseDto responseDto = new ObjectSuccessResponseDto();
        responseDto.setResult("Logout completed");
        return responseDto;
    }
}
