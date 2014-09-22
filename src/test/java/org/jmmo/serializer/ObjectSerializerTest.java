package org.jmmo.serializer;

import org.junit.Test;

import java.io.Serializable;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class ObjectSerializerTest {

    @Test
    public void testSerializeFailed() throws Exception {
        ExampleNotSerializable exampleNotSerializable = new ExampleNotSerializable();
        assertEquals(Optional.<byte[]>empty(), ObjectSerializer.serialize(exampleNotSerializable));
    }

    @Test
    public void testSerializeAndDeserialize() throws Exception {
        ExampleSerializable example = new ExampleSerializable(1, "User");
        Object restoredExample = ObjectSerializer.serialize(example).flatMap(ObjectSerializer::deserialize).orElseThrow(RuntimeException::new);
        assertEquals(example, restoredExample);
    }

    private static class ExampleNotSerializable {
    }

    private static class ExampleSerializable implements Serializable {
        private final int id;
        private final String name;

        private ExampleSerializable(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ExampleSerializable example = (ExampleSerializable) o;

            if (id != example.id) return false;
            if (!name.equals(example.name)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = id;
            result = 31 * result + name.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "ExampleSerializable{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}