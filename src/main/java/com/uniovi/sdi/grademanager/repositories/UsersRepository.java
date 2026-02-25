package com.uniovi.sdi.grademanager.repositories;

import com.uniovi.sdi.grademanager.entities.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepository extends CrudRepository<User, Long>{
    User findByDni(String dni);

    @Query("SELECT r FROM User r WHERE (LOWER(r.name) LIKE LOWER(?1) OR LOWER(r.lastName) LIKE LOWER(?1))")
    List<User> searchByNameAndLastName(String searchText);
}
