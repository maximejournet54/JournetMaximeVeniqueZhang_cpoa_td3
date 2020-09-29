package pojo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import connexion.ConnexionMYSQL;

public class LigneCommande {
    private int id_commande, id_produit, quantite;	
    private double tarif_unitaire;
    public LigneCommande(int id_commande, int id_produit, int quantite, double tarif_unitaire){
        this.id_commande=id_commande;
        this.id_produit=id_produit;
        this.quantite=quantite;
        this.tarif_unitaire=tarif_unitaire;
    }

    public LigneCommande(int id_commande, int id_produit) {
		this.id_commande=id_commande;
		this.id_produit=id_produit;
	}

	public void setId(int id_commande) {
        this.id_commande=id_commande;
	}

	public int getId() {
		return id_commande;
	}

    public static void create(Object T) {
        try {
            LigneCommande c=(LigneCommande) T;
            Connection laConnexion = ConnexionMYSQL.creeConnexion();
            Statement requete= laConnexion.createStatement();
            String query="INSERT INTO Ligne_commande VALUES("+c.id_commande+",'"+c.id_produit+"', '"+c.quantite+"', '"+c.tarif_unitaire+"')";
            requete.executeUpdate(query);
            System.out.println("Ligne de commande ajoutee");
        } catch(SQLException sqle){
            System.out.println("Pb select:" +sqle.getMessage());
        }
    }
    
	public static void delete(Object T) {
       try {
            LigneCommande c=(LigneCommande) T;
            Connection laConnexion = ConnexionMYSQL.creeConnexion();
            Statement requete= laConnexion.createStatement();
            String query="delete from Ligne_commande where id_commande="+c.id_commande+ " and id_produit="+c.id_produit;
            requete.executeUpdate(query);
            System.out.println(" Ligne de commande supprimee");
        } catch(SQLException sqle){
            System.out.println("Probleme select:" +sqle.getMessage());
        }
	}

	public static void update(Object T) {
        try {
            LigneCommande c=(LigneCommande) T;
            Connection laConnexion = ConnexionMYSQL.creeConnexion();
            Statement requete= laConnexion.createStatement();
            String query="update from Ligne_commande where id_commande="+c.id_commande+ " and id_produit="+c.id_produit;
            requete.executeUpdate(query);
            System.out.println(" Ligne de commande mise a jour");
        } catch(SQLException sqle){
            System.out.println("Probleme select:" +sqle.getMessage());
        }
	}
}

