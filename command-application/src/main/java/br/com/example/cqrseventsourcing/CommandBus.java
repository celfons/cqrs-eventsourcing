package br.com.example.cqrseventsourcing;

import br.com.example.cqrseventsourcing.command.Command;
import br.com.example.cqrseventsourcing.handler.Handler;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommandBus {

    private Map<String, Handler<? extends Command>> handlers = new HashMap<>();

    public CommandBus(List<Handler> handlers) {
        handlers.forEach(this::addHandler);
    }

    public void send(Command command) {
        Handler handler = handlers.get(command.getClass().getCanonicalName());
        if (handler == null) {
            throw new IllegalArgumentException("There is no handler for this command");
        }
        handler.handle(command);
    }

    private  void addHandler(Handler<? extends Command> handler) {
        handlers.put(handler.getCommandClass().getCanonicalName(), handler);
    }

}
