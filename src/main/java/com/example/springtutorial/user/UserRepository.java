package com.example.springtutorial.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByRole(String role); // Spring data JPA automatically creates this method findBy{nameOfTheParamToSearch}
}
