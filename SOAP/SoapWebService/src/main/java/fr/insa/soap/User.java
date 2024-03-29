package fr.insa.soap;

import javax.xml.bind.annotation.XmlRootElement;


public class User {

	private int id;
	private String username;
	private String email;
	
	public User(int id, String username, String email) {
		this.id = id;
		this.username = username;
		this.email = email;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getUsername() {
		return this.username;
	}
}
