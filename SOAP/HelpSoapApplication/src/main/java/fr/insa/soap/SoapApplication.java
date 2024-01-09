package fr.insa.soap;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import fr.insa.soap.wsdltojava.User;
import fr.insa.soap.wsdltojava.UserService;
import fr.insa.soap.wsdltojava.UserWS;

public class SoapApplication {
	
	public static void main(String [] args) throws MalformedURLException {
		
		final String adress ="http://localhost:8089/userService";
		
		final URL url= URI.create(adress).toURL();
		
		final UserService service = new UserService(url);
		
		final UserWS port = service.getPort(UserWS.class);
		
		
		String username1 = "Mathieu";
		String email1 = "math@yy.fr";
		
		String username2 = "Mady";
		String email2 = "mady@yy.fr";
		
		String username3  = "Sandrine";
		String email3 = "sansan@yy.fr";
		
		String username4 = "Rodolphe";
		String email4 = "roro@yy.fr";
		
		String username5 = "Emilie";
		String email5 = "emilie@yy.fr";
		
		String username6 = "Julien";
		String email6 = "juju@yy.fr";
		
		System.out.println("Start");
		
		System.out.println("Empty list : "+port.getAllUsername());
		System.out.println(port.addUser(username1, email1));	
		System.out.println(port.addUser(username2, email2));	
		System.out.println(port.addUser(username3, email3));
		System.out.println("list 3 : "+port.getAllUsername());
		System.out.println(port.addUser(username4, email4));	
		System.out.println(port.addUser(username5, email5));	
		System.out.println(port.addUser(username6, email6));
		System.out.println("list 6 : "+port.getAllUsername());
		User userSandrine = port.getUserById(2);
		
		
		System.out.println("End");
		
		
	}

}
