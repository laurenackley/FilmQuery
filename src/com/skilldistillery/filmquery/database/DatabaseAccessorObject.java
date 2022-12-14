package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Central";
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	String user = "student";
	String pwd = "student";

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;
		Connection conn = DriverManager.getConnection(URL, user, pwd);
		String sql = "SELECT film.title, film.release_year, film.rating, film.description, language.name FROM film "
				+ "JOIN language ON film.language_id = language.id WHERE film.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, filmId);
		ResultSet filmResult = stmt.executeQuery();
		if (filmResult.next()) {
			film = new Film(); 
			// film.setId(filmResult.getInt("id"));
			film.setTitle(filmResult.getString("film.title"));
			film.setReleaseYear(filmResult.getShort("film.release_year"));
			film.setRating(filmResult.getString("film.rating"));
			film.setDescription(filmResult.getString("film.description"));
			// film.setLanguageId(filmResult.getInt("language_id"));
			// film.setRentalDuration(filmResult.getInt("rental_duration"));
			// film.setRentalRate(filmResult.getInt("rental_rate"));
			// film.setLength(filmResult.getInt("length"));
			// film.setReplacementCost(filmResult.getDouble("replacement_cost"));
			// film.setSpecialFeatures(filmResult.getString("special_features"));
			film.setLanguage(filmResult.getString("language.name"));
		}
		filmResult.close();
		stmt.close();
		conn.close();
		return film;
	}

	public Actor findActorById(int actorId) throws SQLException {

		Actor actor = null;
		Connection conn = DriverManager.getConnection(URL, user, pwd);
		// ...
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, actorId);
		ResultSet actorResult = stmt.executeQuery();
		if (actorResult.next()) {
			actor = new Actor();
			actor.setId(actorResult.getInt("id"));
			actor.setFirstName(actorResult.getString("first_name"));
			actor.setLastName(actorResult.getString("last_name"));
		}
		actorResult.close();
		stmt.close();
		conn.close();
		return actor;
	}

	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		List<Actor> actors = new ArrayList<Actor>();
		Connection conn = DriverManager.getConnection(URL, user, pwd);
		String sql = "SELECT film_actor.actor_id, actor.first_name, actor.last_name FROM actor"
				+ " JOIN film_actor ON film_actor.actor_id = actor.id AND film_actor.film_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("film_actor.actor_id");
			String firstName = rs.getString("actor.first_name");
			String lastName = rs.getString("actor.last_name");
			Actor actor = new Actor(id, firstName, lastName);
			actors.add(actor);
		}
		rs.close();
		stmt.close();
		conn.close();
		return actors;
	}

	public List<Film> findFilmsBySearchWord(String parameter) throws SQLException {
		List<Film> filmSearch = new ArrayList<Film>();
		Connection conn = DriverManager.getConnection(URL, user, pwd);
		String sql = "SELECT film.id, film.title, film.release_year, film.rating, film.description, language.name FROM film "
				+ "JOIN language ON film.language_id = language.id WHERE film.title LIKE ? OR film.description LIKE ?";
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setString(1, "%" + parameter + "%");
		stmt.setString(2, "%" + parameter + "%");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			int filmSearchId = rs.getInt("film.id");
			String title = rs.getString("film.title");
			short releaseYear = rs.getShort("film.release_year");
			String rating = rs.getString("film.rating");
			String description = rs.getString("film.description");
			String language = rs.getString("language.name");
			Film filmS = new Film(filmSearchId, title, releaseYear, rating, description, language);
			filmSearch.add(filmS);
		}
		
		rs.close();
		stmt.close();
		conn.close();
		return filmSearch;
	}

}
