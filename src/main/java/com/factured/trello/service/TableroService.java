package com.factured.trello.service;

import com.factured.trello.entity.Tablero;
import com.factured.trello.repository.TableroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TableroService {

    @Autowired
    TableroSyncService tableroSyncService;

    private final TableroRepository tableroRepository;

    public TableroService(TableroRepository tableroRepository) {
        this.tableroRepository = tableroRepository;
    }

    public List<Tablero> getAllTableros() {
        return tableroRepository.findAll();
    }

    public Tablero getTableroById(Long id) {
        return tableroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tablero no encontrado con ID: " + id));
    }


    public Tablero createTablero(Tablero tablero) {
        Tablero saved = tableroRepository.save(tablero);
        tableroSyncService.syncTablero(saved);
        return saved;
    }


    public Tablero updateTablero(Long id, Tablero tablero) {
        Tablero existingTablero = getTableroById(id);
        existingTablero.setNombre(tablero.getNombre());
        existingTablero.setDescripcion(tablero.getDescripcion());
        return tableroRepository.save(existingTablero);
    }

    public void deleteTablero(Long id) {
        tableroRepository.deleteById(id);
    }
}