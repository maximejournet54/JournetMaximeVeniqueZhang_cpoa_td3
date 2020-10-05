package test;

import java.sql.*;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import connexion.ConnexionMYSQL;
import dao.MYSQLLigneCommandeDAO;
import pojo.LigneCommande;

public class MYSQLLigneCommandeDAOtest {
	private static MYSQLLigneCommandeDAO instance;

	@Test
	public LigneCommande getById(int id_commande) throws SQLException {
		LigneCommande commande = null;
		Connection cnx = ConnexionMYSQL.creeConnexion();
		PreparedStatement req =cnx.prepareStatement("select * from LigneCommande where id_commande = ?");
		req.setInt(1, id_commande);	
		ResultSet res = req.executeQuery();
		while (res.next()) {
			commande= new LigneCommande(id_commande,res.getInt(2),res.getInt(3),res.getDouble(4));
		}
		cnx.close();
		req.close();
		res.close();
		return commande;
	}
		
	@Test
	public boolean create(LigneCommande c) throws  SQLException{
		Connection cnx = ConnexionMYSQL.creeConnexion();
		PreparedStatement req = cnx.prepareStatement("INSERT INTO Commande (id_commande,id_produit,quantite,tarif_unitaire) values (?,?,?,?)", java.sql.Statement.RETURN_GENERATED_KEYS);
		req.setInt(1, c.getId_commande());
		req.setInt(2, c.getId_produit());
		req.setInt(3, c.getQuantite());
		req.setDouble(4, c.getTarif_unitaire());
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
	public boolean update(LigneCommande c) throws SQLException {
		Connection cnx = ConnexionMYSQL.creeConnexion();
		PreparedStatement req = cnx.prepareStatement("update LigneCommande set id_commande=?, id_produit=?, quantite=?, tarif_unitaire=?");
		req.setInt(1, c.getId_commande());
		req.setInt(2, c.getId_produit());
		req.setInt(3, c.getQuantite());
		req.setDouble(4, c.getTarif_unitaire());
		int nbLignes = req.executeUpdate();
		cnx.close();
		req.close();	
	return nbLignes==1;
	}

	@Test
	public boolean delete(LigneCommande c) throws SQLException{	
		try {
			Connection cnx = ConnexionMYSQL.creeConnexion();
			PreparedStatement req = cnx.prepareStatement("delete from LigneCommande where id_commande=?");
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
	public static MYSQLLigneCommandeDAO getInstance() {
		if (instance==null) {
			instance = new MYSQLLigneCommandeDAO();
		}
		return instance;
	}
	
	@Test
	public ArrayList<LigneCommande> findAll() throws Exception {
		ArrayList<LigneCommande> produ = new ArrayList<LigneCommande>();
		Connection MaConnection = ConnexionMYSQL.creeConnexion();
		PreparedStatement req = MaConnection.prepareStatement("select * from LigneCommande ");
		ResultSet res = req.executeQuery();
		while (res.next()) {
			produ.add(new LigneCommande(res.getInt("id_commande"), res.getInt("id_produit"), res.getInt("quantite"), res.getDouble("tarif_unitaire")));
		}
		req.close();
		res.close();
		return produ;
	}
	
}
