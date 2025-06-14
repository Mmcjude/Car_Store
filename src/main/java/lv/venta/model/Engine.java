package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lv.venta.model.enums.FuelType;

@Entity
@Table(name = "engines")
@Getter @Setter @NoArgsConstructor @ToString
public class Engine extends BaseEntity {
    
    @Column(nullable = false, unique = true)
    @NotNull
    private String engineCode;
    
    @Column(nullable = false)
    @Min(500)
    private int displacement; // in cc
    
    @Column(nullable = false)
    @Min(50)
    private int horsepower;
    
    @Column(nullable = false)
    @Min(50)
    private int torque; // in Nm
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FuelType fuelType;
    
    @OneToOne
    @JoinColumn(name = "car_id", nullable = false)
    @ToString.Exclude
    private Car car;
    
    public Engine(String engineCode, int displacement, int horsepower, 
                 int torque, FuelType fuelType, Car car) {
        setEngineCode(engineCode);
        setDisplacement(displacement);
        setHorsepower(horsepower);
        setTorque(torque);
        setFuelType(fuelType);
        setCar(car);
    }
}