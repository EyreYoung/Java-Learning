package customRPC;

import org.springframework.context.annotation.Bean;

public interface CustomService {

    @Bean
    String sayHello(String param);

}
