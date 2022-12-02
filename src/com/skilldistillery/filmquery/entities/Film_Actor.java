package com.skilldistillery.filmquery.entities;

import java.util.Objects;

public class Film_Actor {
	private int actorId;
	private int filmId;

	public Film_Actor() {}
	
	public Film_Actor( int actorId, int filmId) {
		this.actorId = actorId;
		this.filmId = filmId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actorId, filmId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film_Actor other = (Film_Actor) obj;
		return actorId == other.actorId && filmId == other.filmId;
	}

	public int getActorId() {
		return actorId;
	}

	public void setActorId(int actorId) {
		this.actorId = actorId;
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}
	
}
