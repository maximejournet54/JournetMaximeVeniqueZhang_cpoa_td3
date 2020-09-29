package pojo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connexion.ConnexionMYSQL;

public class Categorie {
    private int id;
    private String visuel;
    private String titre;

    public Categorie(int id, String visuel, String titre) {
        this.id = id;
        this.visuel = visuel;
        this.titre = titre;
    }
    
    public Categorie(int id) {
    	this.setId(id);
    }

    public void setId(int id) {
        this.id=id;
	}

	public int getId() {
		return id;
	}

    public static void create(Object T){

        try {
            Categorie c=(Categorie) T;
            Connection laConnexion = ConnexionMYSQL.creeConnexion();
            Statement requete= laConnexion.createStatement();
            String query="INSERT INTO Categorie VALUES("+c.id+",'"+c.visuel+"', '"+c.titre+"')";
            requete.executeUpdate(query);
            System.out.println("Categorie ajoutee");
        } catch(SQLException sqle){
            System.out.println("Pb select:" +sqle.getMessage());
        }
    }

    public static void delete(Object T){
        try {
            Categorie c=(Categorie) T;
            Connection laConnexion = ConnexionMYSQL.creeConnexion();
            Statement requete= laConnexion.createStatement();
            String query="delete from Categorie where id_categorie="+c.id;
            requete.executeUpdate(query);
            System.out.println("categorie supprimee");
        } catch(SQLException sqle){
            System.out.println("Probleme select:" +sqle.getMessage());
        }
    }

    public static void update(Object T){
        try {
            Categorie c=(Categorie) T;
            Connection laConnexion = ConnexionMYSQL.creeConnexion();
            Statement requete= laConnexion.createStatement();
            String query="update from Categorie where id_categorie ="+c.id;
            requete.executeUpdate(query);
            System.out.println("Categorie mise a jour");
        } catch(SQLException sqle){
            System.out.println("Probleme select:" +sqle.getMessage());
        }
    }
    public static void AfficherCategorie() {
        try {
            Connection laConnexion = ConnexionMYSQL.creeConnexion();
            Statement requete = laConnexion.createStatement();
            ResultSet res = requete.executeQuery("select id_categorie, titre, visuel from Categorie");
            while (res.next()) {
                int id = res.getInt("id_categorie");
                String titre=res.getString("titre");
                String visuel=res.getString("visuel");
                System.out.println(id);
                System.out.println(titre);
                System.out.println(visuel);
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
