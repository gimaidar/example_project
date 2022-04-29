package com.gimaletdinov.exampleProject.exception;

/**
 * Исключение, генерируемое в программе для несуществующих данных в БД и отправляемое пользователю
 */
public class NoSuchObjectException extends RuntimeException{
    /**
     *
     * @param message Текст ошибки
     */
    public NoSuchObjectException(String message) {
        super(message);
    }
}
