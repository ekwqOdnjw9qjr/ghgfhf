package ru.edu.penzgtu.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.dto.FilmDto;
import ru.edu.penzgtu.entity.Film;
import ru.edu.penzgtu.repo.ProducerRepository;

import java.util.List;
@RequiredArgsConstructor
@Service
public class FilmMapper {
    private final ProducerRepository producerRepository;

    public List<FilmDto> toListDto(List<Film> films) {
        return films.stream().map(this::toDto).toList();
    }

    public FilmDto toDto(Film film) {
        String producerName = film.getProducer() != null ? film.getProducer().getName() : null;

        return FilmDto.builder()
                .id(film.getId())
                .title(film.getTitle())
                .description(film.getDescription())
                .genre(film.getGenre())
                .format(film.getFormat())
                .localDateTime(film.getLocalDateTime())
                .producerName(producerName)
                .build();
    }

    public Film toEntity(FilmDto filmDto) {
        Film film = new Film();

        film.setId(filmDto.getId());
        film.setTitle(filmDto.getTitle());
        film.setDescription(filmDto.getDescription());
        film.setGenre(filmDto.getGenre());
        film.setFormat(filmDto.getFormat());
        film.setProducer(producerRepository.findByName(filmDto.getProducerName()));


        return film;
    }
}
