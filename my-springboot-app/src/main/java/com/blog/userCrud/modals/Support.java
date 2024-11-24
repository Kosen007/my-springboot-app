package com.blog.userCrud.modals;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "qa")
public class Support {

	@Id
	private String id;

	private String question;

	private String answer;

	public Support(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}
}
