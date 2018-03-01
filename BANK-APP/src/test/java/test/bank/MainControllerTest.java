package test.bank;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;

import com.bank.controller.MainController;
import com.bank.model.InMemory;
import com.bank.model.Person;
import com.bank.service.H2Service;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MainController.class,H2Service.class,InMemory.class})
@WebAppConfiguration
public class MainControllerTest {
	@Autowired
	MainController main;
	@Autowired
	H2Service h2;
	ModelMap map;
    	
  	@Test
	    public void testListMovements() throws Exception {
  		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.main).build();
  		H2Service h2Mock = mock(H2Service.class);
  		h2.initDB();
  		when(h2Mock.findPerson()).thenReturn(new Person(1, "rafael", "rafa@asds.com", "123456789874561",
  	            12345.0,"1234567") );
  		mockMvc.perform(get("/list-movements"))
  		.andExpect(status().isOk());
	    }	
   	
}
