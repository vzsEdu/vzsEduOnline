package com.civil.interfaces.web.exam.common;

/**
 * Created by byao on 3/1/16.
 */
public interface ExamDisplayConstant {

	String questionHolder = "$space$";
	String questionHolderRegx = "\\$space\\$";

	String placeHolderRegx = "\\$placeholder\\$";
	String examIdRegx = "\\$optionQuestionId\\$";

	String optionInput = "<input type=\"text\" class=\"dac-header-search-input\" name=\"options\" placeholder = \"$placeholder$\"/><input " +
			"type=\"hidden\"  name=\"optionQuestionIds\" value=\"$optionQuestionId$\" />";
}
