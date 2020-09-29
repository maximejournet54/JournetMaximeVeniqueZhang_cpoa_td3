package dao;

import java.util.ArrayList;
import java.util.List;

import pojo.Client;

public class ListeMemoireClientDAO extends MYSQLClientDAO{
    private static ListeMemoireClientDAO instance;
	private List<Client> donnees;

	public static ListeMemoireClientDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireClientDAO();
		}
		return instance;
	}

	private ListeMemoireClientDAO() {
		this.donnees = new ArrayList<Client>();
		this.donnees.add(new Client(5, "Journet", "Maxime", "journet9u@ul", "mdp", 4, "rue Belle Isle", 57000, "Metz", "France"));
	}

	public boolean create(Client objet) {
		objet.setId(3);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.donnees.contains(objet)) {
			objet.setId(objet.getId() + 1);
		}
		boolean ok = this.donnees.add(objet);
		return ok;
	}

	public boolean update(Client objet) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un client inexistant");
		} else {
			this.donnees.set(idx, objet);
		}
		return true;
	}

	public boolean delete(Client objet) {
		Client supprime;
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un client inexistant");
		} else {
			supprime = this.donnees.remove(idx);
		}
		return objet.equals(supprime);
	}

	public Client getById(int id) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(new Client(id, "Journet", "Maxime", "journet9u@ul", "mdp", 4, "rue Belle Isle", 57000, "Metz", "France"));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun client ne possède cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	public ArrayList<Client> findAll() {
		return (ArrayList<Client>) this.donnees;
	}
    
}
