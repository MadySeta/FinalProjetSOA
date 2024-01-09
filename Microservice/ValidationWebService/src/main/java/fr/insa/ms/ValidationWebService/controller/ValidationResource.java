package fr.insa.ms.ValidationWebService.controller;

import fr.insa.ms.ValidationWebService.model.Validation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/validation")
public class ValidationResource {
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
    
	@GetMapping("/{missionId}")
	public Validation getValidation(@PathVariable int missionId) {
		Connection connection = null;
		String query = "SELECT * FROM validation WHERE missionId = ?";
        Validation validation = null;

        try{
        	connection = openConnection();
        
             PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, missionId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Récupérez les informations de la validation
                    boolean validity = resultSet.getBoolean("validity");
                    int validatorId = resultSet.getInt("validatorId");
                    String comment = resultSet.getString("comment");

                    // Créez un objet Validation avec les informations récupérées
                    validation = new Validation(missionId, validity, validatorId, comment);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	closeConnection(connection);
        }
        return validation;
	}

	@PostMapping("/{missionId}")
	public void createValidation(@PathVariable int missionId) {
		Connection connection = null;
		String query = "INSERT INTO validation (missionId) VALUES (?)";
        try{
        	connection = openConnection();
        
             PreparedStatement preparedStatement = connection.prepareStatement(query);

             preparedStatement.setInt(1, missionId);
             preparedStatement.executeUpdate();
            

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	closeConnection(connection);
        }
	}
	
	@PutMapping("/{missionId}/{validatorId}")
	public void updateValidatorId(@PathVariable int missionId,
			@PathVariable int validatorId) {
		Connection connection = null;
		String query = "UPDATE validation SET validatorId = ? WHERE missionId = ?";
        
        try {
        	connection = openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setInt(1, validatorId);
            preparedStatement.setInt(2, missionId);

            int lignesAffectees = preparedStatement.executeUpdate();

            if (lignesAffectees > 0) {
                System.out.println("Status de la mission mis à jour avec succès.");
            } else {
                System.out.println("Échec de la mise à jour du status de la validation.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	closeConnection(connection);
        }
	}
	
	@PutMapping("/refuse/{missionId}/{comment}")
	public void refuseMission(@PathVariable int missionId,
			@PathVariable String comment) {
		
		Connection connection = null;
		String query = "UPDATE validation SET comment = ?, validity = ? WHERE missionId = ?";
        
        try {
        	connection = openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setString(1, comment);
            preparedStatement.setBoolean(2, false);
            preparedStatement.setInt(3, missionId);

            int lignesAffectees = preparedStatement.executeUpdate();

            if (lignesAffectees > 0) {
                System.out.println("Status de la mission mis à jour avec succès.");
            } else {
                System.out.println("Échec de la mise à jour du status de la validation.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	closeConnection(connection);
        }
	}
	
	@PutMapping("/accept/{missionId}")
	public void acceptMission(@PathVariable int missionId) {
		
		Connection connection = null;
		String query = "UPDATE validation SET comment = ?, validity = ? WHERE missionId = ?";
        
        try {
        	connection = openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setString(1, "Approved");
            preparedStatement.setBoolean(2, true);
            preparedStatement.setInt(3, missionId);

            int lignesAffectees = preparedStatement.executeUpdate();

            if (lignesAffectees > 0) {
                System.out.println("Status de la mission mis à jour avec succès.");
            } else {
                System.out.println("Échec de la mise à jour du status de la validation.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	closeConnection(connection);
        }
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
