package ru.edu.penzgtu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Информация о сериале")
public class SerialDto {


    @JsonProperty("id")
    @Schema(description = "ID сериала в БД", example = "123")
    private Long id;


    @JsonProperty("Название")
    @Schema(description = "Название сериала в БД", example = "Сверхъестественное")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Название сериала может содержать только буквы")
    private String title;


    @JsonProperty("Описание")
    @Schema(description = "Описание сериала", example = "Про паранормальные явления")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Описание сериала может содержать только буквы")
    private String description;


    @JsonProperty("Жанр")
    @Schema(description = "Жанр сериала", example = "Ужасы")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Жанр сериала может содержать только буквы")
    private String genre;


    @JsonProperty("Главный актёр")
    @Schema(description = "Главный актёр", example = "Джаред Падалеки")
    @NotBlank
    private String mainActor;


    @JsonProperty("Дата и время")
    @Schema(description = "Дата и время добавления сериала в БД")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;


    @JsonProperty("Студия")
    @Schema(description = "Название студии", example = "Kripke Enterprises")
    private String studioTitle;

    public SerialDto(Long id, String title, String description, String genre,
                     String mainActor, LocalDateTime localDateTime, String studioTitle) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.mainActor = mainActor;
        this.localDateTime = localDateTime;
        this.studioTitle = studioTitle;
    }
}
