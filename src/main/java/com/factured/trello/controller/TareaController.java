package com.factured.trello.controller;

import com.factured.trello.dto.TareaResponseDTO;
import com.factured.trello.entity.Tarea;
import com.factured.trello.service.TareaService;
import com.factured.trello.service.TareaQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaService tareaService;
    private final TareaQueryService tareaQueryService;

    public TareaController(TareaService tareaService, TareaQueryService tareaQueryService) {
        this.tareaService = tareaService;
        this.tareaQueryService = tareaQueryService;
    }

    @GetMapping
    public ResponseEntity<List<TareaResponseDTO>> getAllTareas() {
        return ResponseEntity.ok(tareaService.getAllTareas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaResponseDTO> getTareaById(@PathVariable Long id) {
        return ResponseEntity.ok(tareaService.getTareaById(id));
    }

    @PostMapping
    public ResponseEntity<TareaResponseDTO> createTarea(@RequestBody Tarea tarea) {
        return ResponseEntity.ok(tareaService.createTarea(tarea));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TareaResponseDTO> updateTarea(@PathVariable Long id, @RequestBody Tarea tarea) {
        return ResponseEntity.ok(tareaService.updateTarea(id, tarea));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarea(@PathVariable Long id) {
        tareaService.deleteTarea(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<TareaResponseDTO> updateEstado(@PathVariable Long id, @RequestParam String estado) {
        return ResponseEntity.ok(tareaService.updateEstado(id, estado));
    }


}
