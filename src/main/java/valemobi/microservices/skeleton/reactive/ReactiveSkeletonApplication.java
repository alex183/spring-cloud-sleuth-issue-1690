package valemobi.microservices.skeleton.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "valemobi")
public class ReactiveSkeletonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveSkeletonApplication.class, args);
	}

}
