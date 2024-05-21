package ru.edu.penzgtu.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description",nullable = false,length = 77)
    private String description;

    @Column(name = "genre",nullable = false,length = 77)
    private String genre;

    @Column(name = "format",nullable = false,length = 77)
    private String format ;

    @Column(name = "date_and_time")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    private Producer producer;
}

