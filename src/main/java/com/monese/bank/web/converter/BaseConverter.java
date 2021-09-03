package com.monese.bank.web.converter;

public abstract class BaseConverter<S, D> {

    public D convert(S source, D destination) {
        if (source == null) {
            return null;
        } else {
            convertInner(source, destination);
            return destination;
        }
    }

    public D convert(S source) {
        return convert(source, newObject());
    }

    protected abstract void convertInner(S source, D destination);

    protected abstract D newObject();
}
