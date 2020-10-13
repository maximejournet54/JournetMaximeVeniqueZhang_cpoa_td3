package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pojo.Categorie;
import pojo.Produit;
import dao.MYSQLProduitDAO;
import dao.DAOFactory;
import connexion.Persistance;

public class MYSQLProduitDAOtest {
	private Produit p;
    
    @BeforeEach
    public void Setup() throws InvalidPropertiesFormatException, SQLException, IOException {
    	Categorie cat=new Categorie(3,"Watch","watch.png");
    	p=new Produit(1, "aaa", "222xx",(double) 1.0,"xxx.png",cat);
		MYSQLProduitDAO.getInstance().create(p);
    }
    
    @AfterEach
    public void tearDown() throws InvalidPropertiesFormatException, SQLException, IOException {
	MYSQLProduitDAO.getInstance().delete(p);
    }
    
	@Test
	public void testSelectExiste() throws Exception {
	int id=p.getId_produit();
	Produit cBdd=MYSQLProduitDAO.getInstance().getById(id);
	assertNotNull(cBdd);
	}

	@Test
	public void testGetbyid() throws Exception {
	    try {
		DAOFactory.getDAOFactory(Persistance.MYSQL).getProduitDAO().getById(p.getId_produit());
		}catch(Exception e) {
	    	fail("erreur de getbyid");
	    }   
	}

	@Test
	public void testCreate() throws Exception {
		Categorie cat=new Categorie(3,"Watch","watch.png");
		Produit c2 = new Produit(1, "aaa", "222xx",(double) 1.0,"xxx.png",cat);
		try { 
			MYSQLProduitDAO.getInstance().create(c2);
		}catch(Exception e) {
		    fail("Erreur lors de l'insertion");
		}
		assertEquals(p.getNom(),"aaa");
		assertEquals(p.getDescription(),"222xx");
		assertEquals(p.getTarif(),1.0,1.0); //utilisation d'un delta car float
		assertEquals(p.getVisuel(),"xxx.png");
		assertEquals(p.getCategorie().getId(),3);
		MYSQLProduitDAO.getInstance().delete(c2);
	}

	@Test
	public void testDelete() throws Exception {
	    
		Categorie cat=new Categorie(3,"Watch","watch.png");
		Produit p2 =new Produit(1, "aaa", "222xx",(double) 1.0,"xxx.png",cat);
	    MYSQLProduitDAO.getInstance().create(p2);
		//int idd =p2.getIdProduit();
		assertTrue(MYSQLProduitDAO.getInstance().delete(p2));
		//CMProduit pr = DAOFactory.getDAOFactory(Persistance.MYSQL).getProduitDAO().getById(idd);
		//assertNull(p2);
		assertFalse(MYSQLProduitDAO.getInstance().delete(p2));	
	}
	
	@Test
	public void testUpdate() throws Exception {
		Categorie cat=new Categorie(3,"Watch","watch.png");
		Produit p2= new Produit(p.getId_produit(),"bbb","333zz",(double)2.0,"yyy.png",cat);
		DAOFactory.getDAOFactory(Persistance.MYSQL).getProduitDAO().update(p2);
		//Produit p3 = DAOFactory.getDAOFactory(Persistance.MYSQL).getProduitDAO().getById(p2.getId_produit());
		assertEquals("bbb", p2.getNom());
		assertEquals("333zz", p2.getDescription());
		assertEquals((float)1,0, p2.getTarif());
		assertEquals("yyy.png", p2.getVisuel());
		assertEquals(3, p2.getCategorie().getId());
	}

}
