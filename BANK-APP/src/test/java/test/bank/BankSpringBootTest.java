package test.bank;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.test.context.junit4.SpringRunner;

import com.bank.BankSpringBoot;

@RunWith(SpringRunner.class)
public class BankSpringBootTest {

	@Test
	public void test() {
	
		System.out.println("Testing context loading");
		try{
			SpringApplication.run(BankSpringBoot.class);
		}catch(Exception e){
			System.out.println("Error on start up: ");
			e.printStackTrace();
		}
	}

}
