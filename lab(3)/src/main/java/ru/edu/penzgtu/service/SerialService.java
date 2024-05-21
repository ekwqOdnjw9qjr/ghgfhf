package ru.edu.penzgtu.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.dto.SerialDto;
import ru.edu.penzgtu.exception.ErrorType;
import ru.edu.penzgtu.exception.PenzGtuException;
import ru.edu.penzgtu.repo.SerialRepository;
import ru.edu.penzgtu.service.mapper.SerialMapper;
import ru.edu.penzgtu.entity.Serial;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SerialService {
    private final SerialRepository serialRepository;
    private final SerialMapper serialMapper;


    public List<SerialDto> findAllSerials() {
        log.info("Найдены все существующие сериалы в БД");
        return serialMapper.toListDto(serialRepository.findAll());
    }


    public SerialDto findSerialById(Long id) {
        log.info("Найден сериал по id: " + id);
        Serial serial = serialRepository.findById(id)
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND,"Сериал с id " + id + " на найден"));
        return serialMapper.toDto(serial);
    }

    public void  createSerial (SerialDto serialDto) {
        log.info("Сериал создан");
        Serial serial = serialMapper.toEntity(serialDto);
        serial.setLocalDateTime(LocalDateTime.now());
        serialRepository.save(serial);
    }


    public void updateSerial (SerialDto newSerial) {
        log.info("Данные о сериале были обновлены");
        Serial oldSerial = serialRepository.findById(newSerial.getId()).
                orElseThrow(()-> new PenzGtuException(ErrorType.NOT_FOUND,"Сериал не найден"));
        oldSerial.setTitle(newSerial.getTitle());
        oldSerial.setDescription(newSerial.getDescription());
        oldSerial.setGenre(newSerial.getGenre());
        oldSerial.setMainActor(newSerial.getMainActor());
        serialRepository.save(oldSerial);

    }


    public void deleteSerialById (Long id) {
        log.info("Удалён сериал с id: " + id);
        serialRepository.deleteById(id);
    }
}
