package org.anon.fluentInterface;

public class SimpleMailBuilder {
	public SimpleMailBuilder from(final String address) { /*... */; return this; }
	public SimpleMailBuilder to(final String address) { /*... */; return this; }
	public SimpleMailBuilder subject(final String line) { /*... */; return this; }
	public SimpleMailBuilder body(final String message) { /*... */; return this; }
	public void send() { System.out.println("sending..."); }
	
	public static void main (String [] args) {
		new SimpleMailBuilder()
		.from("build@gooddeveloper.com")
		.to("daband@agiledeveloper.com")
		.subject("build notification")
		.body("it sucks less")
		.send();
		//The design does not prevent someone from storing the reference from new and chaining the reference. We'd still have to deal with 
		//object lifetime.
		
	}
}
