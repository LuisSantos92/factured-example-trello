package com.factured.trello.service;

import com.factured.trello.entity.Tablero;
import com.factured.trello.mongo.TableroMongoRepository;
import com.factured.trello.repository.TableroRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TableroService {

    @Autowired
    TableroMongoRepository tableroMongoRepository;

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
        existingTablero.setFechaCreacion(tablero.getFechaCreacion());

        // Actualizar en PostgreSQL
        Tablero updatedTablero = tableroRepository.save(existingTablero);

        // Sincronizar en MongoDB
        tableroMongoRepository.save(updatedTablero);

        return updatedTablero;
    }


    public void deleteTablero(Long id) {
        Tablero tablero = getTableroById(id); // Asegúrate de que el tablero existe en PostgreSQL
        tableroRepository.deleteById(id);    // Eliminar de PostgreSQL
        tableroMongoRepository.deleteById(tablero.getId().toString()); // Eliminar de MongoDB
    }

    public void generateMassiveData(int count) {
        List<Tablero> tableros = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Tablero tablero = new Tablero();
            tablero.setNombre("Tablero " + i);
            tablero.setDescripcion("Descripción " + RandomStringUtils.randomAlphabetic(10));
            tablero.setFechaCreacion(LocalDate.now().minusDays((long) (Math.random() * 365)));
            tableros.add(tablero);
        }

        // Guarda en lotes para evitar sobrecargar la memoria
        for (int i = 0; i < tableros.size(); i += 1000) {
            int end = Math.min(i + 1000, tableros.size());
            List<Tablero> batch = tableros.subList(i, end);

            // Guardar en PostgreSQL
            List<Tablero> savedBatch = tableroRepository.saveAll(batch);

            // Sincronizar con MongoDB
            tableroMongoRepository.saveAll(savedBatch);
        }
    }
}
