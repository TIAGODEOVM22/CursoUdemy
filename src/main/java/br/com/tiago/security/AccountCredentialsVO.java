package br.com.tiago.security;

import java.io.Serializable;
import java.util.Objects;

public class AccountCredentialsVO  implements Serializable{
	private static final long serialVersionUID = 1L;

	private String username;
	private String pasword;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasword() {
		return pasword;
	}
	public void setPasword(String pasword) {
		this.pasword = pasword;
	}
	@Override
	public int hashCode() {
		return Objects.hash(pasword, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountCredentialsVO other = (AccountCredentialsVO) obj;
		return Objects.equals(pasword, other.pasword) && Objects.equals(username, other.username);
	}
	
	
	
}
