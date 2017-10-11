package model;

/**
 * StudentCourse entity. @author MyEclipse Persistence Tools
 */

public class StudentCourse implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer number;
	private String course;
	private Float score;
	private Integer ispass;

	// Constructors

	/** default constructor */
	public StudentCourse() {
	}

	/** full constructor */
	public StudentCourse(Integer number, String course, Float score,
			Integer ispass) {
		this.number = number;
		this.course = course;
		this.score = score;
		this.ispass = ispass;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getCourse() {
		return this.course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Float getScore() {
		return this.score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Integer getIspass() {
		return this.ispass;
	}

	public void setIspass(Integer ispass) {
		this.ispass = ispass;
	}

}