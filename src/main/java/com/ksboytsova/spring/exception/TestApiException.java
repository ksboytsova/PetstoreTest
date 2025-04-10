package com.ksboytsova.spring.exception;

public class TestApiException extends RuntimeException {
    /**
     * Ошибка работы тестового фрейморка с причиной ее вызвавшей
     *
     * @param cause причина нарушения работы
     */
    public TestApiException(Throwable cause) {
        super(cause);
    }

    /**
     * Ошибка работы тестового фрейморка с сообщением
     *
     * @param msg сообщение расшифровывающее ошибку
     */
    public TestApiException(String msg) {
        super(msg);
    }
}
