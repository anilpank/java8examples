package org.anon.fluentInterface;

import java.util.function.Consumer;

public class CoolMailer {
	private CoolMailer() {		
	}
	
	public CoolMailer from(final String address) { /*... */; return this; }
	public CoolMailer to(final String address) { /*... */; return this; }
	public CoolMailer subject(final String line) { /*... */; return this; }
	public CoolMailer body(final String message) { /*... */; return this; }
	
	/**
	 * Rather than creating an instance, users will now invoke send() and pass
	 * a block of code. Object's scope is confined within the block, once we return from send method, the reference is gone
	 * 
	 * @param block
	 */
	public static void send(final Consumer<CoolMailer> block) {
		final CoolMailer mailer = new CoolMailer();
		block.accept(mailer);
		System.out.println("sending...");
	}
	
	
	public static void main (String[] args) {
		CoolMailer.send(mailer ->
		mailer.from("anil@agiledeveloper.com")
		.to("venkat@agiledeveloper.com")
		.subject("build notification")
		.body("much better"));
		//The instance's scope is fairly easy to see: we get it, work with it, and return it. This is called Loan Pattern.
		
		//We can use this pattern to configure mailers, specify database connection params, or anywhere where we need to build
		//a series of states on an instance.
		
	}
	
}
