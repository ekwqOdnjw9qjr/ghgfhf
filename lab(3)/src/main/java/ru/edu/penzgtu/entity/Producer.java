package ru.edu.penzgtu.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "producer")
public class Producer {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name",nullable = false,length = 77)
    private String name;

    @Column(name = "country",nullable = false,length = 77)
    private String country;

    @Column(name = "speciality",nullable = false,length = 77)
    private String speciality;

    @Column(name = "favFilm",nullable = false,length = 77)
    private String favFilm ;

    @Column(name = "date_and_time")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;


    @OneToMany(mappedBy = "producer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Film> films;}