package model;

/**
 * Examination entity. @author MyEclipse Persistence Tools
 */

public class Examination implements java.io.Serializable {

	// Fields

	private Integer id;
	private String examcourse;
	private String examtime;
	private String ctrName;
	private String gjrName;
	private Integer howlong;
	private String shijuanname;
	private Integer state;

	// Constructors

	/** default constructor */
	public Examination() {
	}

	/** minimal constructor */
	public Examination(String examcourse, Integer howlong) {
		this.examcourse = examcourse;
		this.howlong = howlong;
	}

	/** full constructor */
	public Examination(String examcourse, String examtime, String ctrName,
			String gjrName, Integer howlong, String shijuanname, Integer state) {
		this.examcourse = examcourse;
		this.examtime = examtime;
		this.ctrName = ctrName;
		this.gjrName = gjrName;
		this.howlong = howlong;
		this.shijuanname = shijuanname;
		this.state = state;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExamcourse() {
		return this.examcourse;
	}

	public void setExamcourse(String examcourse) {
		this.examcourse = examcourse;
	}

	public String getExamtime() {
		return this.examtime;
	}

	public void setExamtime(String examtime) {
		this.examtime = examtime;
	}

	public String getCtrName() {
		return this.ctrName;
	}

	public void setCtrName(String ctrName) {
		this.ctrName = ctrName;
	}

	public String getGjrName() {
		return this.gjrName;
	}

	public void setGjrName(String gjrName) {
		this.gjrName = gjrName;
	}

	public Integer getHowlong() {
		return this.howlong;
	}

	public void setHowlong(Integer howlong) {
		this.howlong = howlong;
	}

	public String getShijuanname() {
		return this.shijuanname;
	}

	public void setShijuanname(String shijuanname) {
		this.shijuanname = shijuanname;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}