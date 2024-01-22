package taco.com.tacocloud.data;

import taco.com.tacocloud.tacos.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder tacoOrder);
}
