package ShaqAndSoldier.Copyfy;

import ShaqAndSoldier.Copyfy.repository.SongRepository;
import ShaqAndSoldier.Copyfy.service.StorageProperties;
import ShaqAndSoldier.Copyfy.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class CopyfyApplication {

	public static void main(String[] args) {
            System.out.println("gecc"); //But why?
		SpringApplication.run(CopyfyApplication.class, args);
	}
        
        @Bean
        CommandLineRunner init(StorageService storageService) {
            return (args) -> {
                storageService.deleteAll();
                storageService.init();
            };
        }
        
}
