package pojo;

import java.sql.*;

import connexion.ConnexionMYSQL;

public class Client {
    String nom, prenom, identifiant ,mdp, voie, ville, pays;
    int id_client, num, cp;

    public Client(int id_client, String nom, String prenom, String identifiant, String mdp, int num, String voie, int cp, String ville, String pays) {
        this.setId_client(id_client);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setIdentifiant(identifiant);
		this.setMdp(mdp);
		this.setRue(num);
		this.setVoie(voie);
		this.setCp(cp);
		this.setVille(ville);
		this.setPays(pays);
    }
    
    public Client(int id_client) {
    	this.id_client=id_client;
    }

    public int getId_client() {
		return id_client;
	}

    public void setId_client(int id_client) {
        this.id_client=id_client;
	}

	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getIdentifiant() {
		return identifiant;
	}


	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}


	public String getMdp() {
		return mdp;
	}


	public void setMdp(String mdp) {
		this.mdp = mdp;
	}


	public int getRue() {
		return num;
	}


	public void setRue(int num) {
		this.num = num;
	}


	public String getVoie() {
		return voie;
	}


	public void setVoie(String voie) {
		this.voie = voie;
	}


	public int getCp() {
		return cp;
	}


	public void setCp(int cp) {
		this.cp = cp;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}


	public String getPays() {
		return pays;
	}


	public void setPays(String pays) {
		this.pays = pays;
	}

    public static void create(Object T){
        try {
            Client c = (Client) T;
            Connection laConnexion = ConnexionMYSQL.creeConnexion();
            Statement requete= laConnexion.createStatement();
            String query="INSERT INTO Client VALUES("+c.id_client+","+c.nom+","+c.prenom+","+c.identifiant+","+c.mdp+","+c.num+","+c.voie+","+c.cp+","+c.ville+","+c.pays+")";
            requete.executeUpdate(query);
            System.out.println("Client ajoute");
        } catch(SQLException sqle){
            System.out.println("Probleme select:" +sqle.getMessage());
        }            
    }

    public static void delete(Object T){
        try {
            Client c = (Client) T;
            Connection laConnexion = ConnexionMYSQL.creeConnexion();
            Statement requete= laConnexion.createStatement();
            String query="delete from Client where id_client="+c.id_client;
            requete.executeUpdate(query);
            System.out.println("client supprime");
            } catch(SQLException sqle){
            System.out.println("Probleme select:" +sqle.getMessage());
        }          
    }

    public static void update(Object T){
        try {
            Client c = (Client) T;
            Connection laConnexion=ConnexionMYSQL.creeConnexion();
            Statement requete= laConnexion.createStatement();
            String query="update from Client where id_client="+c.id_client;
            requete.executeUpdate(query);
            System.out.println("client mis a jour");
            } catch(SQLException sqle){
            System.out.println("Probleme select:" +sqle.getMessage());
        }  
    }

    public static void AfficherClient() {
        try {
            Connection laConnexion = ConnexionMYSQL.creeConnexion();
            Statement requete = laConnexion.createStatement();
            ResultSet res = requete.executeQuery("select * from Client");
            while (res.next()) {
                System.out.println(new Client(res.getInt("id_client"), res.getString("nom"), res.getString("prenom"), res.getString("identifiant"), res.getString("mot_de_passe"), res.getInt("adr_numero"), res.getString("adr_voie"), res.getInt("adr_code_postal"), res.getString("adr_ville"), res.getString("adr_pays")));
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
    

