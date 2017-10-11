package model;

/**
 * TeacherCourse entity. @author MyEclipse Persistence Tools
 */

public class TeacherCourse implements java.io.Serializable {

	// Fields

	private Integer id;
	private String course;
	private String teachername;
	private Integer teacherid;

	// Constructors

	/** default constructor */
	public TeacherCourse() {
	}

	/** full constructor */
	public TeacherCourse(String course, String teachername, Integer teacherid) {
		this.course = course;
		this.teachername = teachername;
		this.teacherid = teacherid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCourse() {
		return this.course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getTeachername() {
		return this.teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	public Integer getTeacherid() {
		return this.teacherid;
	}

	public void setTeacherid(Integer teacherid) {
		this.teacherid = teacherid;
	}

}