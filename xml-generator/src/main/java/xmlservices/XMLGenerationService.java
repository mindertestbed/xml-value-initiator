package xmlservices;

import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import xmlmodel.books.*;
public class XMLGenerationService{

	public byte[] generateXML(byte[] input){
		ObjectFactory objectFactory = new ObjectFactory();
		
		BookForm bookform = objectFactory.createBookForm();
		bookform.setId("bk001");
		bookform.setAuthor("George Orwell");
		bookform.setTitle("1984");
		bookform.setGenre("Sci-Fi");
		bookform.setPubDate(null);
		bookform.setReview("Very Good!");
		
		BookForm bookform2 = objectFactory.createBookForm();
		bookform2.setId("bk002");
		bookform2.setAuthor("Aldous Huxley");
		bookform2.setTitle("Brave New World");
		bookform2.setGenre("Sci-Fi");
		bookform2.setPubDate(null);
		bookform2.setReview("Very Good!");
		
		BooksForm booksForm = objectFactory.createBooksForm();
		booksForm.getBook().add(bookform);
		booksForm.getBook().add(bookform2);
		
		ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("xmlmodel.books");
			JAXBElement<BooksForm> jaxbElement = objectFactory.createBooks(booksForm);
			
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output",Boolean.TRUE);
			//marshaller.marshal(jaxbElement, System.out);
			marshaller.marshal(jaxbElement, byteArrayOutputStream);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return byteArrayOutputStream.toByteArray();

	}
	

}
