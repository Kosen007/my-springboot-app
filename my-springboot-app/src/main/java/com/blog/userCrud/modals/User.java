package com.blog.userCrud.modals;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {
	
	@Id
	private String id;
	
	@NotBlank
    @Size(min=3, max = 20)
	private String name;
	
	@NotBlank
	private String password;
	
	private LocalDateTime regDateTime;
	
	public User(String name, String password, LocalDateTime regDateTime) {
		this.name = name;
		this.password = password;
		this.regDateTime = regDateTime;
	}
}
