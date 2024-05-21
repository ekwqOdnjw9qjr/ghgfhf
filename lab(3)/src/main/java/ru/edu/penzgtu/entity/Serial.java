package ru.edu.penzgtu.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Table( name = "Serials")
public class Serial {
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

    @Column(name = "mainActor",nullable = false,length = 77)
    private String mainActor ;

    @Column(name = "date_and_time")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "studio_id")
    private Studio studio;}
