package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
		app.test();
//    app.launch();
	}

//make sure it works then  comment out the test then run the real one
	private void test() throws SQLException {
		Film film = db.findFilmById(2);
		System.out.println(film);
		Actor actor = db.findActorById(1);
		System.out.println(actor);

	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {

	}

	/*
	 * some method that would have code like: if( !
	 * findFilmsByActorId(someActorId).isEmpty()){.........} else sysout no films
	 * for unemployed actor
	 */

}
