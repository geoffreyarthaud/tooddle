package fr.tooddle.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SondageTest {

	private Sondage sondage;
	private String emailCreateur;
	private String titre;

	@BeforeEach
	void createSondage() {
		emailCreateur = "johndoe@tooddle.fr";
		titre = "Atelier de story mapping";
		sondage = new Sondage(emailCreateur, titre);
	}

	@Test
	void givenMinimalInfos_WhenCreateSondage_ThenCorrectInfosAndStatusIsLancement() {
		// GIVEN

		// THEN
		assertThat(sondage).extracting("emailCreateur", "titre", "status")
				.containsExactly(emailCreateur, titre, SondageStatus.LANCEMENT);
	}

	@Test
	void givenMinimalInfos_WhenCreateSondage_ThenHasAUUID() {
		// GIVEN / WHEN -> BeforeEach

		// THEN
		assertThat(sondage.getId()).isNotBlank();

	}

	@Test
	void givenAnotherSondage_whenGetId_ThenGetDifferentId() {
		// GIVEN
		final Sondage sondage2 = new Sondage(emailCreateur, titre);

		// WHEN
		final String id2 = sondage2.getId();

		// THEN
		assertThat(id2).isNotEqualTo(sondage.getId());

	}

	@Test
	void givenSondageWithCreneaux_WhenDiffuser_ThenStatusIsDiffusion() {
		// GIVEN
		final LocalDateTime baseTime1 = LocalDateTime.now().plusDays(1);
		final LocalDateTime baseTime2 = LocalDateTime.now().plusDays(2);
		final LocalDateTime baseTime3 = LocalDateTime.now().plusDays(3);
		sondage.ajouterCreneaux(
				new Creneau(baseTime1, baseTime1.plusHours(2)),
				new Creneau(baseTime2, baseTime2.plusHours(2)),
				new Creneau(baseTime3, baseTime3.plusHours(2)));

		// WHEN
		sondage.diffuser();

		// THEN
		assertThat(sondage.getStatus()).isEqualTo(SondageStatus.DIFFUSION);

	}

	@Tag("rg-42")
	@Tag("rg")
	@Test
	void givenSondageNoCreneau_WhenDiffuser_ThenThrowISE() {
		// GIVEN

		// WHEN / THEN
		assertThatIllegalStateException().isThrownBy(() -> sondage.diffuser());
	}

	@Test
	void givenSondageAddTwoTimesCreneaux_WhenDiffuser_ThenGetAllCreneaux() {
		// GIVEN
		final LocalDateTime baseTime1 = LocalDateTime.now().plusDays(1);
		final LocalDateTime baseTime2 = LocalDateTime.now().plusDays(2);
		final LocalDateTime baseTime3 = LocalDateTime.now().plusDays(3);
		final var creneau1 = new Creneau(baseTime1, baseTime1.plusHours(2));
		final var creneau2 = new Creneau(baseTime2, baseTime2.plusHours(2));
		final var creneau3 = new Creneau(baseTime3, baseTime3.plusHours(2));
		sondage.ajouterCreneaux(creneau1, creneau2);
		sondage.ajouterCreneaux(creneau3);

		// WHEN
		sondage.diffuser();

		// THEN
		assertThat(sondage.getCreneaux()).containsExactly(creneau1, creneau2, creneau3);
	}

}
