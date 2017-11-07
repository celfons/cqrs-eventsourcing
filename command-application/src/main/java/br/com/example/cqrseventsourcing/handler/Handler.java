package br.com.example.cqrseventsourcing.handler;

import br.com.example.cqrseventsourcing.command.Command;

public interface Handler<T extends Command> {
    void handle(T command);
    Class<T> getCommandClass();
}
