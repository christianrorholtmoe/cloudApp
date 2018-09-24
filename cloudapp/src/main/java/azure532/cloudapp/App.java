package azure532.cloudapp;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class App {

	@RequestMapping("/")
	String home() {
		return "Veldig enkel Springboot App, kjører på Azure og repo på Githøbb";
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}

}