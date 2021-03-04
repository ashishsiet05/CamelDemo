/**
 * 
 */
package com.camel.demo.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @author rajup
 *
 */

@Component
public class EmployeeOnboardingRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		from("file:D:/demo/input?fileName=employee.txt")
			.split(bodyAs(String.class).tokenize(System.lineSeparator()))
				.to("activemq:queue:EMPLOYEE");
		
		from("activemq:queue:EMPLOYEE")
			.filter(bodyAs(String.class).not().startsWith("DUMMY"))
				.to("activemq:queue:EMPLOYEE_FILTERED");
		
		from("activemq:queue:EMPLOYEE_FILTERED")
			.transform(bodyAs(String.class).append(System.lineSeparator()))
			.choice()
				.when(bodyAs(String.class).startsWith("JP"))
					.to("file:D:/demo/output/jp?fileName=employee-${date:now:yyyyMMdd}.txt&fileExist=Append")
				.otherwise()
					.to("file:D:/demo/output/global?fileName=employee-${date:now:yyyyMMdd}.txt&fileExist=Append");
			
			
				
	}

}
