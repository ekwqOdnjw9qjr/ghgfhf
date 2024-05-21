package ru.edu.penzgtu.repo;

import org.springframework.stereotype.Repository;
import ru.edu.penzgtu.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    Film findByTitle(String title);

}