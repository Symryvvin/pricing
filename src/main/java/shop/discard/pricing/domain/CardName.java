package shop.discard.pricing.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class CardName implements Comparable<CardName> {

	private String name;

	private CardName(String name) {
		this.name = name;
	}

	public static CardName from(String name) {
		if (StringUtils.isEmpty(name)) {
			throw new IllegalArgumentException("Card name be empty");
		}
		return new CardName(name);
	}

	public String getName() {
		return name;
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
