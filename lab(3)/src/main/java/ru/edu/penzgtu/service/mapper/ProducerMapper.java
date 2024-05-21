package ru.edu.penzgtu.service.mapper;

 import lombok.RequiredArgsConstructor;
 import org.springframework.stereotype.Service;
 import ru.edu.penzgtu.dto.ProducerDto;
 import ru.edu.penzgtu.entity.Film;
 import ru.edu.penzgtu.entity.Producer;
 import ru.edu.penzgtu.repo.FilmRepository;

 import java.util.Collections;
 import java.util.List;

 @Service
 @RequiredArgsConstructor
 public class ProducerMapper {
    private final FilmRepository filmRepository;


 public List<ProducerDto> toListDto(List<Producer> producers) {
 return producers.stream().map(this::toDto).toList();
 }

    public ProducerDto toDto(Producer producer) {
    return ProducerDto.builder()
            .id(producer.getId())
            .name(producer.getName())
            .country(producer.getCountry())
            .speciality(producer.getSpeciality())
            .favFilm(producer.getFavFilm())
            .localDateTime(producer.getLocalDateTime())
        .films(producer.getFilms().stream()
        .map(Film::getTitle)
        .toList())
            .build();
 }

     public Producer toEntity(ProducerDto producerDto) {
     Producer producer = new Producer();

     producer.setId(producerDto.getId());
     producer.setName(producerDto.getName());
     producer.setCountry(producerDto.getCountry());
     producer.setSpeciality(producerDto.getSpeciality());
     producer.setFavFilm(producerDto.getFavFilm());
     producer.setLocalDateTime(producerDto.getLocalDateTime());
     producer.setFilms((Collections.singletonList
             (filmRepository.findByTitle(String.valueOf(producerDto.getFilms().stream().toList())))));

     return producer;
  }
 }