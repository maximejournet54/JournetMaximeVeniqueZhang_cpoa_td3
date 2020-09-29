package dao;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import connexion.ConnexionMYSQL;
import pojo.LigneCommande;

public class MYSQLLigneCommandeDAO implements DAO<LigneCommande> {
    @Override
    public boolean create(Object T) {
        try {
                LigneCommande.create(T);
                return true;
        } catch (Exception e) {
            return false;
        } 
    }

    @Override
    public boolean delete(Object T) {
        try {
            LigneCommande.delete(T);
            return true;
    } catch (Exception e) {
        return false;
    }
    }

    @Override
    public boolean update(Object T) {
        try {
            LigneCommande.update(T);
            return true;
    } catch (Exception e) {
        return false;
    }
    }

    public LigneCommande getById(int id_commande) {
        try {
            Connection laConnexion = ConnexionMYSQL.creeConnexion();
            Statement requete = laConnexion.createStatement();
            ResultSet res = requete.executeQuery("select * from LigneCommande where id_commande ="+id_commande);
            while (res.next()) {
                int id_produit =res.getInt("id_produit");
                int quantite =res.getInt("quantite");
                double tarif_unitaire =res.getDouble("tarif_unitaire");
                return new LigneCommande(id_commande, id_produit, quantite, tarif_unitaire);
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
    

