package fr.tooddle.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DispoTest {

	private Dispo dispo;

	@BeforeEach
	void createDispo() {
		final LocalDateTime baseTime1 = LocalDateTime.now().plusDays(1);
		final LocalDateTime baseTime2 = LocalDateTime.now().plusDays(2);
		final LocalDateTime baseTime3 = LocalDateTime.now().plusDays(3);

		dispo = new Dispo(Arrays.asList(
				new Creneau(baseTime1, baseTime1.plusHours(2)),
				new Creneau(baseTime2, baseTime2.plusHours(2)),
				new Creneau(baseTime3, baseTime3.plusHours(2))));
	}

	@Test
	void givenSondageDiffuse_whenCreateDispo_ThenGetZeroAnswerCount() {
		// GIVEN / WHEN
		// THEN
		assertThat(dispo.nbReponses()).isEqualTo(0);
	}

	@Test
	void givenANewDispo_whenAddOneAnswer_thenGetOneAnswerCount() {
		// GIVEN
		final String userMail = "user@tooddle.fr";
		final List<Boolean> answers = Arrays.asList(true, false, true);

		// WHEN
		dispo.ajouterReponse(userMail, answers);

		// THEN
		assertThat(dispo.nbReponses()).isEqualTo(1);
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 2, 4, 5 })
	void givenANewDispo_whenAddBadSizeAnswer_thenThrowIAE(int reponseSize) {
		// GIVEN
		final String userMail = "user@tooddle.fr";
		final List<Boolean> answers = Collections.nCopies(reponseSize, false);

		// WHEN / THEN
		assertThatIllegalArgumentException()
				.isThrownBy(() -> dispo.ajouterReponse(userMail, answers));

	}

	@Test
	void givenANewDispo_whenAddOneAnswer_thenRetrieveAnswerFromMail() {
		// GIVEN
		final String userMail = "user@tooddle.fr";
		final List<Boolean> answers = Arrays.asList(true, false, true);

		// WHEN
		dispo.ajouterReponse(userMail, answers);

		// THEN
		assertThat(dispo.ofMail(userMail)).isEqualTo(answers);
	}

}
