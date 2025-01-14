package com.factured.trello.mongo;



import com.factured.trello.entity.Tablero;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableroMongoRepository extends MongoRepository<Tablero, String> {
    // Métodos personalizados para consultas específicas en MongoDB.
}

