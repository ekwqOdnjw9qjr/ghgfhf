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
import ru.edu.penzgtu.dto.ProducerDto;
import ru.edu.penzgtu.repo.ProducerRepository;
import ru.edu.penzgtu.service.ProducerService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/producers")
@RequiredArgsConstructor
@Tag(name = "Режиссёры", description = "Операции над режиссёрами")
public class ProducerController {
    private final ProducerService producerService;
    private final BaseResponseService baseResponseService;
    private final ProducerRepository producerRepository;

    @Operation(
            summary = "Получение всех режиссёров",
            description = "Позволяет выгрузить всех режиссёров из БД"
    )
    @GetMapping
    public ResponseWrapper<List<ProducerDto>> findAll() {
        return baseResponseService.wrapSuccessResponse(producerService.findAllProducers());
    }

    @Operation(
            summary = "Получение режиссёра по ID",
            description = "Позволяет выгрузить одного режиссёра по ID из БД"
    )
    @GetMapping("/{id}")
    public ResponseWrapper<ProducerDto> getById(@PathVariable @Min(0) Long id) {
        return baseResponseService.wrapSuccessResponse(producerService.findProducerById(id));
    }

    @Operation(
            summary = "Создать режиссёра",
            description = "Позволяет создать новую запись о режиссёре в БД"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProducer(@RequestBody @Valid ProducerDto producer) {
        producerService.createProducer(producer);
    }


    @Operation(
            summary = "Обновить данные о режиссёре",
            description = "Позволяет обновить информацию о режиссёре в БД"
    )
    @PutMapping("/")
    public void updateProducer(@RequestBody @Valid ProducerDto producer) {
        producerService.updateProducer(producer);
    }


    @Operation(
            summary = "Удалить режиссёра по ID",
            description = "Позволяет удалить режиссёра по ID из БД"
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProducer(@PathVariable @Min(0) Long id) {
        producerService.deleteProducerById(id);
    }
}
