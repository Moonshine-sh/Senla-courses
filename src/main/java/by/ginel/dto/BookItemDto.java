package by.ginel.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class BookItemDto extends AbstractDto{
//    private Long bookId;
//    private Long orderId;
    private BigDecimal price;
}
