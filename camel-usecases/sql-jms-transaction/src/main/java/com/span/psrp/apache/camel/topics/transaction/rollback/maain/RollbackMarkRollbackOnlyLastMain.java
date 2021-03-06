package com.span.psrp.apache.camel.topics.transaction.rollback.maain;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.span.psrp.apache.camel.topics.transaction.basic.AuditLog;

public class RollbackMarkRollbackOnlyLastMain {
	
   private static ProducerTemplate template;
	
	public static void main( String[] args ) throws Exception{
		
    	AbstractApplicationContext appContext = new ClassPathXmlApplicationContext("rollbackMarkRollbackOnlyLast-context.xml");
    	CamelContext camelContext = SpringCamelContext.springCamelContext(
				appContext, false);
    	template = camelContext.createProducerTemplate();
		System.out.println("Start camel context");
		AuditLog auditLog =new AuditLog();
		auditLog.setMessage("jagan");
	    template.sendBody("direct:route1", auditLog);
    	appContext.start();
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	System.out.println("Entered>>>>>");
    	appContext.stop();
    }
}
