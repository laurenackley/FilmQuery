package com.skilldistillery.filmquery.database;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Film_Actor;

public interface DatabaseAccessor {
  public Film findFilmById(int id) throws SQLException;
  public Actor findActorById(int actorId) throws SQLException; //does it need to throw exception???
  public List<Actor> findActorsByFilmId(int filmId) throws SQLException;
  public Film_Actor findFilmIdAndActorId(int actorId, int filmId) throws SQLException;
  
  
//  public Actor findActorById(int actorId);
//  public List<Actor> findActorsByFilmId(int filmId); //not required for homework 
  //gives an example of what is required for homework
  
}
