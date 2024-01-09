package fr.insa.ms.ReviewWebService.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.ms.ReviewWebService.model.Review;



@RestController
@RequestMapping("/review")
public class ReviewResource {
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
	public Review getReviewById(@PathVariable int id) {
		Connection connection = null;
		String query = "SELECT * FROM review WHERE id = ?";

		try {
			connection = openConnection();
	
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					// Récupérez les informations de l'utilisateur
					String review = resultSet.getString("review");
					int star = resultSet.getInt("star");
					int userId = resultSet.getInt("userId");

					// Créez un objet Utilisateur avec les informations récupérées
					return new Review(review, star, userId);
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
	public ResponseEntity createReview(@RequestBody Review review) {
		Connection connection = null;
		String query = "INSERT INTO review (review, star, userId) VALUES (?, ?, ?)";

		try {
			connection = openConnection();
	
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			// Définir les valeurs des paramètres
			preparedStatement.setString(1, review.getReview());
			preparedStatement.setInt(2, review.getStar());
			preparedStatement.setInt(3, review.getUserId());

			// Exécuter la requête
			int lignesAffectees = preparedStatement.executeUpdate();

			if (lignesAffectees > 0) {
				return new ResponseEntity<>("Review ajouté avec succès", HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("Échec de l'ajout du review", HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Erreur lors de l'ajout du review", HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			closeConnection(connection);
		}
	}
	
	@GetMapping("/all/{userId}")
	public List<Review> getAllReiviewsFromId(@PathVariable int userId) {
		Connection connection = null;
		String query = "SELECT * FROM review WHERE userId = ?";
		List<Review> reviewList = new ArrayList<>();

		try {
			connection = openConnection();
	
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, userId);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					// Récupérez les informations de l'utilisateur
					String review = resultSet.getString("review");
					int star = resultSet.getInt("star");
					int newUserId = resultSet.getInt("userId");

					// Créez un objet Utilisateur avec les informations récupérées
					Review newReview = new Review(review, star, newUserId);
					reviewList.add(newReview);			}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return reviewList;
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
