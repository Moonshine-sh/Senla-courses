package by.ginel.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class OrderDto extends AbstractDto{
    private Timestamp date;
//    private Long personId;
    private BigDecimal price;
//    private Long statusId;
}
