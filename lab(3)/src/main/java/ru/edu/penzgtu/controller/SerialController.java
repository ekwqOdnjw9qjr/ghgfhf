package ru.edu.penzgtu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.edu.penzgtu.baseresponse.BaseResponseService;
import ru.edu.penzgtu.baseresponse.ResponseWrapper;
import ru.edu.penzgtu.dto.SerialDto;
import ru.edu.penzgtu.service.SerialService;

import java.util.List;
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("serials")
@Tag(name = "Сериалы",description = "Операции над сериалами")
public class SerialController {
    private final SerialService serialService;
    private final BaseResponseService baseResponseService;

    @Operation(
            summary = "Получение всех сериалов",
            description = "Позволяет выгрузить все сериалы из БД"
    )
    @GetMapping
    public ResponseWrapper<List<SerialDto>> findAll(){
        return baseResponseService.wrapSuccessResponse(serialService.findAllSerials());
    }


    @Operation(
            summary = "Получение сериалов по ID",
            description = "Позволяет выгрузить один сериал по ID из БД"
    )
    @GetMapping("/{id}")
    public ResponseWrapper<SerialDto> getById(@PathVariable @Min(0) Long id) {
        return baseResponseService.wrapSuccessResponse(serialService.findSerialById(id));
    }

    @Operation(
            summary = "Создать сериал",
            description = "Позволяет создать новую запись о сериале в БД"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSerial(@RequestBody @Valid SerialDto serial){
        serialService.createSerial(serial);
    }
    @Operation(
            summary = "Обновить данные о сериале",
            description = "Позволяет обновить информацию о сериале в БД"
    )
    @Transactional
    @PutMapping("/")
    public void updateSerial(@RequestBody  @Valid SerialDto serial) {

        serialService.updateSerial(serial);
    }


    @Operation(
            summary = "Удалить сериал по ID",
            description = "Позволяет удалить сериал по ID из БД"
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSerial(@PathVariable @Min(0) Long id) {
        serialService.deleteSerialById(id);
    }
}
