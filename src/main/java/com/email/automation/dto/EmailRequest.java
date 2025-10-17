package com.email.automation.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmailRequest {
    private List<String> recipients;
    private String subject;
    private String message;
    private MultipartFile file;

    // getters and setters
}
