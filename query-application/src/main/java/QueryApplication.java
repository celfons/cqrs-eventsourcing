import br.com.example.cqrseventsourcing.CustomerEventSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaRepositories("br.com.example.cqrseventsourcing.query.repository")
@EntityScan(basePackageClasses = {QueryApplication.class, Jsr310JpaConverters.class}, basePackages = "br.com.example.cqrseventsourcing.*")
@ComponentScan("br.com.example.cqrseventsourcing")
public class QueryApplication {

    @Autowired
    private CustomerEventSubscriber customerEventSubscriber;

	@PostConstruct
	public void init() {
		customerEventSubscriber.start();
	}

	public static void main(String[] args) {
		SpringApplication.run(QueryApplication.class, args);
	}
}
