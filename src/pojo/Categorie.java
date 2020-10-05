package pojo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connexion.ConnexionMYSQL;

public class Categorie {
    private int id_categorie;
    private String visuel;
    private String titre;

    public Categorie(int id_categorie, String titre, String visuel ) {
        this.id_categorie = id_categorie;
        this.visuel = visuel;
        this.titre = titre;
    }
    
    public Categorie(int id_categorie) {
    	this.setId_categorie(id_categorie);
    }

	public int getId_categorie() {
		return id_categorie;
    }
    
    public void setId_categorie(int id_categorie) {
        this.id_categorie=id_categorie;
    }
    
    public String getTitre()
	{
		return this.titre;
	}
	public void setTitre(String titre)
	{   
		if(titre==null|| titre.trim().length()==0)
		{
			throw new IllegalArgumentException("Titre de la categorie!");
		}
		this.titre=titre;
    }
    
    public String getVisuel()
	{
		return this.visuel;
    }
    
	public void setVisuel(String visuel)
	{
		this.visuel=visuel;
	}

    public static void create(Object T){

        try {
            Categorie c=(Categorie) T;
            Connection laConnexion = ConnexionMYSQL.creeConnexion();
            Statement requete= laConnexion.createStatement();
            String query="INSERT INTO Categorie VALUES("+c.id_categorie+",'"+c.titre+"', '"+c.visuel+"')";
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
            String query="delete from Categorie where id_categorie="+c.id_categorie;
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
            String query="update from Categorie where id_categorie ="+c.id_categorie;
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
