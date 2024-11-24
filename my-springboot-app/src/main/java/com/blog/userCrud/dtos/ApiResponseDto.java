package com.blog.userCrud.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto<T> {
    private String status;
//    private String message;
    private T response;
    
//    public ApiResponseDto(String status, T response) {
//    	this.status = status;
//    	this.response = response;
//    }
}