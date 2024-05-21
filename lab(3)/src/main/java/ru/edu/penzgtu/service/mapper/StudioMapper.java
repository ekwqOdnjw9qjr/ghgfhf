package ru.edu.penzgtu.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.dto.StudioDto;
import ru.edu.penzgtu.entity.Studio;
import ru.edu.penzgtu.entity.Serial;
import ru.edu.penzgtu.repo.SerialRepository;

import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class StudioMapper {
    private final SerialRepository serialRepository;


    public List<StudioDto> toListDto(List<Studio> studios) {
        return studios.stream().map(this::toDto).toList();
    }

    public StudioDto toDto(Studio studio) {
        return StudioDto.builder()
                .id(studio.getId())
                .title(studio.getName())
                .country(studio.getCountry())
                .mainGenre(studio.getMainGenre())
                .mainGenre(studio.getMainGenre())
                .localDateTime(studio.getLocalDateTime())
                .serials(studio.getSerials().stream()
                        .map(Serial::getTitle)
                        .toList())
                .build();
    }

    public Studio toEntity(StudioDto studioDto) {
        Studio studio = new Studio();

        studio.setId(studioDto.getId());
        studio.setName(studioDto.getTitle());
        studio.setCountry(studioDto.getCountry());
        studio.setMainGenre(studioDto.getMainGenre());
        studio.setDirector(studioDto.getDirector());
        studio.setLocalDateTime(studioDto.getLocalDateTime());
        studio.setSerials((Collections.singletonList
                (serialRepository.findByTitle(String.valueOf(studioDto.getSerials().stream().toList())))));


        return studio;
    }

}
