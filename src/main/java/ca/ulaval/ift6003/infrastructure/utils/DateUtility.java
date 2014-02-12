package ca.ulaval.ift6003.infrastructure.utils;

import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Consts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// bean or not ??
public class DateUtility {

	public static String getCurrentDateTimeString() {
		DateFormat dateFormat = new SimpleDateFormat(Consts.DATE_FORMAT);
		Date date = getCurrentDateTime();
		return dateFormat.format(date);
	}

	public static Date getCurrentDateTime() {
		return new Date();
	}

	public static Boolean dateEstAVenir(Date date) {
		Date maintenant = DateUtility.getCurrentDateTime();
		return date.after(maintenant);
	}

	public static Date ajouterJours(Date date, int nbJours) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, nbJours);
		return cal.getTime();
	}

}