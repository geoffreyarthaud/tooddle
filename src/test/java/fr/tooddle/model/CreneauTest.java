package fr.tooddle.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CreneauTest {

	// Un créneau est une durée caractérisée par une heure de début
	// et une heure de fin strictement supérieure. Sa durée peut être calculée.

	@Test
	void givenTwoSameTime_ThenGetIAE() {
		// GIVEN
		final var time = LocalDateTime.now();

		// WHEN/THEN
		assertThatIllegalArgumentException().isThrownBy(() -> {
			new Creneau(time, time);
		});

	}

	@Test
	void givenMoreThan12hDuration_ThenGetIAE() {
		// GIVEN
		final var time = LocalDateTime.now();

		// WHEN/THEN
		assertThatIllegalArgumentException().isThrownBy(() -> {
			new Creneau(time, time.minusHours(2));
		});
	}

	@Test
	void givenNullStartTime_ThenGetNPE() {
		// GIVEN
		final var time = LocalDateTime.now();

		// WHEN/THEN
		assertThatNullPointerException().isThrownBy(() -> {
			new Creneau(null, time);
		});
		assertThatNullPointerException().isThrownBy(() -> {
			new Creneau(time, null);
		});
	}

	@ParameterizedTest
	@ValueSource(ints = { 2, 3, 4 })
	void givenCreneauFrom8And8PlusN_whenGetDuration_thenGetNhours(int nDuration) {
		// GIVEN
		final var fromDate = LocalDateTime.of(2020, 9, 5, 8, 0);
		final var creneau = new Creneau(fromDate, fromDate.plusHours(nDuration));

		// WHEN
		final var duree = creneau.duree();

		// THEN
		assertThat(duree).hasHours(nDuration);
	}

	@Test
	void givenTwoEquivalentCreneau_thenObtainEquality() {
		// GIVEN
		final var startDate = LocalDateTime.of(2020, 9, 5, 10, 0);
		final var equivStartDate = LocalDateTime.of(2020, 9, 5, 8, 0).plusHours(2);

		final var endDate = LocalDateTime.of(2020, 9, 5, 11, 0);
		final var equivEndDate = LocalDateTime.of(2020, 9, 5, 9, 0).plusHours(2);

		// WHEN
		final var creneau = new Creneau(startDate, endDate);
		final var equivCreneau = new Creneau(equivStartDate, equivEndDate);
		// THEN
		assertThat(creneau).isEqualTo(equivCreneau);

	}

}
