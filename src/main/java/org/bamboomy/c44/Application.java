
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

/**
 * This bot is incomplete:
 * 	- En passant is not covered
 * 	- Neither is Rocade
 * 
 * -> The bot does get the Rocade move from the server
 * but does not calculate it down the tree...
 * -> En passant (both double as single) are not passed to the bot.
 **/

package org.bamboomy.c44;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import org.bamboomy.c44.rest.Poller;
import org.json.JSONArray;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = { "org.bamboomy.c44" })
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

		return application.sources(Application.class);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/index.html").allowedOrigins("http://localhost:8080");
			}
		};
	}

	public static void main(String[] args) throws Exception {

		Properties prop = new Properties();

		String serverUrl = null;

		String colors = null;

		try {

			prop.load(Poller.class.getClassLoader().getResourceAsStream("application.properties"));

			serverUrl = prop.getProperty("server.url");

			System.out.println(prop.getProperty("colors"));

			colors = prop.getProperty("colors");

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		SpringApplication.run(Application.class, args);

		System.out.println("waiting...");

		try {

			Thread.sleep(3_000);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		JSONArray initingRobotHashes = getInitingRobotHashes(serverUrl);

		boolean pollReady = !initingRobotHashes.isEmpty();

		while (!pollReady) {

			System.out.println("Couldn't find a game (yet...)...");

			try {

				Thread.sleep(3_000);

			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			initingRobotHashes = getInitingRobotHashes(serverUrl);

			pollReady = !initingRobotHashes.isEmpty();
		}

		Poller poller = new Poller(serverUrl, initingRobotHashes.getString(0), colors);

		(new Thread(poller)).start();
	}

	private static JSONArray getInitingRobotHashes(String serverUrl) {

		URL url;

		boolean connected = false;

		while (!connected) {

			try {

				url = new URL(serverUrl + "/react/getInitingRobotHashes");
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");

				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				in.close();

				con.disconnect();

				System.out.println(content.toString());

				return new JSONArray(content.toString());

			} catch (IOException e) {

				System.out.println("ignoring exception...");

				try {
					Thread.sleep(1_000);
				} catch (InterruptedException e1) {

					e1.printStackTrace();
				}
			}
		}

		return null;
	}

}
