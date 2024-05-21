package ru.edu.penzgtu.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import ru.edu.penzgtu.dto.ProducerDto;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.entity.Producer;
import ru.edu.penzgtu.exception.ErrorType;
import ru.edu.penzgtu.exception.PenzGtuException;
import ru.edu.penzgtu.repo.ProducerRepository;
import lombok.RequiredArgsConstructor;
import ru.edu.penzgtu.service.mapper.ProducerMapper;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class ProducerService {
    private final ProducerRepository producerRepository;
    private final ProducerMapper producerMapper;
    @Transactional
    public List<ProducerDto> findAllProducers(){
        log.info("Найдены все существующие режиссёры в БД");
        return producerMapper.toListDto(producerRepository.findAll());
    }


    public ProducerDto findProducerById(Long id)  {
        log.info("Найден режиссёр по id: " + id);
        Producer producer = producerRepository.findById(id)
                .orElseThrow(()-> new PenzGtuException(ErrorType.NOT_FOUND,"Режиссёр с id " + id + " не найден"));
        return producerMapper.toDto(producer);
    }

    public void createProducer(ProducerDto producerDto){
        log.info("Режиссёр создан");
        Producer producer = producerMapper.toEntity(producerDto);
        producer.setLocalDateTime(LocalDateTime.now());
        producerRepository.save(producer);
    }

    public void updateProducer(ProducerDto newProducer) {
        log.info("Данные о режиссёры были обновлены");
        Producer oldProducer = producerRepository.findById(newProducer.getId())
                .orElseThrow(() ->new PenzGtuException(ErrorType.NOT_FOUND,"Режиссёр не найден"));
        oldProducer.setName(newProducer.getName());
        oldProducer.setCountry(newProducer.getCountry());
        oldProducer.setSpeciality(newProducer.getSpeciality());
        oldProducer.setFavFilm(oldProducer.getFavFilm());
        producerRepository.save(oldProducer);
    }


    public void deleteProducerById(Long id ) {
        log.info("Удалён режиссёр с id: " + id);
        producerRepository.deleteById(id);
    }

}
