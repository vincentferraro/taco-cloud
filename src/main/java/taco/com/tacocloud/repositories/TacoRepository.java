package taco.com.tacocloud.repositories;

import org.springframework.data.repository.CrudRepository;
import taco.com.tacocloud.models.Taco;
public interface TacoRepository extends CrudRepository<Taco, Long> {
    
}
