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
import com.skilldistillery.filmquery.entities.Film_Actor;

public class DatabaseAccessorObject implements DatabaseAccessor {
	public static void main(String[] args) {
		DatabaseAccessorObject dbao = new DatabaseAccessorObject();
		try {
			dbao.findFilmById(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Central";
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	String user = "student";
	String pwd = "student";


  @Override
  public Film findFilmById(int filmId) throws SQLException {
	  Film film = null;
	  Connection conn = DriverManager.getConnection(URL, user, pwd);
	  String sql = "SELECT id, title, description, release_year, language_id, rental_duration, rental_rate,"
	  		+ "length, replacement_cost, rating, special_features FROM film WHERE id = ?";
	  PreparedStatement stmt = conn.prepareStatement(sql);
	  
	  
	  stmt.setInt(1,filmId);
	  ResultSet filmResult = stmt.executeQuery();
	  if (filmResult.next()) {
		  film = new Film(); // Create the object
		  // Here is our mapping of query columns to our object fields:
		  film.setId(filmResult.getInt("id"));
		  film.setTitle(filmResult.getString("title"));
		  film.setDesc(filmResult.getString("description"));
		  film.setReleaseYear(filmResult.getShort("release_year"));
		  film.setLangId(filmResult.getInt("language_id"));
		  film.setRentDur(filmResult.getInt("rental_duration"));
		  film.setRate(filmResult.getInt("rental_rate"));
		  film.setLength(filmResult.getInt("length"));
		  film.setRepCost(filmResult.getDouble("replacement_cost"));
		  film.setRating(filmResult.getString("rating"));
		  film.setFeatures(filmResult.getString("special_features"));
		  
		  
		  
	  }	    
	  filmResult.close();
	  stmt.close();
	  conn.close();
    return film;
  }

 
  public Actor findActorById(int actorId) throws SQLException {
	  
	  Actor actor = null;
	  Connection conn = DriverManager.getConnection(URL, user, pwd);
	  //...
	  String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
	  PreparedStatement stmt = conn.prepareStatement(sql);

	  
	  stmt.setInt(1,actorId);
	  ResultSet actorResult = stmt.executeQuery();
	  if (actorResult.next()) {
	    actor = new Actor(); // Create the object
	    // Here is our mapping of query columns to our object fields:
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
	List<Actor> findActorFilm = new ArrayList<Actor>();
	 Connection conn = DriverManager.getConnection(URL, user, pwd);
	 String sql = "SELECT actor.id, actor.first_name, actor.last_name, film.id, film.title, film.description,"
	 		+ "film.release_year, film.language_id, film.rental_duration, film.rental_rate,"
	 		+ "film.length, film.replacement_cost, film.rating, film.special_features "
	 		+ "FROM actor JOIN film_actor ON film_actor.actor_id = actor.id JOIN film ON film_actor.film_id=film.id"
	 		+ "WHERE actor.id = ?";
	  PreparedStatement stmt = conn.prepareStatement(sql);

	  
  stmt.setInt(1, filmId);
	  ResultSet actorResult = stmt.executeQuery();
	
	
	
	return findActorFilm;
}


@Override
public Film_Actor findFilmIdAndActorId(int actorId, int filmId) throws SQLException {
	  Film_Actor filmActor = null;
	  Connection conn = DriverManager.getConnection(URL, user, pwd);
	  //...
	  String sql = "SELECT actor_id, film_id FROM film_actor WHERE actor_id = ?";
	  PreparedStatement stmt = conn.prepareStatement(sql);

	  
	  stmt.setInt(1,actorId);
	  ResultSet filmActorResult = stmt.executeQuery();
	  if (filmActorResult.next()) {
	    filmActor = new Film_Actor(); // Create the object
	    // Here is our mapping of query columns to our object fields:
	    filmActor.setActorId(filmActorResult.getInt("actor_id"));
	    filmActor.setFilmId(filmActorResult.getInt("film_id"));
	  }	    
	  filmActorResult.close();
	  stmt.close();
	  conn.close();
	  return filmActor;
}
  
//  @Override
//  public List<Film> findFilmsByActorId(int actorId) {
//	  List<Film> films = new ArrayList<>();
//	  try {
//	    Connection conn = DriverManager.getConnection(URL, user, pwd);
//	    String sql = "SELECT * FROM film JOIN film_actor ON film.id = film_actor.film_id WHERE actor_id = ?";
//	    PreparedStatement stmt = conn.prepareStatement(sql);
//	    stmt.setInt(1, actorId);
//	    ResultSet rs = stmt.executeQuery();
//	    while (rs.next()) {
//	      int filmId = rs.getInt("id");
//	      String title = rs.getString("title");
//	      String desc = rs.getString("description");
//	      short releaseYear = rs.getShort("release_year");
//	      int langId = rs.getInt("language_id");
//	      int rentDur = rs.getInt("rental_duration");
//	      double rate = rs.getDouble("rental_rate");
//	      int length = rs.getInt("length");
//	      double repCost = rs.getDouble("replacement_cost");
//	      String rating = rs.getString("rating");
//	      String features = rs.getString("special_features");
//	      Film film = new Film(filmId, title, desc, releaseYear, langId,
//	                           rentDur, rate, length, repCost, rating, features);
//	      films.add(film);
//	    }
//	    rs.close();
//	    stmt.close();
//	    conn.close();
//	  } catch (SQLException e) {
//	    e.printStackTrace();
//	  }
//	  return films;
//	}
}
