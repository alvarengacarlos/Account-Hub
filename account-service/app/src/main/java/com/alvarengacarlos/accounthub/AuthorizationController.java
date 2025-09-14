package com.alvarengacarlos.accounthub;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@RestController
public class AuthorizationController {
    private final String clientId = "a8fc6aeb9527978ba6b5";
    private final String code = "cef50bba5265";

    @GetMapping("/")
    public void health() {
    }

    @GetMapping("/authorize")
    public RedirectView authorize(
            @RequestParam(required = true, name = "response_type") String responseType,
            @RequestParam(required = true) String scope,
            @RequestParam(required = true, name = "client_id") String clientId,
            @RequestParam(required = true, name = "redirect_uri") String redirectUri,
            @RequestParam(required = true) String state,
            @RequestParam(required = true) String prompt) {
        String error = "invalid_request";
        StringBuilder errorMessage = new StringBuilder();
        if (!responseType.equals("code")) {
            errorMessage.append("Unsupported response type. ");
        }

        if (!scope.contains("openid")) {
            errorMessage.append("Unsupported scope.");
        }

        if (!clientId.equals(this.clientId)) {
            errorMessage.append("Client id not found. ");
        }

        if (!prompt.equals(("login"))) {
            errorMessage.append("Unsupported prompt. ");
        }

        if (!errorMessage.isEmpty()) {
            return new RedirectView(redirectUri + "?error=" + error +
                    "&error_description="
                    + errorMessage.toString() + "&state=" + state);
        }

        StringBuilder params = new StringBuilder();
        params.append("code=" + code + "&");
        params.append("redirect_uri=" + redirectUri + "&");
        params.append("state=" + state);

        return new RedirectView("https://localhost/account-web/index.html?" + params.toString());
    }

    @CrossOrigin(origins = "https://localhost")
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDto dto) {
        String email = "johndoe@email.com";
        String password = "John@123";
        if (!dto.email().equals(email) || !dto.password().equals(password)) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials"));
        }

        return ResponseEntity.status(200).body(Map.of("message", "Success"));
    }

    record LoginDto(
            @NotBlank @Email String email,
            @NotBlank String password) {

    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Map<String, String>> token(
            @RequestParam(required = true, name = "grant_type") String grantType,
            @RequestParam(required = true) String code,
            @RequestParam(required = true, name = "client_id") String clientId,
            @RequestParam(required = true, name = "client_secret") String clientSecret) {

        StringBuilder errorMessage = new StringBuilder();
        if (!grantType.equals("authorization_code")) {
            errorMessage.append("Invalid authorization_code. ");
        }

        if (!code.equals(this.code)) {
            errorMessage.append("Invalid code");
        }

        if (!clientId.equals(this.clientId)) {
            errorMessage.append("Invalid client_id");
        }

        if (!clientSecret.equals("197e97fd6af444db975c54ae6c714f55")) {
            errorMessage.append("Invalid client_secret");
        }

        if (!errorMessage.isEmpty()) {
            ResponseEntity.status(400).body(Map.of(
                    "error", "invalid_request",
                    "error_description", errorMessage.toString()));
        }

        Map<String, String> body = Map.of(
                "token_type", "Bearer",
                "id_token", "",
                "access_token", "",
                "expires_in", "",
                "refresh_token", "");
        return ResponseEntity.status(200).body(body);
    }
}
