package by.ginel.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person")
public class Person extends AbstractEntity{
    private String firstName;
    private String lastName;
    private String email;
    private String mobNum;
    private Boolean locked;
    private Boolean enabled;

    @OneToOne(mappedBy = "person")
    private PersonCredentials credentials;

    @ManyToMany
    @JoinTable(name = "person_role", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @OneToMany(mappedBy = "person")
    private List<Order> orders;
}
