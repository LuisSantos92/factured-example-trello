package com.factured.trello.mongo;

import com.factured.trello.entity.Tarea;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TareaMongoRepository extends MongoRepository<Tarea, String> {
}
