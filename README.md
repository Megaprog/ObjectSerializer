ObjectSerializer
================

For easy serialization/deserialization Java objects to/from byte array and pack byte arrays to/from Strings or char array.

## How to get it?

You can download the latest build at:
    https://github.com/Megaprog/ObjectSerializer/releases

Or use it as a maven dependency:

```xml
<dependency>
    <groupId>org.jmmo</groupId>
    <artifactId>object-serializer</artifactId>
    <version>1.0</version>
</dependency>
```

## How to use it?

Serialize some object to byte array
```java
Optional<byte[]> bytesOpt = ObjectSerializer.serialize(someObject);
```

Deserialize some object from byte array
```java
Optional<SomeClass> someObjectOpt = ObjectSerializer.deserialize(SomeClass.class, bytes);
```

Pack bytes array from String
```java
String string = CharsBytesConverter.toString(bytes);
```

Unpack bytes array from String
```java
byte[] bytes = CharsBytesConverter.fromString(string);
```
