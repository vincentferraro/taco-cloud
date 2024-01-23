package taco.com.tacocloud.trashfiles;

import java.sql.Types;

import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import taco.com.tacocloud.tacos.IngredientRef;
import taco.com.tacocloud.tacos.Taco;
import java.util.Date;
import java.util.Arrays;
import java.util.List;
import taco.com.tacocloud.tacos.TacoOrder;
import taco.com.tacocloud.data.OrderRepository;
import taco.com.tacocloud.tacos.Ingredient;
@Repository
public class JDBCOrderRepository implements OrderRepository {
    
    private JdbcOperations jdbcoperations;

    JDBCOrderRepository(JdbcOperations jdbcOperations){
        this.jdbcoperations = jdbcOperations;
    }


    @Override
    @Transactional
    public TacoOrder save(TacoOrder order){
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory("insert into Taco_Order "
        + "(delivery_name, delivery_street, delivery_city, "
        + "delivery_state, delivery_zip, cc_number, "
        + "cc_expiration, cc_cvv, placed_at) "
        + "values (?,?,?,?,?,?,?,?,?)",
        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
        Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP);
        pscf.setReturnGeneratedKeys(true);

        order.setPlacedAt(new Date());

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(
            order.getDeliveryName(),
            order.getDeliveryStreet(),
            order.getDeliveryCity(),
            order.getDeliveryState(),
            order.getDeliveryZip(),
            order.getCcNumber(),
            order.getCcExpiration(),
            order.getCcCVV(),
            order.getPlacedAt())) ;

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcoperations.update(psc, keyHolder);
        long orderId = keyHolder.getKey().longValue();
        order.setId(orderId);

        List<Taco> tacos = order.getTacos();
        int i = 0;
        for(Taco taco : tacos){
            saveTaco(orderId,i++,taco);
        }
        return order;
    }

    private long saveTaco(Long orderId, int orderKey, Taco taco){
        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory("insert into Taco"
                            +"(name, created_dat, taco_order, taco_order_key)"
                            +"values(?,?,?,?)",
                            Types.VARCHAR,Types.TIMESTAMP,Type.LONG,Type.LONG);
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(
            taco.getName(),
            taco.getCreatedAt(),
            orderId,
            orderKey
        ));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcoperations.update(psc, keyHolder);
        long tacoId = keyHolder.getKey().longValue();
        taco.setId(tacoId);
        saveIngredientsRefs(tacoId, taco.getIngredients());

        return tacoId;
    }

    private void saveIngredientsRefs(long tacoId, List<Ingredient> ingredientsRefs){
        int key = 0;
        for(Ingredient ingredientRef : ingredientsRefs){
            jdbcoperations.update("insert into Ingredient_Ref (ingredient, taco, taco_key)"+
            "valeues(?,?,?)",ingredientRef.getName(),tacoId, key ++);
        }

    }
}
