package model;

/**
 * StudentShijuan entity. @author MyEclipse Persistence Tools
 */

public class StudentShijuan implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer subjectId;
	private Integer userId;
	private Integer ischecked;
	private String answer;
	private Float score;
	private String shijuanname;
	private String pingyu;

	// Constructors

	/** default constructor */
	public StudentShijuan() {
	}

	/** full constructor */
	public StudentShijuan(Integer subjectId, Integer userId, Integer ischecked,
			String answer, Float score, String shijuanname, String pingyu) {
		this.subjectId = subjectId;
		this.userId = userId;
		this.ischecked = ischecked;
		this.answer = answer;
		this.score = score;
		this.shijuanname = shijuanname;
		this.pingyu = pingyu;
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

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getIschecked() {
		return this.ischecked;
	}

	public void setIschecked(Integer ischecked) {
		this.ischecked = ischecked;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Float getScore() {
		return this.score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public String getShijuanname() {
		return this.shijuanname;
	}

	public void setShijuanname(String shijuanname) {
		this.shijuanname = shijuanname;
	}

	public String getPingyu() {
		return this.pingyu;
	}

	public void setPingyu(String pingyu) {
		this.pingyu = pingyu;
	}

}