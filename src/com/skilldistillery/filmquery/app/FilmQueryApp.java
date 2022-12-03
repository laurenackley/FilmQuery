package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

////make sure it works then  comment out the test then run the real one
//	private void test() throws SQLException {
//		Film film = db.findFilmById(2);
//		System.out.println(film);
//		Actor actor = db.findActorById(1);
//		System.out.println(actor);
//	}

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) throws SQLException {
		int answer = 0;
		boolean keepGoing = true;
		System.out.println("Welcome to blockbuster.");
		while (keepGoing == true) {
			System.out.println("Enter 'one' if you'd like to search for a film by a number between 1-1000 \n"
					+ "or 'two' to search for a film by a keyword \n" + "or 'quit' if you'd like to quit");
			String response = input.next();

			switch (response.toUpperCase()) {
			case "ONE":
				System.out.println("Enter a movie id:");
				answer = input.nextInt();
//			if (answer <= 0) {
//				System.out.println("Thanks for playing!");
//				k
//			}
				Film film = db.findFilmById(answer);
				if (film == null) {
					System.out.println("No such film, try adjusting your inquiry. Back to main menu.");
				} else {
					System.out.println(film);
					List<Actor> actor = db.findActorsByFilmId(answer);
					System.out.println("Actors and Actresses include:");
					System.out.println(actor);
				}
				break;
			case "TWO":
				System.out.println("What phrase would you like to see if it is in a movie?");
				String phrase = input.next();
				List<Film> filmS = db.findFilmsBySearchWord(phrase);
					
				int filmId = 0;
			if(filmS.isEmpty()) {
					System.out.println("No films with that keyword");
				}
				else {
					for (Film f : filmS) {
						System.out.println("Title: "+f.getTitle()+", Release Year: "+f.getReleaseYear()+", Rated: "+ f.getRating()
						+ ", Description: "+f.getDescription()+", Language: "+ f.getLanguage());
						filmId = f.getId();
						List<Actor> actorSearch = db.findActorsByFilmId(filmId);
						System.out.println("Actors and Actresses include:");
						System.out.println(actorSearch);
					}
					}
				
				
				break;
			case "QUIT":
				System.out.println("thanks for playing, program ending");
				keepGoing = false;
				break;
			default:
				System.out.println("Invalid response, try again.");
				break;
			}
		}
	}
}
