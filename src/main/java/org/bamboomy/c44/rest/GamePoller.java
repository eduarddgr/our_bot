package org.bamboomy.c44.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**

Copyright 2026 Sander Theetaert

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.


**/

public class GamePoller implements Runnable {

	private final String identifierHash, serverUrl;

	private ArrayList<String> startedGames = new ArrayList<String>();

	public GamePoller(String serverUrl, String identifierHash) {

		this.identifierHash = identifierHash;
		this.serverUrl = serverUrl;
	}

	@Override
	public void run() {

		while (true) {

			System.out.println("waiting for game...");

			try {
				Thread.sleep(5_000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String game = requestGame();

			if (game != null) {

				if (startedGames.contains(game)) {

					System.out.println("Game: " + game + " still exists on the server...");

				} else {

					System.out.println("commencing game: " + game);

					startedGames.add(game);

					(new Thread(new ColorNegotiator(serverUrl, game, identifierHash))).start();
				}

			} else {

				System.out.println("null");
			}
		}
	}

	private String requestGame() {

		return getPath(serverUrl, "/askGame/" + identifierHash).toString();
	}

	private StringBuffer getPath(String serverUrl, String path) {

		StringBuffer result = null;

		URL url;

		try {

			url = new URL(serverUrl + "/color" + path);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			result = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				result.append(inputLine);
			}
			in.close();

			con.disconnect();

			System.out.print(result.toString());

		} catch (ConnectException e) {

			System.out.println("could not connect :(");

			return null;

		} catch (IOException e) {

			e.printStackTrace();
		}

		return result;
	}
}
