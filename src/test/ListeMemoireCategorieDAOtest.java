package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

import org.junit.Before;


import java.util.ArrayList;

import connexion.Persistance;

import dao.DAOFactory;
import dao.ListeMemoireCategorieDAO;

import pojo.Categorie;
public class ListeMemoireCategorieDAOtest {
	private Categorie c;

	@Before
    public void Setup() throws Exception {	
		c =new Categorie(5, "chaussures", "titre");
		ListeMemoireCategorieDAO.getInstance().create(c);
	}
	
	@Test
	public void testSelectExiste() throws Exception {
		int id=c.getId_categorie();
		Categorie c=ListeMemoireCategorieDAO.getInstance().getById(id);
		assertNotNull(c);
 }

	@Test
	public void testCreate() {
		assertEquals(c.getId_categorie(),5);
		assertEquals(c.getVisuel(),"chaussures");
		assertEquals(c.getTitre(),"titre"); 
	}

	@Test
	public void testUpdate() throws Exception{
		Categorie c2= new Categorie(c.getId_categorie(),"fdfdfd","dfdfdfd");
		((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getCategorieDAO().update(c2);
		Categorie c3 = ((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getCategorieDAO().getById(c2.getId_categorie());
		
		assertEquals("titre", c3.getTitre());
		assertEquals("chaussures", c3.getVisuel());

	}

	@Test
	public void testDelete() throws Exception {
		assertTrue((ListeMemoireCategorieDAO.getInstance().delete(c)), "");
		int id = c.getId_categorie();

		try {
			((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getCategorieDAO().getById(id);
		fail("La categorie existe toujours");
		}catch(Exception e){

		}
		
		try {
		ListeMemoireCategorieDAO.getInstance().delete(c);
		fail("La categorie existe toujours");
		}
		catch (Exception e){
		    
		}	
	}

	@Test
	public void testgetById() throws Exception {
		try {
			((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getCategorieDAO().getById(c.getId_categorie());
		} catch(Exception e) {
				fail("Erreur lors de la récupération");
			}
	}

	@Test
	public void testfindAll() throws Exception {
			Categorie c2=new Categorie(1, "nomm", "656565");
			ListeMemoireCategorieDAO l = (ListeMemoireCategorieDAO) ((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getCategorieDAO();
			ArrayList<Categorie> ar = new ArrayList<Categorie>(l.findAll());
			ar.add(c2);
			l.create(c2);	
			assertEquals(l.findAll(), ar);
			((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getCategorieDAO().delete(c);
	}
	
}
