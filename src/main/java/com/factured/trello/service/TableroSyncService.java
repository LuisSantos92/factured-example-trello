package com.factured.trello.service;

import com.factured.trello.entity.Tablero;
import com.factured.trello.mongo.TableroMongoRepository;
import org.springframework.stereotype.Service;

@Service
public class TableroSyncService {
    private final TableroMongoRepository mongoRepository;

    public TableroSyncService(TableroMongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    public void syncTablero(Tablero tablero) {
        mongoRepository.save(tablero);
    }
}
