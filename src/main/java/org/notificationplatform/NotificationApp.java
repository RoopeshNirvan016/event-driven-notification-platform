package org.notificationplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationApp {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApp.class,args);
        System.out.printf("Notification application is started !");
    }
}