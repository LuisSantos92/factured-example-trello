package com.factured.trello.repository;

import com.factured.trello.entity.Tablero;
import com.factured.trello.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByTablero(Tablero tablero);

    @Query("SELECT t.estado, COUNT(t) FROM Tarea t GROUP BY t.estado")
    Map<String, Long> countTareasPorEstado();
}

