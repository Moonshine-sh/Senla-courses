package by.ginel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "verification_token")
public class VerificationToken extends AbstractEntity {
    private String token;
    private Timestamp expiryDate;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
