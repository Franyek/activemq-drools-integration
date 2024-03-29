package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Application {

    private static Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        // Launch the application
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    Environment environment;

    @EventListener(ApplicationReadyEvent.class)
    public void welcome() {
        int port = environment.getProperty("server.port", Integer.class, 8080);
        log.info("API available at http://localhost:{}", port);
        log.info("Swagger UI available at http://localhost:{}/swagger-ui.html", port);
    }
}
