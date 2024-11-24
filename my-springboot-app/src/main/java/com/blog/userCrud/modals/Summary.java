package com.blog.userCrud.modals;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "summary")
public class Summary {

	@Id
	private String id;

	private String title;

	private String body;

	private String update;

	public Summary(String title, String body, String update) {
		this.title = title;
		this.body = body;
		this.update = update;
	}
}
