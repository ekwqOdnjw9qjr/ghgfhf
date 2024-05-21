package ru.edu.penzgtu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.edu.penzgtu.baseresponse.BaseResponseService;
import ru.edu.penzgtu.baseresponse.ResponseWrapper;
import ru.edu.penzgtu.dto.StudioDto;
import ru.edu.penzgtu.service.StudioService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/studios")
@RequiredArgsConstructor
@Tag(name = "Студии", description = "Операции над студиями")
public class StudioController {

    private final StudioService studioService;
    private final BaseResponseService baseResponseService;

    @Operation(
            summary = "Получение всех студий",
            description = "Позволяет выгрузить все студии из БД"
    )

    @GetMapping
    public ResponseWrapper<List<StudioDto>> findAll(){
        return baseResponseService.wrapSuccessResponse(studioService.findAllStudios());
    }

    @Operation(
            summary = "Получение студий по ID",
            description = "Позволяет выгрузить одну студию по ID из БД"
    )
    @GetMapping("/{id}")
    public ResponseWrapper<StudioDto> getById(@PathVariable @Min(0) Long id)  {
        return baseResponseService.wrapSuccessResponse(studioService.findStudioById(id));
    }

    @Operation(
            summary = "Создать студию",
            description = "Позволяет создать новую запись о студии в БД"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudio(@RequestBody @Valid StudioDto studio){
        studioService.createStudio(studio);
    }

    @Operation(
            summary = "Обновить данные о студии",
            description = "Позволяет обновить информацию о студии в БД"
    )
    @Transactional
    @PutMapping("/")
    public void updateStudio(@RequestBody  @Valid StudioDto studio) {
        studioService.updateStudio(studio);
    }

    @Operation(
            summary = "Удалить студию по ID",
            description = "Позволяет удалить студию по ID из БД"
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudio(@PathVariable @Min(0) Long id) {
        studioService.deleteStudioById(id);
    }
}



