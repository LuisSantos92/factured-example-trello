package com.factured.trello.service;

import com.factured.trello.entity.Tarea;
import com.factured.trello.mongo.TareaMongoRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TareaQueryService {

    private final TareaMongoRepository tareaMongoRepository;

    public TareaQueryService(TareaMongoRepository tareaMongoRepository) {
        this.tareaMongoRepository = tareaMongoRepository;
    }

    public List<Tarea> getAllTareas() {
        return tareaMongoRepository.findAll();
    }

    public Tarea getTareaById(String id) {
        return tareaMongoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con ID: " + id));
    }

    public Map<String, Object> generateReport() {
        List<Tarea> tareas = tareaMongoRepository.findAll();

        Map<Long, Map<String, Long>> conteoPorTableroYEstado = new HashMap<>();
        Map<String, Long> conteoGlobalPorEstado = new HashMap<>();

        tareas.forEach(tarea -> {
            String estado = tarea.getEstado().name();
            conteoGlobalPorEstado.put(estado, conteoGlobalPorEstado.getOrDefault(estado, 0L) + 1);

            Long tableroId = tarea.getTablero().getId();
            conteoPorTableroYEstado.putIfAbsent(tableroId, new HashMap<>());
            Map<String, Long> estadosPorTablero = conteoPorTableroYEstado.get(tableroId);
            estadosPorTablero.put(estado, estadosPorTablero.getOrDefault(estado, 0L) + 1);
        });

        Map<String, Object> report = new HashMap<>();
        report.put("conteoPorEstado", conteoGlobalPorEstado);
        report.put("conteoPorTablero", conteoPorTableroYEstado);
        return report;
    }


}