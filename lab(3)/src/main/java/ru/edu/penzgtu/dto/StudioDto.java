package ru.edu.penzgtu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Schema(description = "Информация о студии")
public class StudioDto {

    @JsonProperty("id")
    @Schema(description = "ID студии в БД", example = "1")
    private Long id;

    @JsonProperty("Название")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Название студии может содержать только буквы")
    @Schema(description = "Название студии", example = "Kripke Enterprises")
    private String title;

    @JsonProperty("Страна")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Страна студии может содержать только буквы")
    @Schema(description = "Страна студии", example = "США")
    private String country;

    @JsonProperty("Главный жанр")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Главный жанр может содержать только буквы")
    @Schema(description = "Главный жанр", example = "Ужасы")
    private String mainGenre;

    @JsonProperty("Директор")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Директор студии может содержать только буквы")
    @Schema(description = "Директор студии",example = "Eric Kripke")
    private String director;

    @JsonProperty("Дата и время")
    @Schema(description = "Дата и время добавления критика в БД")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @JsonProperty("Сериалы")
    @Size(min = 0,max = 100,
            message = "Количество манг должно быть от 0 до 100")
    @Schema(description = "Количество сериалов у студии")
    private List<String> serials;
    public StudioDto(Long id, String title, String country, String mainGenre,
                     String director, LocalDateTime localDateTime, List<String> serials) {
        this.id = id;
        this.title = title;
        this.country = country;
        this.mainGenre = mainGenre;
        this.director = director;
        this.localDateTime = localDateTime;
        this.serials = serials;
    }

}
