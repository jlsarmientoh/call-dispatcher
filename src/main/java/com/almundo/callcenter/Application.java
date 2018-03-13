package com.almundo.callcenter;

import com.almundo.callcenter.core.PhoneCall;
import com.almundo.callcenter.dispatcher.Dispatcher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            Dispatcher dispatcher = ctx.getBean(Dispatcher.class);

            for(int i = 1; i <= 50; i++){
                dispatcher.dispatchCall(new PhoneCall(i));
            }
        };
    }
}
