package ru.edu.penzgtu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Schema(description = "Информация о режиссёре")
public class ProducerDto {

    @JsonProperty("id")
    @Schema(description = "ID режиссёра в БД", example = "123")
    private Long id;

    @JsonProperty("Имя")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Имя режиссёра может содержать только буквы и пробелы")
    @Schema(description = "Имя режиссёра", example = "Стивен Спилберг")
    private String name;

    @JsonProperty("Страна")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Страна режиссёра может содержать только буквы и пробелы")
    @Schema(description = "Страна режиссёра",example = "США")
    private String country;


    @JsonProperty("Специализация")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Специализация режиссёра может содержать только буквы и пробелы")
    @Schema(description = "Специализация режиссёра", example = "Боевики")
    private String speciality;

    @JsonProperty("Любимый фильм")
    @NotBlank
    @Schema(description = "Любимый фильм режиссёра",
            example = "Крёстный отец")
    private String favFilm;

    @JsonProperty("Дата и время")
    @Schema(description = "Дата и время добавления художника в БД")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @JsonProperty("Фильмы")
    @Size(min = 0,max = 100,
            message = "Количество названий фильмов должно быть от 0 до 100")
    @Schema(description = "Названия фильмов режиссёра")
    private List<String> films;


    public ProducerDto(Long id, String name, String country, String speciality, String favFilm,
                       LocalDateTime localDateTime, List<String> films) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.localDateTime = localDateTime;
        this.speciality = speciality;
        this.favFilm = favFilm;
        this.films = films;
    }

}
