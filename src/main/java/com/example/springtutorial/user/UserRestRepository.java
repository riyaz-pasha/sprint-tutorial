package com.example.springtutorial.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRestRepository extends PagingAndSortingRepository<User, Long> {
    List<User> findByRole(@Param("role") String role); // Spring data JPA automatically creates this method findBy{nameOfTheParamToSearch}
}
