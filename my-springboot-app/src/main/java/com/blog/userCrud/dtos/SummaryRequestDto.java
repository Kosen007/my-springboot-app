package com.blog.userCrud.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryRequestDto {

	private String title;
	
	private String body;
	
	private String update;
}
