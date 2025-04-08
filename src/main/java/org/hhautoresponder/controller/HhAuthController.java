package org.hhautoresponder.controller;

import lombok.RequiredArgsConstructor;
import org.hhautoresponder.dto.user.OAuthResponse;
import org.hhautoresponder.dto.user.UserDto;
import org.hhautoresponder.service.HhAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class HhAuthController {
    private final HhAuthService hhAuthService;

    @GetMapping("/callback")
    public ResponseEntity<OAuthResponse> handleCallback(@RequestParam String code) {
        var response = hhAuthService.getAuthToken(code);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/token")
    public ResponseEntity<OAuthResponse> getToken(@RequestParam String refreshToken) {
        var response = hhAuthService.refreshToken(refreshToken);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/user")
    public UserDto getUser(@RequestParam Long userId) {
        return hhAuthService.getUser(userId);
    }
}