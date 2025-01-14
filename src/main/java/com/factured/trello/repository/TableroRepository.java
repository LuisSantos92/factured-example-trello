package com.factured.trello.repository;

import com.factured.trello.entity.Tablero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableroRepository extends JpaRepository<Tablero, Long> {
    // Puedes agregar métodos personalizados aquí si los necesitas.
}
