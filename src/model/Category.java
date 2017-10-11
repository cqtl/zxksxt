package model;

/**
 * Category entity. @author MyEclipse Persistence Tools
 */

public class Category implements java.io.Serializable {

	// Fields

	private Integer id;
	private String module;
	private String link;
	private String classA;
	private String classB;
	private String modulename;

	// Constructors

	/** default constructor */
	public Category() {
	}

	/** full constructor */
	public Category(String module, String link, String classA, String classB,
			String modulename) {
		this.module = module;
		this.link = link;
		this.classA = classA;
		this.classB = classB;
		this.modulename = modulename;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getClassA() {
		return this.classA;
	}

	public void setClassA(String classA) {
		this.classA = classA;
	}

	public String getClassB() {
		return this.classB;
	}

	public void setClassB(String classB) {
		this.classB = classB;
	}

	public String getModulename() {
		return this.modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

}