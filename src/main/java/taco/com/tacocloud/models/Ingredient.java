package taco.com.tacocloud.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import org.springframework.data.domain.Persistable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table("ingredient")
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class Ingredient implements Persistable<String>{
    
    @Id
    private final String id;

    @Column("name")
    private final String name;
    
    @Column("type")
    private final Type type;

    public enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

    // GETTERS AND SETTERS with Lambo
}
