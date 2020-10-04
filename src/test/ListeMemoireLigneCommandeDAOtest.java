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
import dao.ListeMemoireLigneCommandeDAO;

import pojo.LigneCommande;

class ListeMemoireLigneCommandeDAOtest {
	
	private LigneCommande c;

	@Before
    public void Setup() throws Exception {	
		c =new LigneCommande(5,5, 5,(double) 5.0);
		ListeMemoireLigneCommandeDAO.getInstance().create(c);
	}
	
	@Test
	public void testSelectExiste() throws Exception {
		int id=c.getId_commande();
		LigneCommande c=ListeMemoireLigneCommandeDAO.getInstance().getById(id);
		assertNotNull(c);
 }

	@Test
	public void testCreate() {
		//assertEquals(c.getId_commande(),5);
		assertEquals(c.getId_produit(),5);
		assertEquals(c.getQuantite(),5);
		//assertEquals(c.getTarif_unitaire(), 5.0,5.0); 
	}

	@Test
	public void testUpdate() throws Exception{
		LigneCommande c2= new LigneCommande(c.getId_commande(),6,6,(double) 6.0);
		((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getLigneCommandeDAO().update(c2);
		LigneCommande c3 = ((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getLigneCommandeDAO().getById(c2.getId_commande());
		
		assertEquals(6, c3.getId_produit());
		assertEquals(6, c3.getQuantite());
		//assertEquals((double) 6.0, c3.getTarif_unitaire());

	}

	@Test
	public void testDelete() throws Exception {
		assertTrue((ListeMemoireLigneCommandeDAO.getInstance().delete(c)), "");
		int id = c.getId_commande();

		try {
			((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getLigneCommandeDAO().getById(id);
		fail("La Ligne de commande existe toujours");
		}catch(Exception e){

		}
		
		try {
		ListeMemoireLigneCommandeDAO.getInstance().delete(c);
		fail("La Ligne de commande existe toujours");
		}
		catch (Exception e){
		    
		}	
	}

	@Test
	public void testgetById() throws Exception {
		try {
			((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getLigneCommandeDAO().getById(c.getId_commande());
		} catch(Exception e) {
				fail("Erreur lors de la récupération");
			}
	}

	@Test
	public void testfindAll() throws Exception {
			LigneCommande c2=new LigneCommande(5, 5, 5,(double) 5.0);
			ListeMemoireLigneCommandeDAO l = (ListeMemoireLigneCommandeDAO) ((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getLigneCommandeDAO();
			ArrayList<LigneCommande> ar = new ArrayList<LigneCommande>();
			ar.add(c2);
			l.create(c2);	
			assertEquals(l.findAll(), ar);
			((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getLigneCommandeDAO().delete(c);
	}

}
