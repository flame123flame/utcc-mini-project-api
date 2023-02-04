package UTCC.project.common.service;

import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

import UTCC.framework.utils.ConvertDateUtils;

public class GenerateRandomString {

	public static String generate() {
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();	 
	    String YYYYMMDDHHMMSS = ConvertDateUtils.formatDateToString(new Date(), ConvertDateUtils.YYYYMMDDHHMMSS, ConvertDateUtils.LOCAL_EN);
	    generatedString = YYYYMMDDHHMMSS.concat(generatedString);
	    return generatedString;
	}
}
