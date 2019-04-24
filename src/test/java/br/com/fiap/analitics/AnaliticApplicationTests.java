package br.com.fiap.analitics;

import br.com.fiap.analitics.Controller.TransactionController;
import br.com.fiap.analitics.Domain.NewTransaction;
import br.com.fiap.analitics.Repository.AllTransactionDao;
import br.com.fiap.analitics.Repository.NewTransactionDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import java.util.ArrayList;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
public class AnaliticApplicationTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private AllTransactionDao repositoryAllTransaction;
	private NewTransactionDao repositoryNewTransaction;

	@Test
	public void saveTransaction() throws Exception {
		repositoryNewTransaction = mock(NewTransactionDao.class);
		NewTransaction newDao = returnNewTransaction();

		when(this.repositoryNewTransaction.save(newDao)).thenReturn(HttpStatus.CREATED);
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json",
				java.nio.charset.Charset.forName("UTF-8"));

		MockHttpServletRequestBuilder request = post("/transactions");
		request.content(new ObjectMapper().writeValueAsString(newDao));
		request.accept(MEDIA_TYPE_JSON_UTF8);
		request.contentType(MEDIA_TYPE_JSON_UTF8);

		mvc.perform(request).andExpect(status().isCreated());
	}

	@Test
	public void saveEmptyTransaction() throws Exception {
		repositoryNewTransaction = mock(NewTransactionDao.class);
		NewTransaction newDao = null;

		when(this.repositoryNewTransaction.save(newDao)).thenReturn(HttpStatus.NO_CONTENT);
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json",
				java.nio.charset.Charset.forName("UTF-8"));

		MockHttpServletRequestBuilder request = post("/transactions");
		request.content(new ObjectMapper().writeValueAsString(newDao));
		request.accept(MEDIA_TYPE_JSON_UTF8);
		request.contentType(MEDIA_TYPE_JSON_UTF8);

		mvc.perform(request).andExpect(status().is(400));
	}

	@Test(expected=NullPointerException.class)
	public void testEmptyCase() throws Exception {
		ArrayList<NewTransaction> allTransaction = new ArrayList<>();
		allTransaction = this.repositoryNewTransaction.getList();

		when(this.repositoryAllTransaction.resume(allTransaction)).thenReturn(null);
		mvc.perform(get("/statistics/")
				.accept(MediaType.APPLICATION_JSON));
	}

	/**
	 * Método que retorna um objeto do tipo NewTransaction
	 * @return NewTransaction
	 */
	public NewTransaction returnNewTransaction() {
		NewTransaction transaction = new NewTransaction();

		transaction.setTimestamp(System.currentTimeMillis());
		transaction.setAmount(850.35);
		transaction.setData(System.currentTimeMillis());

		return transaction;
	}
}
