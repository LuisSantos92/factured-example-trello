package com.factured.trello.service;

import com.factured.trello.dto.TareaResponseDTO;
import com.factured.trello.entity.Tarea;
import com.factured.trello.repository.TareaRepository;
import com.factured.trello.utils.EstadoTarea;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public List<TareaResponseDTO> getAllTareas() {
        return tareaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public TareaResponseDTO getTareaById(Long id) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con ID: " + id));
        return mapToDTO(tarea);
    }

    public TareaResponseDTO createTarea(Tarea tarea) {
        Tarea savedTarea = tareaRepository.save(tarea);
        return mapToDTO(savedTarea);
    }

    public TareaResponseDTO updateTarea(Long id, Tarea tarea) {
        Tarea existingTarea = tareaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con ID: " + id));
        existingTarea.setTitulo(tarea.getTitulo());
        existingTarea.setDescripcion(tarea.getDescripcion());
        existingTarea.setEstado(tarea.getEstado());
        Tarea updatedTarea = tareaRepository.save(existingTarea);
        return mapToDTO(updatedTarea);
    }

    public void deleteTarea(Long id) {
        tareaRepository.deleteById(id);
    }

    public TareaResponseDTO updateEstado(Long id, String estado) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con ID: " + id));
        tarea.setEstado(EstadoTarea.valueOf(estado.toUpperCase()));
        Tarea updatedTarea = tareaRepository.save(tarea);
        return mapToDTO(updatedTarea);
    }

    private TareaResponseDTO mapToDTO(Tarea tarea) {
        TareaResponseDTO dto = new TareaResponseDTO();
        dto.setId(tarea.getId());
        dto.setTitulo(tarea.getTitulo());
        dto.setDescripcion(tarea.getDescripcion());
        dto.setEstado(tarea.getEstado().name());
        dto.setTableroId(tarea.getTablero().getId());
        dto.setTableroNombre(tarea.getTablero().getNombre());
        return dto;
    }
}
