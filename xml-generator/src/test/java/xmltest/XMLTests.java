package xmltest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import serialization.SerializationUtils;
import xmlmodel.books.BookForm;
import xmlmodel.books.BooksForm;
import xmlservices.XMLGenerationService;

public class XMLTests {
	private static BooksForm booksForm;
	
	@BeforeClass
	public static void getSampleBooksData() {
		booksForm = new BooksForm();
		
		BookForm bookform = new BookForm();
		bookform.setId("bk001");
		bookform.setAuthor("George Orwell");
		bookform.setTitle("1984");
		bookform.setGenre("Sci-Fi");
		bookform.setPubDate(null);
		bookform.setReview("Very Good!");
		
		BookForm bookform2 = new BookForm();
		bookform2.setId("bk002");
		bookform2.setAuthor("Aldous Huxley");
		bookform2.setTitle("Brave New World");
		bookform2.setGenre("Sci-Fi");
		bookform2.setPubDate(null);
		bookform2.setReview("Very Good!");
		
		booksForm.getBook().add(bookform);
		booksForm.getBook().add(bookform2);
		
	}
	
	@Test
	public void generateXML() {
		byte[] serializedObject=null;
		try {
			serializedObject = SerializationUtils.serializeObject(booksForm);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Bir varmış bir yokmuş, NW üzerinden send ve receive olmuş.=)
		
		XMLGenerationService xmlService = new XMLGenerationService();
		
		byte[] xmlAsByteArray = xmlService.generateXML(serializedObject);
	}
	

}
