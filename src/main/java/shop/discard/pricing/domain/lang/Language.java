package shop.discard.pricing.domain.lang;

import java.util.Arrays;

public enum Language {
	CN("Chinese Simplified", "cs"),
	CT("Chinese Traditional", "ct"),
	DE("German", "de"),
	EN("English", "en"),
	FR("French", "fr"),
	IT("Italian", "it"),
	JA("Japanese", "ja"),
	KO("Korean", "ko"),
	PT("Portuguese (Brazil)", "pt"),
	RU("Russian", "ru"),
	ES("Spanish", "es"),

	// support scryfall language codes,
	ZHS("Chinese Simplified", "zhs"),
	ZHT("Chinese Traditional", "zht"),

	// support of unique cards
	AR("Arabic", "ar"),
	GRC("Greek, Ancient (to 1453)", "grc"),
	LA("Latin", "la"),
	HE("Hebrew", "he"),
	SA("Arabic (Saudi Arabia)", "sa"),
	PX("Phyrexian", "px");

	private String name;
	private String code;

	Language(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public static Language fromCode(String code) throws NotSupportedLanguageException {
		return Arrays.stream(Language.values())
				.filter(l -> l.code.equalsIgnoreCase(code))
				.findFirst()
				.orElseThrow(() -> new NotSupportedLanguageException(code));
	}

	public String getName() {
		return name;
	}

}