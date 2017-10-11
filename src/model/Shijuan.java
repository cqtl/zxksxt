package model;

/**
 * Shijuan entity. @author MyEclipse Persistence Tools
 */

public class Shijuan implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String subjectIds;
	private String createdby;
	private String score;

	// Constructors

	/** default constructor */
	public Shijuan() {
	}

	/** minimal constructor */
	public Shijuan(String name, String subjectIds) {
		this.name = name;
		this.subjectIds = subjectIds;
	}

	/** full constructor */
	public Shijuan(String name, String subjectIds, String createdby,
			String score) {
		this.name = name;
		this.subjectIds = subjectIds;
		this.createdby = createdby;
		this.score = score;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubjectIds() {
		return this.subjectIds;
	}

	public void setSubjectIds(String subjectIds) {
		this.subjectIds = subjectIds;
	}

	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getScore() {
		return this.score;
	}

	public void setScore(String score) {
		this.score = score;
	}

}