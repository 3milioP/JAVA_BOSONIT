package com.example.ejercicio.block7crud.controller;

import com.example.ejercicio.block7crud.domain.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class CustomQueryController {
    @PersistenceContext
    private EntityManager entityManager;
    @GetMapping("/customquery")
    public List<Persona> customQuery(
            @RequestParam(required = true) int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false) String user,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) LocalDate minCreationDate,
            @RequestParam(required = false) LocalDate maxCreationDate,
            @RequestParam(required = false) String orderBy
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> criteriaQuery = cb.createQuery(Persona.class);
        Root<Persona> root = criteriaQuery.from(Persona.class);

        List<Predicate> predicates = new ArrayList<>();

        if (user!=null) {
            predicates.add(cb.equal(root.get("user"), user));
        }
        if (name!=null) {
            predicates.add(cb.equal(root.get("name"), name));
        }
        if (surname!=null) {
            predicates.add(cb.equal(root.get("surname"), surname));
        }
        if (minCreationDate!=null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("creationDate"), minCreationDate));
        }

        if (maxCreationDate!=null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("creationDate"), maxCreationDate));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        if (orderBy!=null && orderBy.equals("user") || Objects.equals(orderBy, "name")) {
            criteriaQuery.orderBy(cb.asc(root.get(orderBy)));
        }

        TypedQuery<Persona> query = entityManager.createQuery(criteriaQuery);
        int firstResult = (pageNumber - 1) * pageSize;
        query.setFirstResult(firstResult);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }
}
