package pojo;

import connexion.ConnexionMYSQL;

import java.sql.*;
import java.time.LocalDate;


public class Commande {
    private int id_commande,id_client;
    private Date date_commande;
	public Commande(int id_commande, String date_commande, int id_client) {
		this.setId_commande(id_commande);
		this.setDate_commande2(date_commande);
		this.setId_client(id_client);
	}
	
	public Commande(int id_commande,Date date_commande,int id_client) {
		this.setId_commande(id_commande);
		this.setDate_commande1(date_commande);
		this.setId_client(id_client);
	}

    public Commande(int id_commande) {
		this.id_commande=id_commande;
	}
    
    public Commande(int id_commande,LocalDate date_commande,int id_client) {
		this.setId_commande(id_commande);
		this.setDate_commande3(date_commande);
		this.setId_client(id_client);
	}
    
    //getters et setters
    
    public int getId_commande() {
		return id_commande;
	}

	public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
	}
	
	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	
	public Date getDate_commande() {
		return date_commande;
	}

	public void setDate_commande1(Date date_commande) {
		this.date_commande = date_commande;
	}
	public void setDate_commande2(String date_commande) {
		this.date_commande = java.sql.Date.valueOf(date_commande);
	}
	public void setDate_commande3(LocalDate date_commande) {
		this.date_commande = java.sql.Date.valueOf(date_commande);
	}
	
    
    public static void create(Object T){
        try {
            Commande c = (Commande) T;
            Connection laConnexion = ConnexionMYSQL.creeConnexion();
            PreparedStatement requete= laConnexion.prepareStatement("INSERT INTO Commande (id_commande, date_commande, id_client) values (?,?,?)", java.sql.Statement.RETURN_GENERATED_KEYS);
            requete.setInt(1,c.getId_commande());
            requete.setDate(2, c.getDate_commande());
			requete.setInt(3, c.getId_client());
			requete.executeUpdate();
			ResultSet res = requete.getGeneratedKeys();
			if(res.next()) {
				int cle = res.getInt(1);
				c.setId_commande(cle);	
			}
			laConnexion.close();
			requete.close();
			res.close();
            
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
