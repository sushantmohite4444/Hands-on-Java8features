package deepPractice;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZonedDateTime;

public class Date_TimeAPI {

	public static void main(String[] args) {
		
		
		LocalDate localDate = LocalDate.now();
		localDate.minusDays(1);
		localDate.plusDays(1);
		System.out.println(localDate);
		
		LocalTime localTime = LocalTime.now();
		 System.out.println(localTime);
		
		
		 LocalDateTime localDateTime = LocalDateTime.now();
		 System.out.println(localDateTime);
		 
		 ZonedDateTime zonedDateTime =ZonedDateTime.now();
		 System.out.println(zonedDateTime);

		 
		 Instant instant = Instant.now();
		 
		 try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 Instant instant2 = Instant.now();
		 
		
		 
	
		 
		
		System.out.println( Duration.between(instant, instant2));
		System.out.println("In Second = "  + Duration.between( localDateTime.minusDays(1),localDateTime ));
		System.out.println("In Days = " + Period.between(localDate.minusDays(1),localDate ));
		 
	}

}
