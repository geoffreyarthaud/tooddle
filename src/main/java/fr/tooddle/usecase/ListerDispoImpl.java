package fr.tooddle.usecase;

import java.util.ArrayList;
import java.util.List;

import fr.tooddle.infrastructure.DispoRepository;
import fr.tooddle.infrastructure.SondageRepository;
import fr.tooddle.model.Creneau;
import fr.tooddle.model.Dispo;
import fr.tooddle.model.Sondage;

public class ListerDispoImpl implements ListerDispoUseCase {

	private final DispoRepository dispoRepository;

	private final SondageRepository sondageRepository;

	public ListerDispoImpl(DispoRepository dispoRepository, SondageRepository sondageRepository) {
		this.dispoRepository = dispoRepository;
		this.sondageRepository = sondageRepository;
	}

	@Override
	public List<Creneau> listerDispoSondageUser(String sondageId, String userMail) {
		final Dispo dispo = dispoRepository.findBySondageId(sondageId);
		final Sondage sondage = sondageRepository.findById(sondageId);
		final List<Boolean> dispos = dispo.ofMail(userMail);
		final List<Creneau> creneaux = new ArrayList<>();
		for (int i = 0; i < dispos.size(); i++) {
			if (dispos.get(i)) {
				creneaux.add(sondage.getCreneaux().get(i));
			}
		}
		return creneaux;
	}

}
