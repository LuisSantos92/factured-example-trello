package com.factured.trello.controller;


import com.factured.trello.entity.Tarea;
import com.factured.trello.service.TareaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping
    public ResponseEntity<List<Tarea>> getAllTareas() {
        return ResponseEntity.ok(tareaService.getAllTareas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> getTareaById(@PathVariable Long id) {
        return ResponseEntity.ok(tareaService.getTareaById(id));
    }

    @PostMapping
    public ResponseEntity<Tarea> createTarea(@RequestBody Tarea tarea) {
        return ResponseEntity.ok(tareaService.createTarea(tarea));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> updateTarea(@PathVariable Long id, @RequestBody Tarea tarea) {
        return ResponseEntity.ok(tareaService.updateTarea(id, tarea));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarea(@PathVariable Long id) {
        tareaService.deleteTarea(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Tarea> updateEstado(@PathVariable Long id, @RequestParam String estado) {
        return ResponseEntity.ok(tareaService.updateEstado(id, estado));
    }

    @GetMapping("/report")
    public ResponseEntity<?> getReport() {
        return ResponseEntity.ok(tareaService.generateReport());
    }
}
