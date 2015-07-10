package org.anon.fluentInterface;

public class SimpleMailer {
	public void from(final String address) { /*... */ }
	public void to(final String address) { /*... */ }
	public void subject(final String line) { /*... */ }
	public void body(final String message) { /*... */ }
	public void send() { System.out.println("sending..."); }
	
	public static void main(String [] args) {
		
		//Configure and send out an email
		SimpleMailer mailer = new SimpleMailer();
		mailer.from("build@agiledeveloper.com");
		mailer.to("venkats@agiledeveloper.com");
		mailer.subject("build notification");
		mailer.body("...your code sucks...");
		mailer.send();
		//It's noisy. We had to repeat mailer so many times
		//At the end of call, what do we do with mailer instance, should we reuse it, or is it disposable
	}
}
