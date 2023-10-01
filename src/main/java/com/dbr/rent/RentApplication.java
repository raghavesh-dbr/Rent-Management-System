package com.dbr.rent;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.Objects;

@SpringBootApplication
public class RentApplication {

	public static void main(String[] args) throws Exception{

		File file =new File("C:\\Raghavesh Personal\\Project Own\\rent\\src\\main\\resources\\serviceAccountKey.json");
		FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://rent-managemet-default-rtdb.firebaseio.com")
				.build();

		FirebaseApp.initializeApp(options);


		SpringApplication.run(RentApplication.class, args);
	}

}
