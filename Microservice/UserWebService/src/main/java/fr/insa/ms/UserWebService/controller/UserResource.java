package fr.insa.ms.UserWebService.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.ms.UserWebService.model.User;

@RestController
@RequestMapping("/user")
public class UserResource {
	private static String URL;
	private static String UTILISATEUR;
	private static final String MOT_DE_PASSE = "ei4tai9A";

	@Value("${db.url}")
	private void setUrl(String url) {
		URL = url;
	}
	
	@Value("${db.user}")
	private void setUtilisateur(String utilisateur) {
		UTILISATEUR = utilisateur;
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable int id) {
		
		Connection connection = null;
		String query = "SELECT * FROM users WHERE id = ?";

		try {
			connection = openConnection();
	
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					// Récupérez les informations de l'utilisateur
					String username = resultSet.getString("username");
					String email = resultSet.getString("email");
					int type = resultSet.getInt("type");

					// Créez un objet Utilisateur avec les informations récupérées
					return new User(id, username, email, type);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return null;
	}

	@PostMapping()
	public ResponseEntity createUser(@RequestBody User user) {
		Connection connection = null;
		String query = "INSERT INTO users (username, email, type) VALUES (?, ?, ?)";

		try {
			connection = openConnection();
	
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			// Définir les valeurs des paramètres
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setInt(3, user.getType());

			// Exécuter la requête
			int lignesAffectees = preparedStatement.executeUpdate();

			if (lignesAffectees > 0) {
				return new ResponseEntity<>("Utilisateur ajouté avec succès", HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("Échec de l'ajout de l'utilisateur", HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Erreur lors de l'ajout de l'utilisateur", HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			closeConnection(connection);
		}
	}

	@GetMapping("/all")
	public List<User> getUserById() {

		Connection connection = null;
		List<User> userList = new ArrayList<>();

		String query = "SELECT * FROM users ";

		try {
			connection = openConnection();
	
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				// Récupérez les informations de l'utilisateur
				int id = resultSet.getInt("id");
				String username = resultSet.getString("username");
				String email = resultSet.getString("email");
				int type = resultSet.getInt("type");

				// Créez un objet Utilisateur avec les informations récupérées
				User user = new User(id, username, email, type);
				userList.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return userList;
	}

	private static Connection openConnection() {
		try {
			// Chargez le pilote JDBC MySQL
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Établissez la connexion
			Connection connexion = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);

			return connexion;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static void closeConnection(Connection connexion) {
        try {
            if (connexion != null && !connexion.isClosed()) {
                connexion.close();
                System.out.println("Connexion fermée avec succès.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
