package ru.edu.penzgtu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edu.penzgtu.entity.Serial;

import java.util.List;

@Repository
public interface SerialRepository extends JpaRepository<Serial, Long> {
    Serial findByTitle(String title);

}