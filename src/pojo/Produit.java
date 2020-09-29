package pojo;

import java.sql.*;

import connexion.ConnexionMYSQL;

public class Produit {
    private int id_produit;
    private String nom;
    private String description;
    private double tarif;
    private String visuel;
    private int id_categorie;
 
    public Produit(int id_produit, String nom, String description, double tarif, String visuel, int id_categorie) {
		this.id_produit = id_produit;
		this.nom = nom;
		this.description = description;
		this.tarif = tarif;
		this.visuel = visuel;
		this.id_categorie = id_categorie;
    }
    
    public Produit(int id_produit) {
		this.id_produit=id_produit;
	}

	public void setId(int id_produit) {
        this.id_produit=id_produit;
	}

	public int getId() {
		return id_produit;
	}

	public static void create(Object T){
        try {
            Produit p=(Produit) T;
            Connection laConnexion = ConnexionMYSQL.creeConnexion();
            Statement requete= laConnexion.createStatement();
            String query="INSERT INTO Produit VALUES("+p.id_produit+","+p.nom+", "+p.description+","+p.tarif+","+p.visuel+", "+p.id_categorie+")";
            requete.executeUpdate(query);
            System.out.println("Produit ajoute");
        } catch(SQLException sqle){
            System.out.println("Pb select:" +sqle.getMessage());
        }
    }

    public static void delete(Object T){
        try {
            Produit p=(Produit) T;
            Connection laConnexion = ConnexionMYSQL.creeConnexion();
            Statement requete= laConnexion.createStatement();
            String query="delete from Produit where id_produit="+p.id_produit;
            requete.executeUpdate(query);
            System.out.println("produit supprime");
        } catch(SQLException sqle){
            System.out.println("Probleme select:" +sqle.getMessage());
        }
    }

    public static void update(Object T){
        try {
            Produit p=(Produit) T;
            Connection laConnexion = ConnexionMYSQL.creeConnexion();
            Statement requete= laConnexion.createStatement();
            String query="update from Produit where id_produit="+p.id_produit;
            requete.executeUpdate(query);
            System.out.println("produit mis a jour");
        } catch(SQLException sqle){
            System.out.println("Probleme select:" +sqle.getMessage());
        }
    }

    public static void AfficherProduit() {
        try {
            Connection laConnexion = ConnexionMYSQL.creeConnexion();
            Statement requete = laConnexion.createStatement();
            ResultSet res = requete.executeQuery("select * from Produit");
            while (res.next()) {
                int id = res.getInt("id_produit");
                String nom = res.getString("nom");
                String description = res.getString("description");
                double tarif = res.getDouble("tarif");
                String visuel = res.getString("visuel");
                int id_categorie = res.getInt("id_categorie");
                System.out.println(id);
                System.out.println(nom);
                System.out.println(description);
                System.out.println(tarif);
                System.out.println(visuel);
                System.out.println(id_categorie);
                System.out.println(new Produit( res.getInt("id_produit"),
                res.getString("nom"),res.getString("description"),res.getDouble("tarif"),res.getString("visuel"),res.getInt("id_categorie")));

            }
            if (res != null)
                res.close();
            if (requete != null)
                requete.close();
            if (laConnexion != null)
                laConnexion.close();
        } catch (SQLException sqle) {
            System.out.println("Pb dans select " + sqle.getMessage());
        }
    }
}

