package dao;

import java.sql.*;

import connexion.ConnexionMYSQL;
import pojo.Client;

public class MYSQLClientDAO implements DAO<Client>{
    @Override
    public boolean create(Object T) {
        try {
            Client.create(T);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean delete(Object T) {
        try {
            Client.delete(T);
            return true;

        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean update(Object T) {
        try {
            Client.update(T);
            return true;

        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Client getById(int id_client) {
        try {
            Connection laConnexion = ConnexionMYSQL.creeConnexion();
            Statement requete = laConnexion.createStatement();
            ResultSet res = requete.executeQuery("select * from Client where id_client ="+id_client);
            while (res.next()) {
                int id =res.getInt("id_client");
                String nom=res.getString("nom");
                String prenom=res.getString("prenom");
                String identifiant=res.getString("identifiant");
                String mdp=res.getString("mot_de_passe");
                int num=res.getInt("adr_numero");
                String voie=res.getString("adr_voie");
                int cp=res.getInt("adr_code_postal");
                String ville=res.getString("adr_ville");
                String pays=res.getString("adr_pays");
                return new Client(id,nom,prenom,identifiant,mdp,num,voie,cp,ville,pays);
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
        return null;
    }
}
