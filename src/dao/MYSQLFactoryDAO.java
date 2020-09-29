package dao;

import pojo.Categorie;
import pojo.Client;
import pojo.Commande;
import pojo.LigneCommande;
import pojo.Produit;

public class MYSQLFactoryDAO extends DAOFactory{
    public DAO<Commande> getCommandeDAO() {
        return ListeMemoireCommandeDAO.getInstance();
    }

    public DAO<LigneCommande> getLigneCommandeDAO() {
        return ListeMemoireLigneCommandeDAO.getInstance();
    }

    public DAO<Client> getClientDAO() {
        return ListeMemoireClientDAO.getInstance();
    }

    public DAO<Produit> getProduitDAO() {
        return ListeMemoireProduitDAO.getInstance();
    }

    public DAO<Categorie> getCategorieDAO() {
        return ListeMemoireCategorieDAO.getInstance();
    }
}


