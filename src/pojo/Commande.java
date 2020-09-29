package pojo;

import connexion.ConnexionMYSQL;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Commande {
    int id_commande,id_client;
    LocalDate date_commande;
	public Commande(int id_commande, String date_commande, int id_client) {
        this.id_commande = id_commande;
        DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateDebut = LocalDate.parse(date_commande, formatage);
        this.date_commande = dateDebut;
		this.id_client = id_client;		
    }

    public Commande(int id_commande) {
		this.id_commande=id_commande;
	}

	public void setId(int id_commande) {
        this.id_commande = id_commande;
	}

	public int getId() {
		return id_commande;
	}
    
    public static void create(Object T){
        try {
            Commande c = (Commande) T;
            Connection laConnexion = ConnexionMYSQL.creeConnexion();
            Statement requete= laConnexion.createStatement();
            String query="INSERT INTO Commande VALUES("+c.id_commande+","+  java.sql.Date.valueOf(c.date_commande)+","+c.id_client+")";
            requete.executeUpdate(query);
            System.out.println("Commande ajoutee");
        } catch(SQLException sqle){
            System.out.println("Probleme select:" +sqle.getMessage());
        }            
    }

    public static void delete(Object T){
        try {
            Commande c = (Commande) T;
            Connection laConnexion = ConnexionMYSQL.creeConnexion();
            Statement requete= laConnexion.createStatement();
            String query="delete from Commande where id_commande="+c.id_commande;
            requete.executeUpdate(query);
            System.out.println("commande supprimee");
            } catch(SQLException sqle){
            System.out.println("Probleme select:" +sqle.getMessage());
        }          
    }

    public static void update(Object T){
        try {
            Commande c = (Commande) T;
            Connection laConnexion=ConnexionMYSQL.creeConnexion();
            Statement requete= laConnexion.createStatement();
            String query="update from Commande where id_commande="+c.id_commande;
            requete.executeUpdate(query);
            System.out.println("commande mise a jour");
            } catch(SQLException sqle){
            System.out.println("Probleme select:" +sqle.getMessage());
        }  
    }

    public static void AfficherCommande() {
        try {
            Connection laConnexion = ConnexionMYSQL.creeConnexion();
            Statement requete = laConnexion.createStatement();
            ResultSet res = requete.executeQuery("select * from Commande");
            while (res.next()) {
                System.out.println(new Commande(res.getInt("id_commande"), res.getString("date_commande"), res.getInt("id_client")));
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
