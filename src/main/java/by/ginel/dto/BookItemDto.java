package by.ginel.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Setter
@Getter
@SuperBuilder
public class BookItemDto extends AbstractDto{
//    private Long bookId;
//    private Long orderId;
    private BigDecimal price;
}
