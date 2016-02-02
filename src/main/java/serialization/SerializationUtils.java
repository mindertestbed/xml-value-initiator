package serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationUtils {
	public static byte[] serializeObject(Object object) throws IOException{
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
		
		objectOutputStream.writeObject(object);
		return byteArrayOutputStream.toByteArray();
	}
	
	public static Object deserializeObject(byte[] byteData) throws IOException, ClassNotFoundException{
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteData);
		ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

		return objectInputStream.readObject();
	}
}
