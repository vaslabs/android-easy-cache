package com.vincentbrison.openlibraries.android.dualcache.lib;

import android.util.Base64;

import org.vaslabs.vserializer.AlphabeticalSerializer;
import org.vaslabs.vserializer.VSerializer;

/**
 * Created by vnicolaou on 08/05/16.
 */
public class MemoryEfficientSerializer<T> implements Serializer<T> {

    private static final VSerializer vSerializer = new AlphabeticalSerializer();
    private final Class clazz;

    public MemoryEfficientSerializer(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T fromString(String data) {
        byte[] bytes = Base64.decode(data, Base64.DEFAULT);
        return (T) vSerializer.deserialise(bytes, clazz);
    }

    @Override
    public String toString(T object) {
        byte[] bytes = vSerializer.serialize(object);
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }
}
