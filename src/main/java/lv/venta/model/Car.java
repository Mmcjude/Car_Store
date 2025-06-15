package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lv.venta.model.enums.BodyType;

@Entity
@Table(name = "cars")
@Getter @Setter @NoArgsConstructor @ToString
public class Car extends BaseEntity {
    
    @Column(nullable = false)
    @NotBlank
    @Size(min = 2, max = 50)
    private String model;
    
    @Column(unique = true, nullable = false)
    @NotBlank
    @Size(min = 17, max = 17, message = "VIN must be exactly 17 characters")
    private String vin;
    
    @Column(nullable = false)
    @Min(1900)
    private int year;
    
    @Column(nullable = false)
    @NotBlank
    @Size(min = 3, max = 20)
    private String color;
    
    @Column(nullable = false)
    @DecimalMin("0.0")
    private float price;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BodyType bodyType;
    
    @ManyToOne
    @JoinColumn(name = "manufacturer_id", nullable = false)
    @ToString.Exclude
    private Manufacturer manufacturer;
    
    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Engine engine;
    
    public Car(String model, int year, String color, float price, String vin, 
               BodyType bodyType, Manufacturer manufacturer) {
        setModel(model);
        setYear(year);
        setColor(color);
        setPrice(price);
        setVin(vin);
        setBodyType(bodyType);
        setManufacturer(manufacturer);
    }

    // Added this getter to expose brand to Thymeleaf
    public String getBrand() {
        if (manufacturer != null) {
            return manufacturer.getBrand();
        }
        return null;
    }
}
