package com.factured.trello.controller;

import com.factured.trello.entity.Tarea;
import com.factured.trello.service.TareaQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/query/tareas")
public class TareaQueryController {

    private final TareaQueryService tareaQueryService;

    public TareaQueryController(TareaQueryService tareaQueryService) {
        this.tareaQueryService = tareaQueryService;
    }

    @GetMapping("/report")
    public ResponseEntity<Map<String, Object>> getReport() {
        return ResponseEntity.ok(tareaQueryService.generateReport());
    }

    // Listar todas las tareas desde MongoDB
    @GetMapping
    public ResponseEntity<List<Tarea>> getAllTareasFromMongo() {
        return ResponseEntity.ok(tareaQueryService.getAllTareas());
    }

    // Obtener una tarea específica desde MongoDB por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> getTareaFromMongoById(@PathVariable String id) {
        return ResponseEntity.ok(tareaQueryService.getTareaById(id));
    }

}
