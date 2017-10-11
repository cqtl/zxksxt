package model;

/**
 * Answers entity. @author MyEclipse Persistence Tools
 */

public class Answers implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer subjectId;
	private String xuanxiang;
	private String answer;
	private String xuanxiang1;
	private String xuanxiang2;
	private String xuanxiang3;
	private String xuanxiang4;
	private String xuanxiang5;
	private String xuanxiang6;
	private String xuanxiang7;
	private String xuanxiang8;
	private String xuanxiang9;
	private String xuanxiang10;

	// Constructors

	/** default constructor */
	public Answers() {
	}

	/** minimal constructor */
	public Answers(Integer subjectId, String answer) {
		this.subjectId = subjectId;
		this.answer = answer;
	}

	/** full constructor */
	public Answers(Integer subjectId, String xuanxiang, String answer,
			String xuanxiang1, String xuanxiang2, String xuanxiang3,
			String xuanxiang4, String xuanxiang5, String xuanxiang6,
			String xuanxiang7, String xuanxiang8, String xuanxiang9,
			String xuanxiang10) {
		this.subjectId = subjectId;
		this.xuanxiang = xuanxiang;
		this.answer = answer;
		this.xuanxiang1 = xuanxiang1;
		this.xuanxiang2 = xuanxiang2;
		this.xuanxiang3 = xuanxiang3;
		this.xuanxiang4 = xuanxiang4;
		this.xuanxiang5 = xuanxiang5;
		this.xuanxiang6 = xuanxiang6;
		this.xuanxiang7 = xuanxiang7;
		this.xuanxiang8 = xuanxiang8;
		this.xuanxiang9 = xuanxiang9;
		this.xuanxiang10 = xuanxiang10;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String getXuanxiang() {
		return this.xuanxiang;
	}

	public void setXuanxiang(String xuanxiang) {
		this.xuanxiang = xuanxiang;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getXuanxiang1() {
		return this.xuanxiang1;
	}

	public void setXuanxiang1(String xuanxiang1) {
		this.xuanxiang1 = xuanxiang1;
	}

	public String getXuanxiang2() {
		return this.xuanxiang2;
	}

	public void setXuanxiang2(String xuanxiang2) {
		this.xuanxiang2 = xuanxiang2;
	}

	public String getXuanxiang3() {
		return this.xuanxiang3;
	}

	public void setXuanxiang3(String xuanxiang3) {
		this.xuanxiang3 = xuanxiang3;
	}

	public String getXuanxiang4() {
		return this.xuanxiang4;
	}

	public void setXuanxiang4(String xuanxiang4) {
		this.xuanxiang4 = xuanxiang4;
	}

	public String getXuanxiang5() {
		return this.xuanxiang5;
	}

	public void setXuanxiang5(String xuanxiang5) {
		this.xuanxiang5 = xuanxiang5;
	}

	public String getXuanxiang6() {
		return this.xuanxiang6;
	}

	public void setXuanxiang6(String xuanxiang6) {
		this.xuanxiang6 = xuanxiang6;
	}

	public String getXuanxiang7() {
		return this.xuanxiang7;
	}

	public void setXuanxiang7(String xuanxiang7) {
		this.xuanxiang7 = xuanxiang7;
	}

	public String getXuanxiang8() {
		return this.xuanxiang8;
	}

	public void setXuanxiang8(String xuanxiang8) {
		this.xuanxiang8 = xuanxiang8;
	}

	public String getXuanxiang9() {
		return this.xuanxiang9;
	}

	public void setXuanxiang9(String xuanxiang9) {
		this.xuanxiang9 = xuanxiang9;
	}

	public String getXuanxiang10() {
		return this.xuanxiang10;
	}

	public void setXuanxiang10(String xuanxiang10) {
		this.xuanxiang10 = xuanxiang10;
	}

}