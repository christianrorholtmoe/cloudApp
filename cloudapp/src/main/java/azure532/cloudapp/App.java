package azure532.cloudapp;

import java.util.Random;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class App {
	
	private CloudQueueController qController = new CloudQueueController("nyko"); ;
	
	@RequestMapping("/")
	String home() {
		return "Veldig enkel Springboot App, kjører på Azure og repo på Githøbb";
	}
	
	@RequestMapping("/add")
	String add() {
		qController.addMessageToCloudQueue("Hei! " + Math.round(Math.random()*100));
		return "La til element ";
	}
	
	@RequestMapping("/get")
	String get() {
		return qController.getMessageFromCloudQueue();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
		
	}

}