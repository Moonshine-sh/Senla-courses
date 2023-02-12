package by.ginel.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Order extends AbstractEntity{
    private Timestamp date;
    private Person person;
    private BigDecimal price;
    private Status status;
}
