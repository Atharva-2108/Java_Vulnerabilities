//Code with vulnerability
import java.io.*;

public class VulnerableDeserialization {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        byte[] serializedData = receiveDataFromUntrustedSource();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(serializedData));
        Object deserializedObject = ois.readObject();
        ois.close();

        // Use the deserialized object
        System.out.println("Deserialized object: " + deserializedObject);
    }

   private static byte[] receiveDataFromUntrustedSource() {
        // Simulate receiving serialized data from an untrusted source
        return new byte[]{ /* serialized data */ };
    }
}

// Secure code
import com.fasterxml.jackson.databind.ObjectMapper;

public class SecureDeserializationWithJackson {
    public static void main(String[] args) throws IOException {
        byte[] serializedData = receiveDataFromTrustedSource();
        ObjectMapper mapper = new ObjectMapper();
        MyObject deserializedObject = mapper.readValue(serializedData, MyObject.class);

        // Use the deserialized object
        System.out.println("Deserialized object: " + deserializedObject);
    }

    private static byte[] receiveDataFromTrustedSource() {
        // Simulate receiving serialized data from a trusted source
        return new byte[]{ /* serialized data */ };
    }
}

class MyObject {
    // Class definition
}