package com.flcode.api_user_flcode.infrastructure.rest.Controller;

import com.flcode.api_user_flcode.application.port.in.AuthImportPort;
import com.flcode.api_user_flcode.application.port.in.UserImputPort;
import com.flcode.api_user_flcode.domain.model.LoginResponse;
import com.flcode.api_user_flcode.domain.model.UserListResponse;
import com.flcode.api_user_flcode.domain.model.UserResponse;
import com.flcode.api_user_flcode.infrastructure.model.AuthRequest;
import com.flcode.api_user_flcode.infrastructure.model.UserRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/api/user")
@AllArgsConstructor
@RestController
@Validated
@CrossOrigin
public class UserController {

    private final UserImputPort userImputPort;
    private final AuthImportPort authImportPort;

    @GetMapping("/{id}")
    Mono<UserListResponse> getUserById(@PathVariable Integer id) {
        return userImputPort.findById(id);
    }

    @GetMapping("/all")
    Mono<UserListResponse> getUserAll() {
        return userImputPort.findAll();
    }

    @PostMapping("/save")
    Mono<UserResponse> saveUser(@Valid @RequestBody UserRequest userRequest) {
        return userImputPort.saveUser(userRequest);
    }

    @PutMapping("/update")
    Mono<UserResponse> updateUser(@Valid @RequestBody UserRequest userRequest) {
        return userImputPort.updateUser(userRequest);
    }

    @DeleteMapping("/delete/{id}")
    Mono<UserResponse> deleteUser(@PathVariable Integer id) {
        return userImputPort.deleteById(id);
    }


    @PostMapping("/login")
    Mono<LoginResponse> loginUser(@Valid @RequestBody AuthRequest authRequest) {
        return authImportPort.login(authRequest.getEmail(), authRequest.getPassword());

    }
}