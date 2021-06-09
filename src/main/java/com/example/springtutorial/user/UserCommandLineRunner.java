package com.example.springtutorial.user;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserCommandLineRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    private static final Logger log = LoggerFactory.logger(UserCommandLineRunner.class);

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User("Employee1", "Application Developer"));
        userRepository.save(new User("Employee2", "Application Developer"));
        userRepository.save(new User("Employee3", "Business Analyst"));
        userRepository.save(new User("Employee4", "Project Manager"));

        log.info("--------------------------------");
        for (User user : userRepository.findAll()) {
            log.info(user);
        }
        log.info("--------------------------------");

        log.info("--------------------------------");
        log.info("Application Developers are\n");
        for (User user : userRepository.findByRole("Application Developer")) {
            log.info(user);
        }
        log.info("--------------------------------");
    }
}
