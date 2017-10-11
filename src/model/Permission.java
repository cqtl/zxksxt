package model;

/**
 * Permission entity. @author MyEclipse Persistence Tools
 */

public class Permission implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String module;

	// Constructors

	/** default constructor */
	public Permission() {
	}

	/** full constructor */
	public Permission(String name, String module) {
		this.name = name;
		this.module = module;
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

	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

}