package br.com.gocharg.command;

public interface CommandProcessor<T> {
    public T process(CommandContext context);
}
