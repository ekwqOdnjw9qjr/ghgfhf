package ru.edu.penzgtu.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorType {
    COMMON_ERROR("Ошибка бизнес логики", "Повторите запрос позже"),
    NOT_FOUND("Не удалось найти ресурс", "По вашему запросу рессурс не найден"),
    CLIENT_ERROR("Ошибка в запросе", "Проверьте параметры и повторитье запрос");

    private final String title;
    private final String text;
}
