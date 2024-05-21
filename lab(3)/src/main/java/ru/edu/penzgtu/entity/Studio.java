package ru.edu.penzgtu.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "studio")
public class Studio {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name",nullable = false,length = 77)
    private String name;

    @Column(name = "country",nullable = false,length = 77)
    private String country;

    @Column(name = "mainGenre",nullable = false,length = 77)
    private String mainGenre;

    @Column(name = "director",nullable = false,length = 77)
    private String director;

    @Column(name = "date_and_time")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @OneToMany(mappedBy = "studio",fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, orphanRemoval = true)
    private List<Serial> serials;}
