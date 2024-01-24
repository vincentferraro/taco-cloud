package taco.com.tacocloud.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import taco.com.tacocloud.models.TacoOrder;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder,Long> {

    List<TacoOrder> findByDeliveryZIP(String deliveryZip);

    @Query("Order o where o.deliveryCity='Seattle'")
    List<TacoOrder> readOrdersDeliveredInSeattle();
}
