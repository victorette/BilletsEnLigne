package ca.ulaval.ift6003.tests.unit.infrastructure.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Consts;
import ca.ulaval.ift6003.infrastructure.utils.DateUtility;

public class DateUtilityTest {

	@Before
	public void setUp() {
	}

	@Test
	public void siDateEstDemandeeAlorsValeurApproprieeEstRetournee() {
		DateFormat dateFormat = new SimpleDateFormat(Consts.DATE_FORMAT);
		Date date = DateUtility.getCurrentDateTime();
		String result = dateFormat.format(date);
		
		assertEquals(result, DateUtility.getCurrentDateTimeString());
	}
	
	@Test
	public void siDateAVenirEstDansLeFuturAlorsVerificationRetourneVrai() {
		assertTrue(DateUtility.dateEstAVenir(DateUtility.ajouterJours(DateUtility.getCurrentDateTime(), 5)));
	}

}
