package fr.tooddle.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Sondage {

	private final String emailCreateur;
	private final String titre;
	private SondageStatus status;
	private final List<Creneau> creneaux;

	public Sondage(String emailCreateur, String titre) {
		this.emailCreateur = emailCreateur;
		this.titre = titre;
		status = SondageStatus.LANCEMENT;
		creneaux = new ArrayList<>();
	}

	public SondageStatus getStatus() {
		return status;
	}

	public String getTitre() {
		return titre;
	}

	public String getEmailCreateur() {
		return emailCreateur;
	}

	public void ajouterCreneaux(Creneau... creneaux) {
		this.creneaux.addAll(Arrays.asList(creneaux));

	}

	public void diffuser() {
		if (creneaux.size() == 0) {
			throw new IllegalStateException("Au moins un créneau doit être spécifié avant diffusion");
		}
		status = SondageStatus.DIFFUSION;
	}

	public Collection<Creneau> getCreneaux() {
		return new ArrayList<>(creneaux);
	}

}
