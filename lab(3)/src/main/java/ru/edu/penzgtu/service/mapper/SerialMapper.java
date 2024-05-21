package ru.edu.penzgtu.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.dto.SerialDto;
import ru.edu.penzgtu.entity.Serial;
import ru.edu.penzgtu.repo.StudioRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SerialMapper {
    private final StudioRepository studioRepository;

    public List<SerialDto> toListDto(List<Serial> serials) {
        return serials.stream().map(this::toDto).toList();
    }

    public SerialDto toDto(Serial serial) {
        String studioTitle = serial.getStudio() != null ? serial.getStudio().getName() : null;
        return SerialDto.builder()
                .id(serial.getId())
                .title(serial.getTitle())
                .description(serial.getDescription())
                .genre(serial.getGenre())
                .mainActor(serial.getMainActor())
                .localDateTime(serial.getLocalDateTime())
                .studioTitle(studioTitle)
                .build();
    }

    public Serial toEntity(SerialDto serialDto) {
        Serial serial = new Serial();

        serial.setId(serialDto.getId());
        serial.setTitle(serialDto.getTitle());
        serial.setDescription(serialDto.getDescription());
        serial.setGenre(serialDto.getGenre());
        serial.setMainActor(serialDto.getMainActor());
        serial.setStudio(studioRepository.findByName(serialDto.getStudioTitle()));
        return serial;
    }
}
