package by.ginel.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class BookItem extends AbstractEntity{
//    private Book book;
//    private Order order;
    private BigDecimal price;
}
