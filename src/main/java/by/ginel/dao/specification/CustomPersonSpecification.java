package by.ginel.dao.specification;

import by.ginel.entity.Person;
import by.ginel.entity.Person_;
import org.springframework.data.jpa.domain.Specification;

public class CustomPersonSpecification {
    public static Specification<Person> nameLike(String name) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(Person_.FIRST_NAME), "%" + name + "%")
        );
    }
}
