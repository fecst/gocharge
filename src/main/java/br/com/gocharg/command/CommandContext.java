package br.com.gocharg.command;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Optional;

public class CommandContext extends HashMap<String, Object> implements Serializable {
    private static final String DATA = "data";
    private static final String RESULT = "result";

    public CommandContext(final Object data) {
        super.put(DATA, data);
    }

    public CommandContext() {
        super();
    }

    public Class<?> getDataClass() {
        return Optional.ofNullable(get(DATA)).map(Object::getClass).orElse(null);
    }

    public Class<?> getResult() {
        return Optional.ofNullable(get(RESULT)).map(Object::getClass).orElse(null);
    }

    public void setResult(final Object result) {
        put(RESULT, result);
    }

    public <T extends Object> T getData(final Class<T> clazz) {
        return getProperty(DATA, clazz);
    }

    public void setData(final Object data) {
        put(DATA, data);
    }

    public <T extends Object> T getResult(final Class<T> clazz) {
        return getProperty(RESULT, clazz);
    }

    public <R> R getProperty(final String key, final Class<R> clazz) {
        return Optional.ofNullable(get(key)).map(clazz::cast).orElse(null);
    }
}
