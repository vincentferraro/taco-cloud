package taco.com.tacocloud.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.io.Serializable;
import org.hibernate.validator.constraints.CreditCardNumber;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.security.access.prepost.PostAuthorize;

import jakarta.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@Data
// @Entity
public class TacoOrder implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private Date placedAt;

    @NotBlank(message="Delivery name is required")
    private String deliveryName;
    @NotBlank(message="Street is required")
    private String deliveryStreet;
    @NotBlank(message="City is required")
    private String deliveryCity;
    @NotBlank(message="State is required")
    private String deliveryState;
    @NotBlank(message="Zip is required")
    private String deliveryZip;
    @CreditCardNumber(message="CCNumber is required")
    private String ccNumber;
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message="Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Taco> tacos = new ArrayList<>();

    @ManyToOne
    private User user;

    public void addTaco(Taco taco){
        this.tacos.add(taco);
    }

    
    // @PostAuthorize("hasRole('ADMIN') || " + "returnObject.user.username == authentication.name")
    // public TacoOrder getOrder(long id) {
    //   //...
    // }
    // GETTERS AND SETTERS with Lambo
}
