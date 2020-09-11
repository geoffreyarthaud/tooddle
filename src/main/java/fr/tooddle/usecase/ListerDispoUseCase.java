package fr.tooddle.usecase;

import java.util.List;

import fr.tooddle.model.Creneau;

public interface ListerDispoUseCase {

	List<Creneau> listerDispoSondageUser(String correctId, String userMail);

}
