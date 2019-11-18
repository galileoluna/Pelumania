package Doppler.Pelumania;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dto.MailDTO;

class MailDTOtest {

	@Test
	void test() {
		MailDTO mail=new MailDTO(0, 0, null);
		
		
		assertEquals(mail.getFecha(),null);
		assertEquals(mail.getIdCita(),0);
		assertEquals(mail.getIdMail(),0);
		
	}

}
