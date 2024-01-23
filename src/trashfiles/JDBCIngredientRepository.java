package taco.com.tacocloud.trashfiles;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.List;

import taco.com.tacocloud.data.IngredientRepository;
import taco.com.tacocloud.tacos.Ingredient;

@Repository
public class JDBCIngredientRepository implements IngredientRepository {
    
    private JdbcTemplate jdbcTemplate;

    public JDBCIngredientRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    
    @Override
    public Optional<Ingredient> findById(String id) {
        List<Ingredient> results = jdbcTemplate.query("select id, name, type from Ingredient where id=?",this::mapRowToIngredient,id);
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
        }
    
    @Override
    public Iterable<Ingredient> findAll(){
        return jdbcTemplate.query("select id, name, type from Ingredient",this::mapRowToIngredient);
    }

    
    public Ingredient mapRowToIngredient(ResultSet row, int rowNum)throws SQLException{
        return new Ingredient(
            row.getString("id"),
            row.getString("name"),
            Ingredient.Type.valueOf(row.getString("type"))
        );
    }

    @Override
    public Ingredient save(Ingredient ingredient){
        jdbcTemplate.update("insert into Ingredient(id,name,value) values(?,?;?)", ingredient.getId(),ingredient.getName(),ingredient.getType());
        return ingredient;
    }
}
