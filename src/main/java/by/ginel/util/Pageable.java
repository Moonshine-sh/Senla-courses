package by.ginel.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pageable {
    private int pageNumber;
    private int pageSize = 20;

    public int getOffset() {
        return pageNumber * pageSize;
    }

    public static Pageable maxPage() {
        return new Pageable(0, Integer.MAX_VALUE);
    }
}
