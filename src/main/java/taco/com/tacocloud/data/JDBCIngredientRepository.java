package taco.com.tacocloud.data;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.List;

import taco.com.tacocloud.tacos.Ingredient;

@Repository
public class JDBCIngredientRepository {
    
    private JdbcTemplate jdbcTemplate;

    public JDBCIngredientRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    // FIND BY ID

    public Optional<Ingredient> findById(String id) {
        List<Ingredient> results = jdbcTemplate.query("select id, name, type from Ingredient where id=?",this::mapRowToIngredient,id);
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
        }
    
        private Ingredient mapRowToIngredient(ResultSet row, int rowNum) throws SQLException {
                return new Ingredient(row.getString("id"),row.getString("name"), Ingredient.Type.valueOf(row.getString("type")));
                }
}
