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
import dao.ListeMemoireCommandeDAO;

import pojo.Commande;

class ListeMemoireCommandeDAOtest {

	private Commande c;

	@Before
    public void Setup() throws Exception {	
		c =new Commande(5, "12/12/2020", 5);
		ListeMemoireCommandeDAO.getInstance().create(c);
	}
	
	@Test
	public void testSelectExiste() throws Exception {
		int id=c.getId_commande();
		Commande c=ListeMemoireCommandeDAO.getInstance().getById(id);
		assertNotNull(c);
 }

	@Test
	public void testCreate() {
		assertEquals(c.getId_commande(),5);
		assertEquals(c.getDate_commande(),"12/12/2020");
		assertEquals(c.getId_client(),5); 
	}

	@Test
	public void testUpdate() throws Exception{
		Commande c2= new Commande(c.getId_commande(),"12/12/2020",5);
		((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getCommandeDAO().update(c2);
		Commande c3 = ((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getCommandeDAO().getById(c2.getId_commande());
		
		assertEquals("12/12/2020", c3.getDate_commande());
		assertEquals(5, c3.getId_client());

	}

	@Test
	public void testDelete() throws Exception {
		assertTrue((ListeMemoireCommandeDAO.getInstance().delete(c)), "");
		int id = c.getId_commande();

		try {
			((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getCommandeDAO().getById(id);
		fail("La commande existe toujours");
		}catch(Exception e){

		}
		
		try {
		ListeMemoireCommandeDAO.getInstance().delete(c);
		fail("La commande existe toujours");
		}
		catch (Exception e){
		    
		}	
	}

	@Test
	public void testgetById() throws Exception {
		try {
			((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getCommandeDAO().getById(c.getId_commande());
		} catch(Exception e) {
				fail("Erreur lors de la récupération");
			}
	}

	@Test
	public void testfindAll() throws Exception {
		Commande c2=new Commande(5, "12/12/2020", 5);
			ListeMemoireCommandeDAO l = (ListeMemoireCommandeDAO) ((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getCommandeDAO();
			ArrayList<Commande> ar = new ArrayList<Commande>(l.findAll());
			ar.add(c2);
			l.create(c2);	
			assertEquals(l.findAll(), ar);
			((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getCommandeDAO().delete(c);
	}
	
}
