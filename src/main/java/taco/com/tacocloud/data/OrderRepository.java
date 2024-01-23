package taco.com.tacocloud.data;

import org.springframework.data.repository.CrudRepository;

import taco.com.tacocloud.tacos.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder,Long> {

}
