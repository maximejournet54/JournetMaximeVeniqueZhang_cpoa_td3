package dao;

import java.util.HashMap;

import pojo.LigneCommande;

public class ListeMemoireLigneCommandeDAO extends MYSQLLigneCommandeDAO{
    private static ListeMemoireLigneCommandeDAO instance;
	private HashMap<Integer, LigneCommande> donnees;

	public static  ListeMemoireLigneCommandeDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireLigneCommandeDAO();
		}
		return instance;
	}

	private ListeMemoireLigneCommandeDAO() {
		this.donnees = new HashMap<Integer,LigneCommande>();
	}

	@SuppressWarnings("unlikely-arg-type")
	public boolean create(LigneCommande objet) {
		objet.setId(3);
		while (this.donnees.entrySet().contains(objet)) {
			objet.setId(objet.getId() + 1);
		}
		try {
			this.donnees.put(objet.getId(),objet);
				return true;
		} catch (Exception e) {
			return false;
		} 
	}

	public boolean update(LigneCommande objet) {
		int idx = objet.getId();
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'une ligne de commande inexistante");
		} else {
			this.donnees.replace(idx,objet);
		}
		return true;
	}

	public boolean delete(LigneCommande objet) {
		LigneCommande supprime;
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = objet.getId();
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une ligne de commande inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}
		return objet.equals(supprime);
	}

	public LigneCommande getById(int id) {
		if (id == -1) {
			throw new IllegalArgumentException("Aucune ligne de commande ne possède cet identifiant");
		} else {
			return this.donnees.get(id);
		}
	}

	public HashMap<Integer, LigneCommande> findAll() {
		return (HashMap<Integer, LigneCommande>) this.donnees;
	} 
}
