package shop.discard.pricing.infrastructure.persistence;

import shop.discard.pricing.domain.Card;
import shop.discard.pricing.domain.lang.Language;

import java.util.Arrays;

public class CardFilter {
	private String name;
	private Language language;

	public CardFilter(String name, Language language) {
		this.name = name;
		this.language = language;
	}

	public static CardFilter filterBy(String name, Language language) {
		return new CardFilter(name, language);
	}

	public boolean filter(Card card) {
		String[] splitName = card.getName().split(" ");
		boolean byName = containsIgnoreCase(splitName, name);
		boolean byLanguage = card.getLanguage() == language;
		return byName && byLanguage;
	}

	private boolean containsIgnoreCase(String[] strings, String subString) {
		return Arrays.stream(strings)
				.anyMatch(s -> s.toLowerCase().startsWith(subString.toLowerCase()));
	}

}
