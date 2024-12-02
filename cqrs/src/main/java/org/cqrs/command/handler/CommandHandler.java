package org.cqrs.command.handler;

public interface CommandHandler<T> {
    void handle(T command);
}
