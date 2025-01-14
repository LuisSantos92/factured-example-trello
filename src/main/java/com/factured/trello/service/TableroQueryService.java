package com.factured.trello.service;

import com.factured.trello.entity.Tablero;
import com.factured.trello.mongo.TableroMongoRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TableroQueryService {

    private final TableroMongoRepository tableroMongoRepository;

    public TableroQueryService(TableroMongoRepository tableroMongoRepository) {
        this.tableroMongoRepository = tableroMongoRepository;
    }

    public List<Tablero> getAllTableros() {
        return tableroMongoRepository.findAll();
    }

    public Tablero getTableroById(String id) {
        return tableroMongoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tablero no encontrado con ID: " + id));
    }

    public Map<String, Object> generateReport() {
        List<Tablero> tableros = tableroMongoRepository.findAll();
        Map<String, Long> conteoPorEstado = new HashMap<>();

        tableros.forEach(tablero -> {
            if (tablero.getTareas() != null) {
                tablero.getTareas().forEach(tarea -> {
                    String estado = tarea.getEstado().name();
                    conteoPorEstado.put(estado, conteoPorEstado.getOrDefault(estado, 0L) + 1);
                });
            }
        });

        return Map.of("conteoPorEstado", conteoPorEstado);
    }
}