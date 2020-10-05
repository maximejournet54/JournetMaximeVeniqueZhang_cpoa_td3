package test;

import java.sql.*;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import connexion.ConnexionMYSQL;
import dao.MYSQLCommandeDAO;
import pojo.Commande;

public class MYSQLCommandeDAOtest {
	private static MYSQLCommandeDAO instance;
	
	@Test
	public Commande getById(int id_commande) throws SQLException {
		Commande commande = null;
		Connection cnx = ConnexionMYSQL.creeConnexion();
		PreparedStatement req =cnx.prepareStatement("select * from Commande where id_commande = ?");
		req.setInt(1, id_commande);	
		ResultSet res = req.executeQuery();
		while (res.next()) {
			commande= new Commande(id_commande,res.getString(2),res.getInt(3));
		}
		cnx.close();
		req.close();
		res.close();
		return commande;
	}
		
	@Test
	public boolean create(Commande c) throws  SQLException{
		Connection cnx = ConnexionMYSQL.creeConnexion();
		PreparedStatement req = cnx.prepareStatement("INSERT INTO Commande (id_commande,date_commande,id_client) values (?,?,?)", java.sql.Statement.RETURN_GENERATED_KEYS);
		req.setInt(1, c.getId_commande());
		req.setDate(2, c.getDate_commande());
		req.setInt(3, c.getId_client());
		int nbLignes = req.executeUpdate();
		ResultSet res = req.getGeneratedKeys();
		int clef;
		if(res.next()) {
			clef = res.getInt(1);
			c.setId_commande(clef);		
		}
		cnx.close();
		req.close();
		res.close();
		return nbLignes==1;
		}

	@Test
	public boolean update(Commande c) throws SQLException {
		Connection cnx = ConnexionMYSQL.creeConnexion();
		PreparedStatement req = cnx.prepareStatement("update Commande set id_commande=?, date_commande=?, id_client=?");
		req.setInt(1, c.getId_commande());
		req.setDate(2, c.getDate_commande());
		req.setInt(3, c.getId_client());
		int nbLignes = req.executeUpdate();
		cnx.close();
		req.close();	
	return nbLignes==1;
	}

	@Test
	public boolean delete(Commande c) throws SQLException{	
		try {
			Connection cnx = ConnexionMYSQL.creeConnexion();
			PreparedStatement req = cnx.prepareStatement("delete from Commande where id_commande=?");
			req.setInt(1,c.getId_commande());
			int nbLignes = req.executeUpdate();
			cnx.close();
			req.close();
			return nbLignes==1;
		}catch(Exception e) {
				return false;
			}
	}
	
	@Test
	public static MYSQLCommandeDAO getInstance() {
		if (instance==null) {
			instance = new MYSQLCommandeDAO();
		}
		return instance;
	}
	
	@Test
	public ArrayList<Commande> findAll() throws Exception {
		ArrayList<Commande> produ = new ArrayList<Commande>();
		Connection MaConnection = ConnexionMYSQL.creeConnexion();
		PreparedStatement req = MaConnection.prepareStatement("select * from Commande ");
		ResultSet res = req.executeQuery();
		while (res.next()) {
			produ.add(new Commande(res.getInt("id_commande"), res.getDate("date_commande"), res.getInt("id_client")));	
		}
		req.close();
		res.close();
		return produ;
	}
}
