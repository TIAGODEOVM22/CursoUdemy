package br.com.tiago.data.vo.v2;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

public class PersonVOv2 extends RepresentationModel <PersonVOv2> implements Serializable {
	private static final long serialVersionUID = 1L;


	private Long id;
	private String firstName;
	private String lastName;
	private String address;
	private String gender;
	private Date birthDate;

	public PersonVOv2() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, birthDate, firstName, gender, id, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonVOv2 other = (PersonVOv2) obj;
		return Objects.equals(address, other.address) && Objects.equals(birthDate, other.birthDate)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(gender, other.gender)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName);
	}
	
	
}