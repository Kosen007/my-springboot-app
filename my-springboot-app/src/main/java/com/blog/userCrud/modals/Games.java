package com.blog.userCrud.modals;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "games")
public class Games {

	@Id
	private String id;

	private String name;

	private String picture;

	private String detail;

	private String salesVolume;

	private String score;

	private LocalDateTime regDateTime;

	public Games(String name, String picture, String detail, String salesVolume, String score,
			LocalDateTime regDateTime) {
		this.name = name;
		this.picture = picture;
		this.detail = detail;
		this.salesVolume = salesVolume;
		this.score = score;
		this.regDateTime = regDateTime;
	}
}
