package org.jmmo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.function.Consumer;

public class ObjectSerializer {
    private ObjectSerializer() {}

    public static byte[] serialize(Object object) {
        return serialize(object, EMPTY_EXCEPTION_CONSUMER, EMPTY_BYTES_CONSUMER);
    }

    public static byte[] serialize(Object object, Consumer<? super IOException> exceptionConsumer) {
        return serialize(object, exceptionConsumer, EMPTY_BYTES_CONSUMER);
    }

    public static byte[] serialize(Consumer<byte[]> bytesConsumer, Object object) {
        return serialize(object, EMPTY_EXCEPTION_CONSUMER, bytesConsumer);
    }

    public static byte[] serialize(Object object, Consumer<? super IOException> exceptionConsumer, Consumer<byte[]> bytesConsumer) {
        try {
            final ByteArrayOutputStream bos = new ByteArrayOutputStream();
            final ObjectOutputStream oos = new ObjectOutputStream(bos);

            oos.writeObject(object);

            oos.flush();
            oos.close();

            final byte[] bytes = bos.toByteArray();
            bytesConsumer.accept(bytes);
            return bytes;
        }
        catch (IOException e) {
            exceptionConsumer.accept(e);
            return null;
        }
    }

    public static Object deserialize(byte[] bytes) {
        return deserialize(bytes, EMPTY_EXCEPTION_CONSUMER);
    }

    public static Object deserialize(byte[] bytes, Consumer<? super Exception> exceptionConsumer) {
        return deserialize(Object.class, bytes, exceptionConsumer, EMPTY_OBJECT_CONSUMER);
    }

    public static <T> T deserialize(Class<T> objectClass, Consumer<? super T> objectConsumer, byte[] bytes) {
        return deserialize(objectClass, bytes, EMPTY_EXCEPTION_CONSUMER, objectConsumer);
    }

    public static <T> T deserialize(Class<T> objectClass, byte[] bytes) {
        return deserialize(objectClass, bytes, EMPTY_EXCEPTION_CONSUMER, EMPTY_OBJECT_CONSUMER);
    }

    public static <T> T deserialize(Class<T> objectClass, byte[] bytes, Consumer<? super Exception> exceptionConsumer) {
        return deserialize(objectClass, bytes, exceptionConsumer, EMPTY_OBJECT_CONSUMER);
    }

    public static <T> T deserialize(Class<T> objectClass, byte[] bytes, Consumer<? super Exception> exceptionConsumer, Consumer<? super T> objectConsumer) {
        try {
            final ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            final ObjectInputStream ois = new ObjectInputStream(bis);

            final T object = objectClass.cast(ois.readObject());
            objectConsumer.accept(object);
            return object;
        }
        catch (IOException | ClassNotFoundException e) {
            exceptionConsumer.accept(e);
            return null;
        }
    }

    public static final Consumer<byte[]> EMPTY_BYTES_CONSUMER = bytes -> {};

    public static final Consumer<Object> EMPTY_OBJECT_CONSUMER = object -> {};

    public static final Consumer<Exception> EMPTY_EXCEPTION_CONSUMER = e -> {};
}
