package sb.tr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TrApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrApplication.class, args);
	}

}
