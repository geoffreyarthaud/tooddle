package tooddle;

import java.time.Duration;
import java.time.LocalDateTime;

public class Creneau {

	private final LocalDateTime start;

	private final LocalDateTime end;

	public Creneau(LocalDateTime start, LocalDateTime end) {

		final Duration between = Duration.between(start, end);
		if (between.isNegative() || between.isZero()) {
			throw new IllegalArgumentException("End time must be strictly later from start time.");
		}
		this.start = start;
		this.end = end;
	}

	public Duration duree() {
		return Duration.between(start, end);
	}

	public LocalDateTime getStart() {
		return start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

}
