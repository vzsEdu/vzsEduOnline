package com.civil.interfaces.web.exam.display.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by byao on 2/27/16.
 */
@Data
public class ExamDisplayDto {
	private String examTitle;
	private List<ExamDisplayOptionDto> examDisplayOptionDtos;

}
