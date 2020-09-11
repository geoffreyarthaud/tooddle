package fr.tooddle.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Sondage {

	private final String emailCreateur;
	private final String titre;
	private SondageStatus status;
	private final List<Creneau> creneaux;
	private final String id;

	public Sondage(String emailCreateur, String titre) {
		id = UUID.randomUUID().toString();
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

	public List<Creneau> getCreneaux() {
		return new ArrayList<>(creneaux);
	}

	public String getId() {
		return id;
	}

}
