package taco.com.tacocloud.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import taco.com.tacocloud.models.TacoOrder;
import taco.com.tacocloud.models.User;
import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder,Long> {

    List<TacoOrder> findByDeliveryZIP(String deliveryZip);

    @Query("Order o where o.deliveryCity='Seattle'")
    List<TacoOrder> readOrdersDeliveredInSeattle();

    List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
