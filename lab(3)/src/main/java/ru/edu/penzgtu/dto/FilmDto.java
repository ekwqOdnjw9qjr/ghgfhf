package ru.edu.penzgtu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Информация о фильме")
public class FilmDto {

    @JsonProperty("id")
    @Schema(description = "ID фильма в БД", example = "1")
    private Long id;

    @JsonProperty("Название")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Название фильма может содержать только буквы")
    @Schema(description = "Название фильма", example = "Назад в будущее")
    private String title;

    @JsonProperty("Описание")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Описание фильма может содержать только буквы")
    @Schema(description = "Описание фильма", example = "Классный фильм")
    private String description;

    @JsonProperty("Жанр")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Жанр фильма может содержать только буквы")
    @Schema(description = "Жанр фильма", example = "Боевик")
    private String genre;

    @JsonProperty("Формат")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Название формата может содержать только буквы")
    @Schema(description = "Формат фильма", example = "Полнометражный")
    private String format;

    @JsonProperty("Дата и время")
    @Schema(description = "Дата и время добавления фильма в БД")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @JsonProperty("Режиссёр")
    @Schema(description = "Имя режиссёра", example = "Стивен Спилберг")
    private String producerName;

    public FilmDto(Long id, String title, String description, String genre,
                   String format, LocalDateTime localDateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.format = format;
        this.localDateTime = localDateTime;
    }
}
