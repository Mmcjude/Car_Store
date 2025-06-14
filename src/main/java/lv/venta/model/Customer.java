package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter @Setter @NoArgsConstructor @ToString
public class Customer extends BaseEntity {
    
    @Column(nullable = false)
    @NotBlank
    @Size(min = 2, max = 50)
    private String firstName;
    
    @Column(nullable = false)
    @NotBlank
    @Size(min = 2, max = 50)
    private String lastName;
    
    @Column(nullable = false, unique = true)
    @NotBlank
    @Pattern(regexp = "[0-9]{6}-[0-9]{5}")
    private String personalCode;
    
    @Column(nullable = false)
    @NotBlank
    private String city;
    
    @Column(nullable = false, unique = true)
    @Email
    private String email;
    
    @Column(nullable = false)
    @NotBlank
    @Pattern(regexp = "[0-9]{8,15}")
    private String phone;
    
    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private List<Order> orders = new ArrayList<>();
    
    public Customer(String firstName, String lastName, String personalCode, 
                   String city, String email, String phone) {
        setFirstName(firstName);
        setLastName(lastName);
        setPersonalCode(personalCode);
        setCity(city);
        setEmail(email);
        setPhone(phone);
    }
}