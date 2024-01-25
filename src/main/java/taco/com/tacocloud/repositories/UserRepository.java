package taco.com.tacocloud.repositories;

import org.springframework.data.repository.CrudRepository;

import taco.com.tacocloud.models.User;

public interface UserRepository extends CrudRepository<User,Long>{
    
    User findByUsername(String username);

}

