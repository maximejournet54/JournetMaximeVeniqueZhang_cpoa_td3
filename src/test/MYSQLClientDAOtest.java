package test;

import java.sql.*;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import connexion.ConnexionMYSQL;
import dao.MYSQLClientDAO;
import pojo.Client;

public class MYSQLClientDAOtest {
	private static MYSQLClientDAO instance;

	@Test
	public Client getById(int id_client) throws SQLException {
		Client Client = null;
		Connection cnx = ConnexionMYSQL.creeConnexion();
		PreparedStatement req =cnx.prepareStatement("select * from Client where id_client = ?");
		req.setInt(1, id_client);	
		ResultSet res = req.executeQuery();
		while (res.next()) {
			Client= new Client(id_client,res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getInt(6),res.getString(7),res.getInt(8),res.getString(9),res.getString(10));
		}
		cnx.close();
		req.close();
		res.close();
		return Client;
	}
		
	@Test
	public boolean create(Client c) throws  SQLException{
		Connection cnx = ConnexionMYSQL.creeConnexion();
		PreparedStatement req = cnx.prepareStatement("INSERT INTO Client (id_client,nom,prenom,identifiant,mot_de_passe,adr_numero,adr_voie,adr_code_postal,adr_ville,adr_pays) values (?,?,?,?,?,?,?,?,?,?)", java.sql.Statement.RETURN_GENERATED_KEYS);
		req.setInt(1, c.getId_client());
		req.setString(2, c.getNom());
		req.setString(3, c.getPrenom());
		req.setString(4, c.getIdentifiant());
		req.setString(5, c.getMdp());
		req.setInt(6, c.getRue());
		req.setString(7, c.getVoie());
		req.setInt(8, c.getCp());
		req.setString(9, c.getVille());
		req.setString(10, c.getPays());

		int nbLignes = req.executeUpdate();
		ResultSet res = req.getGeneratedKeys();
		int clef;
		if(res.next()) {
			clef = res.getInt(1);
			c.setId_client(clef);		
		}
		cnx.close();
		req.close();
		res.close();
		return nbLignes==1;
		}

	@Test
	public boolean update(Client c) throws SQLException {
		Connection cnx = ConnexionMYSQL.creeConnexion();
		PreparedStatement req = cnx.prepareStatement("update Client set id_client=?, nom=?, prenom=?, identifiant=?, mot_de_passe=?, adr_num=?, adr_voie=?, adr_code_postal=?, adr_ville=?, adr_pays=?");
		req.setInt(1, c.getId_client());
		req.setString(2, c.getNom());
		req.setString(3, c.getPrenom());
		req.setString(4, c.getIdentifiant());
		req.setString(5, c.getMdp());
		req.setInt(6, c.getRue());
		req.setString(7, c.getVoie());
		req.setInt(8, c.getCp());
		req.setString(9, c.getVille());
		req.setString(10, c.getPays());
		int nbLignes = req.executeUpdate();
		cnx.close();
		req.close();	
	return nbLignes==1;
	}

	@Test
	public boolean delete(Client c) throws SQLException{	
		try {
			Connection cnx = ConnexionMYSQL.creeConnexion();
			PreparedStatement req = cnx.prepareStatement("delete from Client where id_client=?");
			req.setInt(1,c.getId_client());
			int nbLignes = req.executeUpdate();
			cnx.close();
			req.close();
			return nbLignes==1;
		}catch(Exception e) {
				return false;
			}
	}
	
	@Test
	public static MYSQLClientDAO getInstance() {
		if (instance==null) {
			instance = new MYSQLClientDAO();
		}
		return instance;
	}
	
	@Test
	public ArrayList<Client> findAll() throws Exception {
		ArrayList<Client> produ = new ArrayList<Client>();
		Connection MaConnection = ConnexionMYSQL.creeConnexion();
		PreparedStatement req = MaConnection.prepareStatement("select * from Client ");
		ResultSet res = req.executeQuery();
		while (res.next()) {
			produ.add(new Client(res.getInt("id_client"), res.getString("nom"), res.getString("prenom"),res.getString("identifiant"),res.getString("mot_de_passe"),res.getInt("adr_numero"),res.getString("adr_voie"),res.getInt("adr_code_postal"),res.getString("adr_ville"),res.getString("adr_pays")));	
		}
		req.close();
		res.close();
		return produ;
	}
}
