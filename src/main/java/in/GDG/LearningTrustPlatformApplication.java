package in.GDG;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "in.GDG")  // 👈 Forces scanning
public class LearningTrustPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearningTrustPlatformApplication.class, args);
    }
}
