package com.fot.canteenapp.Repository;

import com.fot.canteenapp.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
}
