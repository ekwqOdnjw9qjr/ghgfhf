package ru.edu.penzgtu.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.dto.StudioDto;
import ru.edu.penzgtu.entity.Studio;
import ru.edu.penzgtu.exception.ErrorType;
import ru.edu.penzgtu.exception.PenzGtuException;
import ru.edu.penzgtu.repo.StudioRepository;
import ru.edu.penzgtu.service.mapper.StudioMapper;

import java.time.LocalDateTime;
import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class StudioService {
    private final StudioRepository studioRepository;
    private final StudioMapper studioMapper;

    public List<StudioDto> findAllStudios(){
        log.info("Найдены все существующие студии в БД");
        return studioMapper.toListDto(studioRepository.findAll());
    }


    public StudioDto findStudioById(Long id)  {
        log.info("Найдена студия по id: " + id);
        Studio studio = studioRepository.findById(id)
                .orElseThrow(()-> new PenzGtuException(ErrorType.NOT_FOUND,"Студия с id " + id + " не найдена"));
        return studioMapper.toDto(studio);
    }

    public void createStudio(StudioDto studioDto){
        log.info("Студия сохранен");
        Studio studio = studioMapper.toEntity(studioDto);
        studio.setLocalDateTime(LocalDateTime.now());
        studioRepository.save(studio);
    }


    public void updateStudio(StudioDto newStudio) {
        log.info("Данные о студии были обновлены");
        Studio oldStudio = studioRepository.findById(newStudio.getId())
                .orElseThrow(() ->new PenzGtuException(ErrorType.NOT_FOUND,"Автор не найден"));
        oldStudio.setName(newStudio.getTitle());
        oldStudio.setCountry((newStudio.getCountry()));
        oldStudio.setDirector(newStudio.getDirector());
        oldStudio.setMainGenre(newStudio.getMainGenre());
        studioRepository.save(oldStudio);
    }


    public void deleteStudioById(Long id ) {
        log.info("Удалена студия с id: " + id);
        studioRepository.deleteById(id);
    }

}


