package ru.edu.penzgtu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.penzgtu.entity.Studio;

import java.util.List;

public interface StudioRepository extends JpaRepository<Studio,Long> {

    Studio findByName(String name);

}