package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connexion.ConnexionMYSQL;
import pojo.Categorie;

public class MYSQLCategorieDAO implements DAO<Categorie>{
	
    @Override
	public boolean create(Object T) {
        try {
            Categorie.create(T);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean delete(Object T) {
        try {
            Categorie.delete(T);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean update(Object T) {
        try {
            Categorie.update(T);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Categorie getById(int id_categorie) {
        try {
            Connection laConnexion = ConnexionMYSQL.creeConnexion();
            Statement requete = laConnexion.createStatement();
            ResultSet res = requete.executeQuery("select id_categorie, titre, visuel from Categorie where id_categorie ="+id_categorie);
            while (res.next()) {
                int id =res.getInt("id_categorie");
                String titre=res.getString("titre");
                String visuel=res.getString("visuel");
                return new Categorie(id,titre,visuel);
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
