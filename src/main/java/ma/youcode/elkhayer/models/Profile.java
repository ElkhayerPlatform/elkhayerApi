package ma.youcode.elkhayer.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String city;
    private String country;
    private String phone;
    private String Address;
    private String website;
    private String facebook;
    private String twitter;
    private String linkedin;
    private String picture;
    private Double capital;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
