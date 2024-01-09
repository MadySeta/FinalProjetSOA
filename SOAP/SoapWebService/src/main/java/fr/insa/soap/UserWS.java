package fr.insa.soap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName="userService")
public class UserWS {
	private List<User> userList = new ArrayList<User>();
	private int userCount = 1;
	
	@WebMethod(operationName="addUser")
	public int addUser(@WebParam(name="username") String username, 
			@WebParam(name="email") String email) {
		User user = new User(userCount++, username, email);
		
		userList.add(user);
		
		return user.getId();
	}
	
	@WebMethod(operationName="getAllUser")
	public List<User> getAllUser(){
		return userList;
	}
	
	@WebMethod(operationName="getAllUsername")
	public List<String> getAllUsername(){
		List<String> usernameList = new ArrayList<String>();
		
		for (User user : userList) {
			usernameList.add(user.getUsername());
		}
		return usernameList;
	}
	
	
	@WebMethod(operationName="getUserById")
	public User getUserById(@WebParam(name="id") int id) {
		for (User user : userList) {
            if (user.getId() == id) {
                return user; // Retourner l'utilisateur s'il est trouvé
            }
        }
		return null;
	}
	
	@WebMethod(operationName="deleteUserById")
	public boolean deleteUserById(@WebParam(name="id") int id){
		Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove(); // Supprimer l'utilisateur avec l'ID spécifié
                System.out.println("L'utilisateur avec l'ID " + id + " a été supprimé.");
                return true; // Sortir de la méthode après la suppression
            }
        }
        System.out.println("Aucun utilisateur avec l'ID " + id + " n'a été trouvé.");
        return false;
	}

}
