package org.example.commands.abstracts;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.model.data.Role;

/**
 * Абстрактный класс, представляющий базовую команду.
 * Он реализует интерфейс {@link Executable}, что позволяет выполнять команду.
 * Класс содержит общие поля и методы, которые могут быть унаследованы другими классами-командами.
 */
@Data
@AllArgsConstructor
public abstract class Command implements Executable {

    /** Роль, требуемая для выполнения команды. */
    protected Role role;

    /** Наименование команды. */
    protected String name;

    /** Описание функционала, предоставляемого командой. */
    protected String description;

    /**
     * Представляет текстовое представление команды.
     * Возвращает строку, содержащую наименование и описание команды.
     *
     * @return Строковое представление команды в формате "наименование: описание".
     */
    @Override
    public String toString() {
        return name + " : " + description;
    }
}
