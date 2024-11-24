package com.blog.userCrud.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsRequestDto {

	@NotBlank(message = "Username is required!")
    @Size(min= 3, message = "Username must have atleast 3 characters!")
    @Size(max= 20, message = "Username can have have atmost 20 characters!")
    private String name;
	
	@NotBlank(message = "Password is required!")
	private String password;
}
