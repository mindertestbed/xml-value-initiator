package xmlservices;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import serialization.SerializationUtils;
import xmlmodel.books.*;
public class XMLGenerationService{

	public byte[] generateXML(byte[] input){
		BooksForm inBooksForm=null;
		try {
		    inBooksForm = (BooksForm) SerializationUtils.deserializeObject(input);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		ObjectFactory objectFactory = new ObjectFactory();
		BooksForm booksForm = objectFactory.createBooksForm();
		for (BookForm inBookForm : inBooksForm.getBook()) {
			BookForm bookform = objectFactory.createBookForm();
			bookform.setId(inBookForm.getId());
			bookform.setAuthor(inBookForm.getAuthor());
			bookform.setTitle(inBookForm.getTitle());
			bookform.setGenre(inBookForm.getGenre());
			bookform.setPubDate(inBookForm.getPubDate());
			bookform.setReview(inBookForm.getReview());
			
			booksForm.getBook().add(bookform);
		}
		
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
