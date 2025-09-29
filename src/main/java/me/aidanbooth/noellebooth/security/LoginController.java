package me.aidanbooth.noellebooth.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String authServerUrl = "https://aidanbooth.dev/api/auth/login";

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody JsonNode credentials) {
        try {
            ((ObjectNode) credentials).put("site", "noellebooth.co.uk");

            // POST request to auth server
            ResponseEntity<JsonNode> authResponse = restTemplate.postForEntity(
                    authServerUrl,
                    credentials,
                    JsonNode.class
            );

            String token = authResponse.getBody().get("token").asText();

            ResponseCookie jwtCookie = ResponseCookie.from("SESSION_ID", token)
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .maxAge(3600)
                    .sameSite("Strict")
                    .build();

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                    .build();

        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(Map.of("message", "Invalid credentials"));
        }
    }

}
