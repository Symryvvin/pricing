package shop.discard.pricing.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class CardName implements Comparable<CardName> {

	private String name;
	private String languageCode;

	private CardName(String name, String languageCode) {
		this.name = name;
		this.languageCode = languageCode;
	}

	public static CardName from(String name, String languageCode) {
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(languageCode)) {
			throw new IllegalArgumentException("cannot be null");
		}
		return new CardName(name, languageCode);
	}

	public String getName() {
		return name;
	}

	public String getLanguageCode() {
		return languageCode;
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
