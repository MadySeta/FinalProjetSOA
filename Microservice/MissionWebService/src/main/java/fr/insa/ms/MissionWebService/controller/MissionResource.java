package fr.insa.ms.MissionWebService.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.ms.MissionWebService.model.Mission;


@RestController
@RequestMapping("/mission")
public class MissionResource {
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
	public Mission getUserById(@PathVariable int id) {
		 String query = "SELECT * FROM mission WHERE id = ?";
	        Connection connexion = null;
	        
	        try {
	        	connexion = openConnection();
	            PreparedStatement preparedStatement = connexion.prepareStatement(query);
	            preparedStatement.setInt(1, id);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    // Récupérez les informations de la mission
	                    int missionId = resultSet.getInt("id");
	                    int helpSeekerId = resultSet.getInt("helpSeekerId");
	                    String title = resultSet.getString("title");
	                    String description = resultSet.getString("description");
	                    int level = resultSet.getInt("level");
	                    int status = resultSet.getInt("status");

	                    // Créez un objet Mission avec les informations récupérées
	                    return new Mission(missionId, helpSeekerId, title, description, level, status);
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	        	closeConnection(connexion);
	        }

	        return null;
	}
	
	@PostMapping()
	public void createMission(@RequestBody Mission mission) {
		Connection connection = null;
		 String query = "INSERT INTO mission (helpSeekerId, title, description, level, status) VALUES (?, ?, ?, ?, ?)";
	        
	        try {
	        	connection = openConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(query);
	            
	            // Définir les valeurs des paramètres
	            preparedStatement.setInt(1, mission.getHelpSeekerId());
	            preparedStatement.setString(2, mission.getTitle());
	            preparedStatement.setString(3, mission.getDescription());
	            preparedStatement.setInt(4, mission.getLevel());
	            preparedStatement.setInt(5, mission.getStatus());

	            // Exécuter la requête
	            int lignesAffectees = preparedStatement.executeUpdate();

	            if (lignesAffectees > 0) {
	                System.out.println("Mission ajoutée avec succès.");
	            } else {
	                System.out.println("Échec de l'ajout de la mission.");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	        	closeConnection(connection);
	        }
	}
	
	@PutMapping("/{id}/{status}")
	public void updateMissionStatus(@PathVariable int id,
			@PathVariable int status) {
		Connection connection = null;
		String query = "UPDATE mission SET status = ? WHERE id = ?";
        
        try {
        	connection = openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            // Définir les valeurs des paramètres
            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, id);

            // Exécuter la requête
            int lignesAffectees = preparedStatement.executeUpdate();

            if (lignesAffectees > 0) {
                System.out.println("Status de la mission mis à jour avec succès.");
            } else {
                System.out.println("Échec de la mise à jour du status de la mission.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	closeConnection(connection);
        }
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteMission(@PathVariable int id) {
		Connection connection = null;
		 String query = "DELETE FROM mission WHERE id = ?";
	        
	        try{
	       
	        	connection = openConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, id);
	            
	            int lignesAffectees = preparedStatement.executeUpdate();

	            if (lignesAffectees > 0) {
	                System.out.println("Mission supprimée avec succès.");
	            } else {
	                System.out.println("Échec de la suppression de la mission (mission non trouvée).");
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
