package by.ginel.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person")
public class Person extends AbstractEntity{
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    @Column(name = "mob_num")
    private String mobNum;
    private Boolean locked;
    private Boolean enabled;

    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY)
    private PersonCredentials credentials;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "person_role", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private Set<Order> orders;
}
