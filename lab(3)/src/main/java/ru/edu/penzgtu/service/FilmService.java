package ru.edu.penzgtu.service;

import lombok.extern.slf4j.Slf4j;
import ru.edu.penzgtu.dto.FilmDto;
import ru.edu.penzgtu.entity.Film;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import ru.edu.penzgtu.exception.ErrorType;
import ru.edu.penzgtu.exception.PenzGtuException;
import ru.edu.penzgtu.repo.FilmRepository;
import ru.edu.penzgtu.service.mapper.FilmMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FilmService {
    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    public List<FilmDto> findAllFilms() {
        log.info("Найдены все существующие фильмы в БД");
        return filmMapper.toListDto(filmRepository.findAll());
    }

    public FilmDto findFilmById(Long id) {
        log.info("Найден фильм по id: " + id);
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND, "Фильм с id " + id + " не найден"));
        return filmMapper.toDto(film);
    }

    public void saveFilm( FilmDto filmDto) {
        log.info("Фильм сохранён");
        Film film = filmMapper.toEntity(filmDto);
        film.setLocalDateTime(LocalDateTime.now());
        filmRepository.save(film);
    }

    public void updateFilm( FilmDto newFilm) {
        log.info("Данные о фильме были обновлены");
        Film oldFilm = filmRepository.findById(newFilm.getId())
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND, "Фильм не найден "));
        oldFilm.setTitle(newFilm.getTitle());
        oldFilm.setDescription(newFilm.getDescription());
        oldFilm.setGenre(newFilm.getGenre());
        oldFilm.setFormat(newFilm.getFormat());
        filmRepository.save(oldFilm);
    }

    public void deleteFilmById( Long id) {
        log.info("Удалён фильм с id: " + id);
        filmRepository.deleteById(id);
    }
}
