package fr.tooddle.infrastructure;

import fr.tooddle.model.Sondage;

public interface SondageRepository {

	Sondage findById(String sondageId);

}
