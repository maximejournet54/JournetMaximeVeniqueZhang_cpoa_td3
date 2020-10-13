package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pojo.Categorie;
import pojo.Client;
import pojo.Commande;
import pojo.LigneCommande;
import pojo.Produit;

import dao.MYSQLCategorieDAO;
import dao.MYSQLClientDAO;
import dao.MYSQLCommandeDAO;
import dao.MYSQLLigneCommandeDAO;
import dao.MYSQLProduitDAO;
import dao.DAOFactory;
import connexion.Persistance;

public class MYSQLLigneCommandeDAOtest {
	private LigneCommande l;
	private Categorie cat;
	private Produit p;
	private Client cli;
	private Commande c;

    @BeforeEach
    public void Setup() throws InvalidPropertiesFormatException, SQLException, IOException {
    	cat=new Categorie("Watch","watch.png");
    	p=new Produit("aaa", "222xx",(double) 1.0,"xxx.png",cat);
    	cli=new Client("Jack","Ma");
    	c=new Commande("2020-01-01" ,cli);
    	l=new LigneCommande(c,p,5);
		MYSQLCategorieDAO.getInstance().create(cat);
		MYSQLProduitDAO.getInstance().create(p);
		MYSQLClientDAO.getInstance().create(cli);
		MYSQLCommandeDAO.getInstance().create(c);
		MYSQLLigneCommandeDAO.getInstance().create(l);
    }
    
    @AfterEach
    public void tearDown() throws InvalidPropertiesFormatException, SQLException, IOException {
		MYSQLLigneCommandeDAO.getInstance().delete(l);
		MYSQLCommandeDAO.getInstance().delete(c);
		MYSQLProduitDAO.getInstance().delete(p);
		MYSQLCategorieDAO.getInstance().delete(cat);
		MYSQLClientDAO.getInstance().delete(cli);
    }
    
	@Test
	public void testSelectExiste() throws Exception {
		assertNotNull(l);
	 }
	 
	@Test
	public void testGetbyid() throws Exception {
	    try {
			DAOFactory.getDAOFactory(Persistance.MYSQL).getLigneCommandeDAO().getById2(c.getId_commande(),p.getId_produit());
		}catch(Exception e) {
	    	fail("erreur de getbyid");
	    }
	}

	@Test
	public void testCreate() throws Exception {
		cat=new Categorie("Watch","watch.png");
    	p=new Produit("aaa", "222xx",(double) 1.0,"xxx.png",cat);
    	cli=new Client("Jack","Ma");
    	c=new Commande("2020-01-01" ,cli);
    	l=new LigneCommande(c,p,6);
		try {
			MYSQLLigneCommandeDAO.getInstance().create(l);
		}catch(Exception e) {
		    fail("Erreur lors de l'insertion");
		}
		assertEquals(l.getQuantite(),6);
		assertEquals(l.getCommande().getIdClient().getNom(),"Jack");
		MYSQLLigneCommandeDAO.getInstance().delete(l);
		MYSQLCommandeDAO.getInstance().delete(c);
		MYSQLProduitDAO.getInstance().delete(p);
		MYSQLCategorieDAO.getInstance().delete(cat);
		MYSQLClientDAO.getInstance().delete(cli);
	}
	@Test
	public void testDelete() throws Exception {
		try {
			MYSQLLigneCommandeDAO.getInstance().delete(l);
		}catch(Exception e) {
				fail("Ligne de commande n'a pas ztz supprimee");
		}	
	}
	
	@Test
	public void testUpdate() throws Exception {
		cat=new Categorie("Watch","watch.png");
    	p=new Produit("aaa", "222xx",(float) 1.0,"xxx.png",cat);
    	cli=new Client("Jack","Mam");
    	c=new Commande("2020-01-01" ,cli);
        l=new LigneCommande(c,p,5);
	 	DAOFactory.getDAOFactory(Persistance.MYSQL).getLigneCommandeDAO().update(l);
		assertEquals(l.getQuantite(),5);
		assertEquals(l.getCommande().getIdClient().getNom(),"Jack");	
		MYSQLLigneCommandeDAO.getInstance().delete(l);
		MYSQLCommandeDAO.getInstance().delete(c);
		MYSQLProduitDAO.getInstance().delete(p);
		MYSQLCategorieDAO.getInstance().delete(cat);
		MYSQLClientDAO.getInstance().delete(cli);
	}
	
}
