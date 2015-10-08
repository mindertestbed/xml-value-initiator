package xmlservices;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import serialization.SerializationUtils;
import xmlmodel.books.BookForm;
import xmlmodel.books.BooksForm;

public class XMLValueInitiatorService {
	public byte[] getSampleBooksData(int nrOfBooks) throws IOException {
		BooksForm booksForm = new BooksForm();

		for (int i = 0; i < nrOfBooks; i++) {
			BookForm bookform = new BookForm();
			bookform.setId("bk_" + i);
			bookform.setAuthor("George Orwell");
			bookform.setTitle("1984");
			bookform.setGenre("Sci-Fi");
			bookform.setPubDate(new XMLGregorianCalendarImpl(GregorianCalendar.from(ZonedDateTime.now())));
			bookform.setReview("Very Good!");

			booksForm.getBook().add(bookform);
		}

		return SerializationUtils.serializeObject(booksForm);

	}

}
