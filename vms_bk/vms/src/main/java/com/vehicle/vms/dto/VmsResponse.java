package com.vehicle.vms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VmsResponse {
    private Integer status;
    private ResponseEntity statusCode;
    private String message;
    public Object data;
}
