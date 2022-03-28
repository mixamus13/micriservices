package com.amigoscode.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
@SpringBootApplication(
        scanBasePackages = {
                "com.amigoscode.notification",
                "com.amigoscode.amqp"
        }
)
public class NotificationApplication {

    public static void main(String... args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer,
//                                        NotificationConfig notificationConfig) {
//        return args -> {
//            producer.publish(
//                    new Person("Max", 43),
//                    notificationConfig.getInternalExchange(),
//                    notificationConfig.getInternalNotificationRoutingKey());
//        };
//    }
//
//    record Person(String name, int age) {}
}
