package com.baoxiao.model;
/**
 * Groupuser entity. @author MyEclipse Persistence Tools
 */

public class Groupuser implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String phoneid;
	private String sex;
	private String age;
	private String birth;
	private String address;
	private String insurer;
	private String company;
	private String type;
	private String occupation;
	private String remarks;
	private String usertag;
	private String grouptype;
	private String userid;
	private String lasttime;
	private String nexttime;


	/** full constructor */
	public Groupuser(Integer id, String name, String phoneid, String sex,
			String age, String birth, String address, String insurer,
			String company, String type, String occupation, String remarks,
			String usertag, String grouptype, String userid, String lasttime,
			String nexttime)
	{
		super();
		this.id = id;
		this.name = name;
		this.phoneid = phoneid;
		this.sex = sex;
		this.age = age;
		this.birth = birth;
		this.address = address;
		this.insurer = insurer;
		this.company = company;
		this.type = type;
		this.occupation = occupation;
		this.remarks = remarks;
		this.usertag = usertag;
		this.grouptype = grouptype;
		this.userid = userid;
		this.lasttime = lasttime;
		this.nexttime = nexttime;
	}

	// Constructors

	/** default constructor */
	public Groupuser() {
	}

	/** minimal constructor */
	public Groupuser(Integer id) {
		this.id = id;
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

	public String getPhoneid() {
		return this.phoneid;
	}

	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBirth() {
		return this.birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInsurer() {
		return this.insurer;
	}

	public void setInsurer(String insurer) {
		this.insurer = insurer;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOccupation() {
		return this.occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getUsertag() {
		return this.usertag;
	}

	public void setUsertag(String usertag) {
		this.usertag = usertag;
	}

	public String getGrouptype() {
		return this.grouptype;
	}

	public void setGrouptype(String grouptype) {
		this.grouptype = grouptype;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getLasttime()
	{
		return lasttime;
	}

	public void setLasttime(String lasttime)
	{
		this.lasttime = lasttime;
	}

	public String getNexttime()
	{
		return nexttime;
	}

	public void setNexttime(String nexttime)
	{
		this.nexttime = nexttime;
	}
	
	
}