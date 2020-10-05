package test;

import java.sql.*;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import connexion.ConnexionMYSQL;
import dao.MYSQLProduitDAO;
import pojo.Produit;

public class MYSQLProduitDAOtest {
	private static MYSQLProduitDAO instance;

	@Test
	public Produit getById(int id_produit) throws SQLException {
		Produit produit = null;
		Connection cnx = ConnexionMYSQL.creeConnexion();
		PreparedStatement req =cnx.prepareStatement("select * from Produit where id_produit = ?");
		req.setInt(1, id_produit);	
		ResultSet res = req.executeQuery();
		while (res.next()) {
			produit= new Produit(id_produit, res.getString(2), res.getString(3), res.getFloat(4), res.getString(5), res.getInt(6));
		}
		cnx.close();
		req.close();
		res.close();
		return produit;
	}
	
	@Test
	public boolean create(Produit p) throws  SQLException{
		Connection cnx = ConnexionMYSQL.creeConnexion();
		PreparedStatement req = cnx.prepareStatement("INSERT INTO Produit (id_produit,nom,description,tarif,visuel,id_categorie) values (?,?,?,?,?,?)", java.sql.Statement.RETURN_GENERATED_KEYS);
		req.setString(1, p.getNom());
		req.setString(2, p.getDescription());
		req.setDouble(3, p.getTarif());
		req.setString(4,p.getVisuel());
		req.setInt(5, p.getId_categorie());
		int nbLignes = req.executeUpdate();
		ResultSet res = req.getGeneratedKeys();
		int clef;
		if(res.next()) {
			clef = res.getInt(1);
			p.setId_produit(clef);		
		}
		cnx.close();
		req.close();
		res.close();
		return nbLignes==1;
		}

	@Test
	public boolean update(Produit p) throws SQLException {
		Connection cnx = ConnexionMYSQL.creeConnexion();
		PreparedStatement req = cnx.prepareStatement("update Produit set id_produit=?, nom=?, description=?, tarif=?, visuel = ?, id_categorie=?  where id_produit=?");
		req.setInt(1, p.getId_produit());
		req.setString(2,p.getNom());
		req.setString(3, p.getDescription());
		req.setDouble(4, p.getTarif());
		req.setString(5,p.getVisuel());
		req.setInt(6, p.getId_categorie());
		int nbLignes = req.executeUpdate();
		cnx.close();
		req.close();	
	return nbLignes==1;
	}

	@Test
	public boolean delete(Produit p) throws SQLException{	
		try {
			Connection cnx = ConnexionMYSQL.creeConnexion();
			PreparedStatement req = cnx.prepareStatement("delete from Produit where id_produit=?");
			req.setInt(1,p.getId_produit());
			int nbLignes = req.executeUpdate();
			cnx.close();
			req.close();
			return nbLignes==1;
		}catch(Exception e) {
				return false;
			}
	}
	
	@Test
	public static MYSQLProduitDAO getInstance() {
		if (instance==null) {
			instance = new MYSQLProduitDAO();
		}
		return instance;
	}
	
	@Test
	public ArrayList<Produit> findAll() throws Exception {
		ArrayList<Produit> produ = new ArrayList<Produit>();
		
		Connection MaConnection = ConnexionMYSQL.creeConnexion();
		PreparedStatement req = MaConnection.prepareStatement("select * from Produit ");
		ResultSet res = req.executeQuery();
		while (res.next()) {
			produ.add(new Produit(res.getInt("id_produit"), res.getString("nom"), res.getString("description"),res.getDouble("tarif"),res.getString("visuel"),res.getInt("id_categorie")));	
		}
		req.close();
		res.close();
		return produ;
	}

}
