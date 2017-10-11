package model;

/**
 * Subjects entity. @author MyEclipse Persistence Tools
 */

public class Subjects implements java.io.Serializable {

	// Fields

	private Integer id;
	private String subject;
	private String attribute;
	private Integer creatby;
	private String leixing;

	// Constructors

	/** default constructor */
	public Subjects() {
	}

	/** minimal constructor */
	public Subjects(String subject) {
		this.subject = subject;
	}

	/** full constructor */
	public Subjects(String subject, String attribute, Integer creatby,
			String leixing) {
		this.subject = subject;
		this.attribute = attribute;
		this.creatby = creatby;
		this.leixing = leixing;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAttribute() {
		return this.attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public Integer getCreatby() {
		return this.creatby;
	}

	public void setCreatby(Integer creatby) {
		this.creatby = creatby;
	}

	public String getLeixing() {
		return this.leixing;
	}

	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}

}