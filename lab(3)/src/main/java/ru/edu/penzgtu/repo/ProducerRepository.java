package ru.edu.penzgtu.repo;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.penzgtu.entity.Producer;

import java.util.List;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {

    Producer findByName(String name);

}
