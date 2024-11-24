package com.blog.userCrud.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamesDetailsRequestDto {

	@NotBlank(message = "Gamename is required!")
	private String name;
	
	@NotBlank(message = "Gamepicture is required!")
	private String picture;

	@NotBlank(message = "Gamedetail is required!")
	private String detail;

	@NotBlank(message = "Gamesales is required!")
	private String salesVolume;

	@NotBlank(message = "Gamescore is required!")
	private String score;
}
