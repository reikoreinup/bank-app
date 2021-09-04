package com.monese.bank.web.converter;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseConverter<S, D> {

    public D convert(S source, D destination) {
        if (source == null) {
            return null;
        } else {
            convertInner(source, destination);
            return destination;
        }
    }

    public List<D> convertAll(List<S> sources) {
        return sources.stream().map(this::convert).collect(Collectors.toList());
    }

    public D convert(S source) {
        return convert(source, newObject());
    }

    protected abstract void convertInner(S source, D destination);

    protected abstract D newObject();
}
