package shop.discard.pricing.domain;

import java.util.Objects;

public class CardName implements Comparable<CardName> {

	private String printedName;
	private String oracleName;

	public CardName(String printedName, String oracleName) {
		this.printedName = printedName;
		this.oracleName = oracleName;
	}

	public String getPrintedName() {
		return printedName;
	}

	public String getOracleName() {
		return oracleName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CardName cardName = (CardName) o;
		return Objects.equals(printedName, cardName.printedName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(printedName);
	}

	@Override
	public String toString() {
		return "CardName{" +
				"printedName='" + printedName + '\'' +
				", oracleName='" + oracleName + '\'' +
				'}';
	}

	@Override
	public int compareTo(CardName o) {
		int byLength = Integer.compare(printedName.length(), o.printedName.length());
		int byName = printedName.compareTo(o.printedName);
		if (byLength == 0) {
			return byName;
		}
		return byLength;
	}
}
