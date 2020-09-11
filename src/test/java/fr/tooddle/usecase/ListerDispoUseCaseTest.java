package fr.tooddle.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.tooddle.infrastructure.DispoRepository;
import fr.tooddle.infrastructure.SondageRepository;
import fr.tooddle.model.Creneau;
import fr.tooddle.model.CreneauTest;
import fr.tooddle.model.Dispo;
import fr.tooddle.model.Sondage;

@ExtendWith(MockitoExtension.class)
class ListerDispoUseCaseTest {

	private ListerDispoUseCase listerDispo;

	@Mock
	private DispoRepository dispoRepo;

	@Mock
	private SondageRepository sondageRepo;

	@BeforeEach
	void setupListerDispoUseCase() {
		listerDispo = new ListerDispoImpl(dispoRepo, sondageRepo);
	}

	@Test
	void givenACorrectSondageId_whenListerDispo_thenGetDispos() {
		// GIVEN
		final String userMail = "user@tooddle.fr";
		final Sondage sondage = new Sondage("orga@tooddle.fr", "titre");
		sondage.ajouterCreneaux(CreneauTest.TEST_CRENEAU_LIST);

		final Dispo dispo = new Dispo(sondage.getCreneaux());
		dispo.ajouterReponse(userMail, Arrays.asList(true, false, true));

		final String sondageId = sondage.getId();

		when(sondageRepo.findById(sondageId)).thenReturn(sondage);
		when(dispoRepo.findBySondageId(sondageId)).thenReturn(dispo);

		// WHEN
		final List<Creneau> dispoList = listerDispo.listerDispoSondageUser(sondageId, userMail);

		// THEN
		assertThat(dispoList).containsExactly(
				CreneauTest.TEST_CRENEAU_LIST[0],
				CreneauTest.TEST_CRENEAU_LIST[2]);
	}

}
