package shop.discard.pricing.domain;

import org.apache.commons.lang3.StringUtils;
import shop.discard.pricing.domain.lang.Language;

import java.util.Objects;

public class CardName implements Comparable<CardName> {

	private String name;
	private Language language;

	private CardName(String name, Language language) {
		this.name = name;
		this.language = language;
	}

	public static CardName from(String name, Language language) {
		if (StringUtils.isEmpty(name) || language == null) {
			throw new IllegalArgumentException("Card name or language cannot be null");
		}
		return new CardName(name, language);
	}

	public String getName() {
		return name;
	}

	public Language getLanguage() {
		return language;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CardName cardName = (CardName) o;
		return Objects.equals(name, cardName.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public int compareTo(CardName o) {
		int byLength = Integer.compare(name.length(), o.name.length());
		int byName = name.compareTo(o.name);
		if (byLength == 0) {
			return byName;
		}
		return byLength;
	}
}
