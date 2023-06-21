package by.ginel.dao;


import by.ginel.dto.PersonView;
import by.ginel.entity.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SDJpaDao extends PagingAndSortingRepository<Person, Long> {

    Person findPersonById(Long id);

    @Query(value = "select p from Person p where p.id = :id")
    Person findPersonByIdWithJPQL(@Param("id") Long id);

    @Query(value = "select * from person p where p.id = :id", nativeQuery = true)
    Person findPersonByIdWithNativeQuery(@Param("id") Long id);

    @EntityGraph(attributePaths = "credentials")
    Person findPersonByMobNum(String mobNum);

    @EntityGraph(value = "person.credentials")
    Person findPersonByEmail(String email);

    @Query(value = "select first_name, email from person p where p.id = :id", nativeQuery = true)
    PersonView findPersonByIdWithView(@Param("id") Long id);

    List<Person> findAllByEnabled(Boolean enabled, Pageable pageable);

    List<Person> findAll(Specification<Person> personSpecification);
}
