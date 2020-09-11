package fr.tooddle.infrastructure;

import fr.tooddle.model.Dispo;

public interface DispoRepository {

	Dispo findBySondageId(String sondageId);

}
