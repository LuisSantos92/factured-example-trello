package com.factured.trello.controller;

import com.factured.trello.entity.Tablero;
import com.factured.trello.service.TableroQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/query/tableros")
public class TableroQueryController {

    private final TableroQueryService tableroQueryService;

    public TableroQueryController(TableroQueryService tableroQueryService) {
        this.tableroQueryService = tableroQueryService;
    }

    @GetMapping
    public ResponseEntity<List<Tablero>> getAllTableros() {
        return ResponseEntity.ok(tableroQueryService.getAllTableros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tablero> getTableroById(@PathVariable String id) {
        return ResponseEntity.ok(tableroQueryService.getTableroById(id));
    }
}
