package fr.tooddle.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dispo {

	private final Map<String, List<Boolean>> reponsesMap;
	private final List<Creneau> creneaux;

	public Dispo(List<Creneau> creneaux) {
		this.creneaux = creneaux;
		reponsesMap = new HashMap<>();
	}

	public int nbReponses() {
		return reponsesMap.size();
	}

	public void ajouterReponse(String userMail, List<Boolean> answers) {
		if (answers.size() != creneaux.size()) {
			throw new IllegalArgumentException("Taille de réponse non conforme pour le sondage.");
		}
		reponsesMap.put(userMail, answers);
	}

	public List<Boolean> ofMail(String userMail) {
		return reponsesMap.get(userMail);
	}

}
