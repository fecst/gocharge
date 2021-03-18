package br.com.gocharge.command;

public interface CommandProcessor<T> {
    public T process(CommandContext commandContext);
}
