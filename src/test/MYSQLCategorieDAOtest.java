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

import connexion.ConnexionMYSQL;
import dao.MYSQLCategorieDAO;
import dao.DAOFactory;
import connexion.Persistance;
import pojo.Categorie;

public class MYSQLCategorieDAOtest {
	private Categorie c;

	@BeforeEach
    public void Setup() throws InvalidPropertiesFormatException, SQLException, IOException {
		c=new Categorie(1,"aa", "aaa.png");
		MYSQLCategorieDAO.getInstance().create(c);
    }
    
    @AfterEach
    public void tearDown() throws InvalidPropertiesFormatException, SQLException, IOException {
		MYSQLCategorieDAO.getInstance().delete(c);
	}

	@Test
	public void testSelectExiste() throws Exception {
		int id=c.getId_categorie();	
		Categorie cBdd=MYSQLCategorieDAO.getInstance().getById(id);
		assertNotNull(cBdd);
 }
	
	@Test
	public void testGetById() throws Exception {
		try {
			DAOFactory.getDAOFactory(Persistance.MYSQL).getCategorieDAO().getById(c.getId_categorie());
			}catch(Exception e) {
				fail("erreur de getbyid");
			}
	}
		
	@Test
	public void testCreate() throws Exception {
	    Categorie c2 = new Categorie(1,"aa", "aaa.png");
		try {    
			MYSQLCategorieDAO.getInstance().create(c2);
			}catch(Exception e) {
				fail("Erreur lors de l'insertion");
			}
			//assertEquals(c.getId(),1);
			assertEquals(c.getTitre(),"aa");
			assertEquals(c.getVisuel(),"aaa.png");			
			MYSQLCategorieDAO.getInstance().delete(c2);
	}

	@Test
	public void testDelete() throws Exception {
	    Categorie c3 =new Categorie(10,"bb","bbb.png");
	    MYSQLCategorieDAO.getInstance().create(c3);
	 	int idd =c3.getId_categorie();
	 	assertTrue(MYSQLCategorieDAO.getInstance().delete(c3));
	 	Categorie pr = DAOFactory.getDAOFactory(Persistance.MYSQL).getCategorieDAO().getById(idd);
	 	assertNull(pr);	
	 	assertFalse(MYSQLCategorieDAO.getInstance().delete(pr));
		
	}

	@Test
	public void testUpdate() throws Exception {
		Categorie c2= new Categorie(c.getId_categorie(),"bb","bbb.png");
		DAOFactory.getDAOFactory(Persistance.MYSQL).getCategorieDAO().update(c2);
		Categorie c3 = DAOFactory.getDAOFactory(Persistance.MYSQL).getCategorieDAO().getById(c2.getId_categorie());
		assertEquals("bb", c3.getTitre());
		assertEquals("bbb.png", c3.getVisuel());
		MYSQLCategorieDAO.getInstance().delete(c2);
		MYSQLCategorieDAO.getInstance().delete(c3);
	}
	
}
