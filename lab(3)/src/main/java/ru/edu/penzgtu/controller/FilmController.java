package ru.edu.penzgtu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.edu.penzgtu.baseresponse.BaseResponseService;
import ru.edu.penzgtu.baseresponse.ResponseWrapper;
import ru.edu.penzgtu.dto.FilmDto;
import ru.edu.penzgtu.service.FilmService;


import java.util.List;

@Validated
@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
@Tag(name = "Фильмы", description = "Операции над фильмами")
public class FilmController {
    private final FilmService filmService;
    private final BaseResponseService baseResponseService;

    @Operation(
            summary = "Получение всех фильмов",
            description = "Позволяет выгрузить все фильмы из БД")
    @GetMapping
    public ResponseWrapper<List<FilmDto>> findAllPicture() {
        return baseResponseService.wrapSuccessResponse(filmService.findAllFilms());
    }

    @Operation(
            summary = "Получение фильма по ID",
            description = "Позволяет выгрузить один фильм по ID из БД")
    @GetMapping("/{id}")
    public ResponseWrapper<FilmDto> getFilmById(@PathVariable @Min(0) Long id) {
        return baseResponseService.wrapSuccessResponse(filmService.findFilmById(id));
    }

    @Operation(
            summary = "Создать фильм",
            description = "Позволяет создать новую запись о фильме в БД"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createFilm(@RequestBody @Valid FilmDto film) {
        filmService.saveFilm(film);
    }

    @Operation(
            summary = "Обновить данные о фильме ",
            description = "Позволяет обновить информацию о фильме в БД")
    @PutMapping("/")
    public void updateFilm(@RequestBody @Valid FilmDto film) {
        filmService.updateFilm(film);
    }

    @Operation(
            summary = "Удалить фильм по ID ",
            description = "Позволяет удалить фильм по ID из БД")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFilm(@PathVariable @Min(0) Long id) {
        filmService.deleteFilmById(id);
    }
}
