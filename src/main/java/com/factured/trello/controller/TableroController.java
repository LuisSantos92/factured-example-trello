package com.factured.trello.controller;

import com.factured.trello.entity.Tablero;
import com.factured.trello.service.TableroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tableros")
public class TableroController {

    private final TableroService tableroService;

    public TableroController(TableroService tableroService) {
        this.tableroService = tableroService;
    }

    @GetMapping
    public ResponseEntity<List<Tablero>> getAllTableros() {
        return ResponseEntity.ok(tableroService.getAllTableros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tablero> getTableroById(@PathVariable Long id) {
        return ResponseEntity.ok(tableroService.getTableroById(id));
    }

    @PostMapping
    public ResponseEntity<Tablero> createTablero(@RequestBody Tablero tablero) {
        return ResponseEntity.ok(tableroService.createTablero(tablero));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tablero> updateTablero(@PathVariable Long id, @RequestBody Tablero tablero) {
        return ResponseEntity.ok(tableroService.updateTablero(id, tablero));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTablero(@PathVariable Long id) {
        tableroService.deleteTablero(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/bulk-generate")
    public ResponseEntity<String> generateMassiveData(@RequestParam int count) {
        tableroService.generateMassiveData(count);
        return ResponseEntity.ok("Se generaron " + count + " tableros correctamente.");
    }
}
