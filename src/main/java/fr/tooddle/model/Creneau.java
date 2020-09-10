package fr.tooddle.model;

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
		return Duration.between(getStart(), getEnd());
	}

	public LocalDateTime getStart() {
		return start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (end == null ? 0 : end.hashCode());
		result = prime * result + (start == null ? 0 : start.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Creneau other = (Creneau) obj;
		if (end == null) {
			if (other.end != null) {
				return false;
			}
		} else if (!end.equals(other.end)) {
			return false;
		}
		if (start == null) {
			if (other.start != null) {
				return false;
			}
		} else if (!start.equals(other.start)) {
			return false;
		}
		return true;
	}

}
