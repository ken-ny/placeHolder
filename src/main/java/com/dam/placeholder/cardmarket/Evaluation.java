package com.dam.placeholder.cardmarket;

import java.util.List;

public class Evaluation {

	private Integer evaluationGrade;
	private Integer itemDescription;
	private Integer packaging;
	private Integer speed;
	private String comment;
	private List<String> complaint;

	public Integer getEvaluationGrade() {
		return evaluationGrade;
	}

	public void setEvaluationGrade(Integer evaluationGrade) {
		this.evaluationGrade = evaluationGrade;
	}

	public Integer getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(Integer itemDescription) {
		this.itemDescription = itemDescription;
	}

	public Integer getPackaging() {
		return packaging;
	}

	public void setPackaging(Integer packaging) {
		this.packaging = packaging;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<String> getComplaint() {
		return complaint;
	}

	public void setComplaint(List<String> complaint) {
		this.complaint = complaint;
	}

}
