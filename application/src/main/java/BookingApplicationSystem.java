import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication(scanBasePackages = "com.booking.**")
@EntityScan(basePackages = "com.booking.**")
//@EnableJpaRepositories(basePackages = "com.booking.**")
@EnableAsync
@EnableScheduling
public class BookingApplicationSystem {
    public static void main(String[] args) {
        SpringApplication.run(BookingApplicationSystem.class,args);
    }
}
