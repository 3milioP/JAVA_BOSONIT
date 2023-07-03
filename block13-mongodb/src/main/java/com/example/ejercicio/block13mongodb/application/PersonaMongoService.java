package com.example.ejercicio.block13mongodb.application;

import com.example.ejercicio.block13mongodb.domain.PersonaMongo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonaMongoService {
    private final MongoTemplate mongoTemplate;

    public PersonaMongo guardar(PersonaMongo persona) {
        return mongoTemplate.save(persona);
    }

    public PersonaMongo obtenerPorId(String id) {
        return mongoTemplate.findById(id, PersonaMongo.class);
    }

    public List<PersonaMongo> obtenerTodos(Pageable pageable) {
        return mongoTemplate.findAll(PersonaMongo.class);
    }

    public void actualizar(PersonaMongo persona) {
        mongoTemplate.save(persona);
    }

    public void eliminar(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, PersonaMongo.class);
    }
}
