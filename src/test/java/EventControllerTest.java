package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.Mockito;

import hello.Event;
import hello.EventRepository;

/* import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders; */

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventControllerTest {
/* 	@Autowired
	private MockMvc mockMvc; */

	@MockBean
	private EventRepository eventRepository; 
	
	@Test
	public void findByName() {
		//String expected = "[{\"id\":4,\"name\":\"Ozzfest\",\"tour\":\"Ozzfest\",\"year\":2002,\"venue\":8,\"fest\":true,\"date\":null}]";
		Iterable<Event> expected = eventRepository.findByName("ozzfest");
		Mockito.when(
				eventRepository.findByName("ozzfest")).thenReturn(expected);
				
	}

}