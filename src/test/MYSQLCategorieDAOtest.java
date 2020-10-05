package test;

import java.sql.*;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import connexion.ConnexionMYSQL;
import dao.MYSQLCategorieDAO;
import pojo.Categorie;

public class MYSQLCategorieDAOtest {
	private static MYSQLCategorieDAO instance;

	@Test
	public Categorie getById(int id_categorie) throws SQLException {
		Categorie categorie = null;
		Connection cnx = ConnexionMYSQL.creeConnexion();
		PreparedStatement req =cnx.prepareStatement("select * from Categorie where id_categorie = ?");
		req.setInt(1, id_categorie);	
		ResultSet res = req.executeQuery();
		while (res.next()) {
			categorie= new Categorie(id_categorie, res.getString(2), res.getString(3));
		}
		cnx.close();
		req.close();
		res.close();
		return categorie;
	}
		
	@Test
	public boolean create(Categorie c) throws  SQLException{
		Connection cnx = ConnexionMYSQL.creeConnexion();
		PreparedStatement req = cnx.prepareStatement("INSERT INTO Categorie (id_categorie,titre,visuel,) values (?,?,?)", java.sql.Statement.RETURN_GENERATED_KEYS);
		req.setInt(1, c.getId_categorie());
		req.setString(2, c.getTitre());
		req.setString(3, c.getVisuel());
		int nbLignes = req.executeUpdate();
		ResultSet res = req.getGeneratedKeys();
		int clef;
		if(res.next()) {
			clef = res.getInt(1);
			c.setId_categorie(clef);		
		}
		cnx.close();
		req.close();
		res.close();
		return nbLignes==1;
		}

	@Test
	public boolean update(Categorie c) throws SQLException {
		Connection cnx = ConnexionMYSQL.creeConnexion();
		PreparedStatement req = cnx.prepareStatement("update Categorie set id_categorie=?,titre=?,visuel=?");
		req.setInt(1, c.getId_categorie());
		req.setString(2, c.getVisuel());
		req.setString(3, c.getTitre());
		int nbLignes = req.executeUpdate();
		cnx.close();
		req.close();	
	return nbLignes==1;
	}

	@Test
	public boolean delete(Categorie c) throws SQLException{	
		try {
			Connection cnx = ConnexionMYSQL.creeConnexion();
			PreparedStatement req = cnx.prepareStatement("delete from Categorie where id_categorie=?");
			req.setInt(1,c.getId_categorie());
			int nbLignes = req.executeUpdate();
			cnx.close();
			req.close();
			return nbLignes==1;
		}catch(Exception e) {
				return false;
			}
	}
	
	public static MYSQLCategorieDAO getInstance() {
		if (instance==null) {
			instance = new MYSQLCategorieDAO();
		}
		return instance;
	}
	
	@Test
	public ArrayList<Categorie> findAll() throws Exception {
		ArrayList<Categorie> produ = new ArrayList<Categorie>();
		
		Connection MaConnection = ConnexionMYSQL.creeConnexion();
		PreparedStatement req = MaConnection.prepareStatement("select * from Categorie ");
		ResultSet res = req.executeQuery();
		while (res.next()) {
			produ.add(new Categorie(res.getInt("id_categorie"), res.getString("visuel"), res.getString("titre")));	
		}
		req.close();
		res.close();
		return produ;
	}
}
