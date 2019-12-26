package shop.discard.pricing.domain.lang;

import org.apache.commons.lang3.StringUtils;

public class NotSupportedLanguageException extends Exception {

	public NotSupportedLanguageException(String languageCode) {
		super(message(languageCode));
	}

	private static String message(String languageCode) {
		if (StringUtils.isEmpty(languageCode)) {
			return "Try to parse Language by code, but code value is empty";
		} else {
			return String.format("Language code [%s] is not support", languageCode);
		}
	}

}
