package com.factured.trello.service;

import com.factured.trello.entity.Tablero;
import com.factured.trello.entity.Tarea;
import com.factured.trello.repository.TareaRepository;
import com.factured.trello.utils.EstadoTarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public List<Tarea> getAllTareas() {
        return tareaRepository.findAll();
    }

    public Tarea getTareaById(Long id) {
        return tareaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con ID: " + id));
    }

    public Tarea createTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public Tarea updateTarea(Long id, Tarea tarea) {
        Tarea existingTarea = getTareaById(id);
        existingTarea.setTitulo(tarea.getTitulo());
        existingTarea.setDescripcion(tarea.getDescripcion());
        existingTarea.setEstado(tarea.getEstado());
        return tareaRepository.save(existingTarea);
    }

    public void deleteTarea(Long id) {
        tareaRepository.deleteById(id);
    }

    public Tarea updateEstado(Long id, String estado) {
        Tarea tarea = getTareaById(id);
        if (!estado.equals("pendiente") && !estado.equals("en progreso") && !estado.equals("completada")) {
            throw new IllegalArgumentException("Estado inválido: " + estado);
        }
        tarea.setEstado(EstadoTarea.valueOf(estado));
        return tareaRepository.save(tarea);
    }

    public Map<String, Object> generateReport() {
        List<Tarea> tareas = tareaRepository.findAll();
        Map<String, Object> report = new HashMap<>();

        Map<String, Long> conteoPorEstado = new HashMap<>();
        Map<Long, Long> conteoPorTablero = new HashMap<>();

        tareas.forEach(tarea -> {
            // Conteo por estado
            conteoPorEstado.put(String.valueOf(tarea.getEstado()),
                    conteoPorEstado.getOrDefault(tarea.getEstado(), 0L) + 1);

            // Conteo por tablero
            Long tableroId = tarea.getTablero().getId();
            conteoPorTablero.put(tableroId,
                    conteoPorTablero.getOrDefault(tableroId, 0L) + 1);
        });

        report.put("conteoPorEstado", conteoPorEstado);
        report.put("conteoPorTablero", conteoPorTablero);
        return report;
    }


}
