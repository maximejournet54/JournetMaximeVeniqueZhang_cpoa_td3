package dao;

import connexion.Persistance;
import pojo.Categorie;
import pojo.Client;
import pojo.Commande;
import pojo.LigneCommande;
import pojo.Produit;

public abstract class DAOFactory {
    public static Object getDAOFactory(Persistance cible) {
        Object daoF = null;
        switch (cible) {
            case MYSQL:
                daoF = new MYSQLFactoryDAO();
                break;
            case LISTE_MEMOIRE:
                daoF = new ListeMemoireFactoryDAO();
                break;
            case SQL:

                break;
            case XML:

            break; 
        }
        return daoF;
        }
        public abstract DAO<Commande> getCommandeDAO();
        public abstract DAO<LigneCommande> getLigneCommandeDAO();
        public abstract DAO<Client> getClientDAO();
        public abstract DAO<Produit> getProduitDAO();
        public abstract DAO<Categorie> getCategorieDAO();
    
}
