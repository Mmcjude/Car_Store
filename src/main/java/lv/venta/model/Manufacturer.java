package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "manufacturers")
@Getter @Setter @NoArgsConstructor @ToString
public class Manufacturer extends BaseEntity {

    @Column(nullable = false, unique = true)
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 2, max = 50)
    private String country;

    @Column(nullable = false)
    @Min(1800)
    private int foundedYear;

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Car> cars = new ArrayList<>();


    public Manufacturer(String name, String country, int foundedYear) {
        setName(name);
        setCountry(country);
        setFoundedYear(foundedYear);
    }


	public String getBrand() {
		// TODO Auto-generated method stub
		return null;
	}
}
