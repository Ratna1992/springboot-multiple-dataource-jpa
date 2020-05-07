package com.ratna.architecture.transferobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="employee")
public class Employee {
	@XmlElement
	private String date;
	@XmlElement
	private String last_name;
	@XmlElement
	private String photo;
	@XmlElement
	private String id;
	@XmlElement
	private String first_name;
	@XmlElement
	private String married;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getMarried() {
		return married;
	}

	public void setMarried(String married) {
		this.married = married;
	}

	@Override
	public String toString() {
		return "ClassPojo [date = " + date + ", last_name = " + last_name + ", photo = " + photo + ", id = " + id
				+ ", first_name = " + first_name + ", married = " + married + "]";
	}
}
