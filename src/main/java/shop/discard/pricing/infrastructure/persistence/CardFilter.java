package shop.discard.pricing.infrastructure.persistence;

import shop.discard.pricing.domain.Card;

public class CardFilter {
	private String partOfName;
	private String langCode;

	public CardFilter(String partOfName, String langCode) {
		this.partOfName = partOfName;
		this.langCode = langCode;
	}

	public static CardFilter filterBy(String partOfName, String langCode) {
		return new CardFilter(partOfName, langCode);
	}

	public boolean filter(Card card) {
		boolean byName = containsIgnoreCase(card.getName(), partOfName);
		boolean byLanguage = card.getLanguage().equalsIgnoreCase(langCode);
		return byName && byLanguage;
	}

	private boolean containsIgnoreCase(String str, String subString) {
		return str.toLowerCase().contains(subString.toLowerCase());
	}

}
