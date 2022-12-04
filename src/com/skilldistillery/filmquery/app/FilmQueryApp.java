package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
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
		app.launch();
	}

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) throws SQLException {
		int answer = 0;
		boolean keepGoing = true;
		System.out.println("\n"
				+ "                                                                                                                                                                                                                                          \n"
				+ "      o            o            o            o            o            o            o            o            o            o            o            o            o            o            o            o            o            o      \n"
				+ "     d8b          d8b          d8b          d8b          d8b          d8b          d8b          d8b          d8b          d8b          d8b          d8b          d8b          d8b          d8b          d8b          d8b          d8b     \n"
				+ "    d888b        d888b        d888b        d888b        d888b        d888b        d888b        d888b        d888b        d888b        d888b        d888b        d888b        d888b        d888b        d888b        d888b        d888b    \n"
				+ "\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\n"
				+ "  \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"  \n"
				+ "  d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b  \n"
				+ " dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb \n"
				+ "                                                                                                                                                                                                                                          \n"
				+ "                                                                                                                                                                                                                                          \n"
				+ "                                                                                                                                                                                                                                          \n"
				+ "888       888          888                                                888                   888888b.   888       .d88888b.   .d8888b.  888    d8P  888888b.   888     888  .d8888b.  88888888888 8888888888 8888888b.  888            \n"
				+ "888   o   888          888                                                888                   888  \"88b  888      d88P\" \"Y88b d88P  Y88b 888   d8P   888  \"88b  888     888 d88P  Y88b     888     888        888   Y88b 888            \n"
				+ "888  d8b  888          888                                                888                   888  .88P  888      888     888 888    888 888  d8P    888  .88P  888     888 Y88b.          888     888        888    888 888            \n"
				+ "888 d888b 888  .d88b.  888  .d8888b  .d88b.  88888b.d88b.   .d88b.        888888  .d88b.        8888888K.  888      888     888 888        888d88K     8888888K.  888     888  \"Y888b.       888     8888888    888   d88P 888            \n"
				+ "888d88888b888 d8P  Y8b 888 d88P\"    d88\"\"88b 888 \"888 \"88b d8P  Y8b       888    d88\"\"88b       888  \"Y88b 888      888     888 888        8888888b    888  \"Y88b 888     888     \"Y88b.     888     888        8888888P\"  888            \n"
				+ "88888P Y88888 88888888 888 888      888  888 888  888  888 88888888       888    888  888       888    888 888      888     888 888    888 888  Y88b   888    888 888     888       \"888     888     888        888 T88b   Y8P            \n"
				+ "8888P   Y8888 Y8b.     888 Y88b.    Y88..88P 888  888  888 Y8b.           Y88b.  Y88..88P       888   d88P 888      Y88b. .d88P Y88b  d88P 888   Y88b  888   d88P Y88b. .d88P Y88b  d88P     888     888        888  T88b   \"             \n"
				+ "888P     Y888  \"Y8888  888  \"Y8888P  \"Y88P\"  888  888  888  \"Y8888         \"Y888  \"Y88P\"        8888888P\"  88888888  \"Y88888P\"   \"Y8888P\"  888    Y88b 8888888P\"   \"Y88888P\"   \"Y8888P\"      888     8888888888 888   T88b 888            \n"
				+ "                                                                                                                                                                                                                                          \n"
				+ "                                                                                                                                                                                                                                          \n"
				+ "                                                                                                                                                                                                                                          \n"
				+ "                                                                                                                                                                                                                                          \n"
				+ "      o            o            o            o            o            o            o            o            o            o            o            o            o            o            o            o            o            o      \n"
				+ "     d8b          d8b          d8b          d8b          d8b          d8b          d8b          d8b          d8b          d8b          d8b          d8b          d8b          d8b          d8b          d8b          d8b          d8b     \n"
				+ "    d888b        d888b        d888b        d888b        d888b        d888b        d888b        d888b        d888b        d888b        d888b        d888b        d888b        d888b        d888b        d888b        d888b        d888b    \n"
				+ "\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\"Y888888888P\"\n"
				+ "  \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"    \"Y88888P\"  \n"
				+ "  d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b    d88P\"Y88b  \n"
				+ " dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb  dP\"     \"Yb \n"
				+ "                                                                                                                                                                                                                                          \n"
				+ "                                                                                                                                                                                                                                          \n"
				+ "                                                                                                                                                                                                                                          \n"
				+ "");
		while (keepGoing == true) {
			System.out.println(
					  "******************************************************************\n"
					+ " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~Menu:~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
					+ " Type \"NUMBER\" to search for a film by number between 1-1000.\n"
					+ " Type \"KEYWORD\" to search for a title or description by keyword. \n"
					+ " Type \"QUIT\" to exit.\n" 
					+ " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
					+ "******************************************************************");
			String response = input.next();

			switch (response.toUpperCase()) {
			case "NUMBER":
				System.out.println("Enter a movie id:");
				answer = input.nextInt();

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
			case "KEYWORD":
				System.out.println("What phrase would you like to see if it is in a movie?");
				String phrase = input.next();
				List<Film> filmS = db.findFilmsBySearchWord(phrase);

				int filmId = 0;
				if (filmS.isEmpty()) {
					System.out.println("Sorry, there are no films with that keyword.");
				} else {
					for (Film f : filmS) {
						System.out.println("Films matching the phrase \" " + phrase + " \" are:");
						System.out.println("Title: " + f.getTitle() + ", Release Year: " + f.getReleaseYear()
								+ ", Rated: " + f.getRating() + ", Description: " + f.getDescription() + ", Language: "
								+ f.getLanguage());
						filmId = f.getId();
						List<Actor> actorSearch = db.findActorsByFilmId(filmId);
						System.out.println("Actors and Actresses include:");
						System.out.println(actorSearch);
					}
				}

				break;
			case "QUIT":
				System.out.println("Thanks for stopping by!");
				System.out.println("\n"
						+ "  _____   ______  _____   ______  ______ _______ _______      _______ __   _ ______  _____ __   _  ______      \n"
						+ " |_____] |_____/ |     | |  ____ |_____/ |_____| |  |  |      |______ | \\  | |     \\   |   | \\  | |  ____      \n"
						+ " |       |    \\_ |_____| |_____| |    \\_ |     | |  |  |      |______ |  \\_| |_____/ __|__ |  \\_| |_____| . . .\n"
						+ "                                                                                                               \n"
						+ "");
				keepGoing = false;
				break;
			default:
				System.out.println("Invalid response, try again.");
				break;
			}
		}
	}
}
