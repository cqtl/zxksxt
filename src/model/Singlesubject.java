package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Singlesubject entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
@Entity
@Table(name="singlesubject")
public class Singlesubject implements java.io.Serializable {

	// Fields

	@Id
	private String s_id;
	@Column()
	private String answer;
	private String subject;

	private String answera;
	private String answerb;
	public String getAnswera() {
		return answera;
	}

	public void setAnswera(String answera) {
		this.answera = answera;
	}

	public String getAnswerb() {
		return answerb;
	}

	public void setAnswerb(String answerb) {
		this.answerb = answerb;
	}

	public String getAnswerc() {
		return answerc;
	}

	public void setAnswerc(String answerc) {
		this.answerc = answerc;
	}

	public String getAnswerd() {
		return answerd;
	}

	public void setAnswerd(String answerd) {
		this.answerd = answerd;
	}

	private String answerc;
	private String answerd;
	// Constructors

	/** default constructor */
	public Singlesubject() {
	}

	/** minimal constructor */
	public Singlesubject(String s_id) {
		this.s_id = s_id;
	}

	/** full constructor */
	public Singlesubject(String s_id, String answer, String subject) {
		this.s_id = s_id;
		this.answer = answer;
		this.subject = subject;
	}

	// Property accessors

	

	

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getS_id() {
		return s_id;
	}

	public void setS_id(String s_id) {
		this.s_id = s_id;
	}

	

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}