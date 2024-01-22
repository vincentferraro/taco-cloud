package taco.com.tacocloud.data;

import java.sql.Types;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import taco.com.tacocloud.tacos.Taco;
import java.util.Date;

import taco.com.tacocloud.tacos.TacoOrder;

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
}
