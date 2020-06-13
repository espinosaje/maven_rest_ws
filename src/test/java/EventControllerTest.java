package hello;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.Mockito;

import hello.Event;
import hello.EventRepository;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = EventController.class, secure = false)
//@SpringBootTest
public class EventControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EventRepository eventRepository; 
	
	@MockBean
	private Iterable<Event> expected;
	
	/* 
	// do this instead of autowiring
	@Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new EventController()).build();

    } */
	
	// This on works fine
	 @Test
	public void findByNameTest() {
		//String expected = "[{\"id\":4,\"name\":\"Ozzfest\",\"tour\":\"Ozzfest\",\"year\":2002,\"venue\":8,\"fest\":true,\"date\":null}]";
		Iterable<Event> expected = eventRepository.findByName("ozzfest");
		Mockito.when(
				eventRepository.findByName("ozzfest")).thenReturn(expected);		
				
	} 
	
	// This on works fine
	@Test
	public void somkeTest() throws Exception {
		
		assertThat(eventRepository).isNotNull();
	}
	
	// This on works fine
	@Test
	public void eventShouldReturnMessageFromService() throws Exception {
		
        when(eventRepository.findByName("test")).thenReturn(expected); //last param need to be Iterable<hello.Event>
        //this.mockMvc.perform(get("/get")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("Hello Mock")));
    } 
	
	/*
	 * // In Process
	 * 
	 * @Test String expected =
	 * "[{\"id\":4,\"name\":\"Ozzfest\",\"tour\":\"Ozzfest\",\"year\":2002,\"venue\":8,\"fest\":true,\"date\":null}]";
	 * RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
	 * "/event/get?name=ozzfest").accept(MediaType.APPLICATION_JSON); MvcResult
	 * result = mockMvc.perform(requestBuilder).andReturn();
	 * System.out.println("=== findByNameSvcTest_Result: "+result.getResponse().
	 * getContentAsString());
	 * 
	 * //JSONAssert.assertEquals(expected,
	 * result.getResponse().getContentAsString(), false);
	 * 
	 * }
	 */
}