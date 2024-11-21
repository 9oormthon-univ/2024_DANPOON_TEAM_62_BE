package region;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"region"})
public class regionApplication {

	public static void main(String[] args) {
		SpringApplication.run(regionApplication.class, args);
	}

}
