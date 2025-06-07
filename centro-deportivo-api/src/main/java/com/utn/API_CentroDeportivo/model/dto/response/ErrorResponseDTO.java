package com.utn.API_CentroDeportivo.model.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ErrorResponseDTO {
    private int status;
    private String error;
    private String code;
    private String message;
    private Map<String, String> details;
    private LocalDateTime timestamp;
}
