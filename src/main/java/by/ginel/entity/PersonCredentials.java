package by.ginel.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name = "person_cred")
@NamedEntityGraph(name = "graph.PersonCredentials.person",
        attributeNodes = @NamedAttributeNode("person"))
public class PersonCredentials extends AbstractEntity{
    private String login;
    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;
}
