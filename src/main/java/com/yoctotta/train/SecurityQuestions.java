package com.yoctotta.train;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SecurityQuestions {
	@Id
	@Column(name = "question_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int question_id;
	@Column(name = "questions")
	private String questions;

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}

	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}

}
