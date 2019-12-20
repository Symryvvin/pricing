package shop.discard.pricing.infrastructure.persistence;

import shop.discard.pricing.domain.Card;

import java.util.Arrays;

public class CardFilter {
	private String name;
	private String langCode;

	public CardFilter(String name, String langCode) {
		this.name = name;
		this.langCode = langCode;
	}

	public static CardFilter filterBy(String name, String langCode) {
		return new CardFilter(name, langCode);
	}

	public boolean filter(Card card) {
		String[] splitName = card.getName().split(" ");
		boolean byName = containsIgnoreCase(splitName, name);
		boolean byLanguage = card.getLanguage().equalsIgnoreCase(langCode);
		return byName && byLanguage;
	}

	private boolean containsIgnoreCase(String[] strings, String subString) {
		return Arrays.stream(strings)
				.anyMatch(s -> s.toLowerCase().startsWith(subString.toLowerCase()));
	}

}
