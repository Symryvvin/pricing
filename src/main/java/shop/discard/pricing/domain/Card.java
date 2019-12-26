package shop.discard.pricing.domain;

import shop.discard.pricing.domain.lang.Language;
import shop.discard.pricing.domain.lang.NotSupportedLanguageException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

public class Card implements Comparable<Card> {
	private static final String DATE_PATTERN = "yyyy-MM-dd";

	private UUID guid;
	private String name;
	private String printCode;
	private Language language;
	private LocalDate releaseDate;

	public Card(UUID guid, String name, String printCode, Language language, LocalDate releaseDate) {
		this.guid = guid;
		this.name = name;
		this.printCode = printCode;
		this.language = language;
		this.releaseDate = releaseDate;
	}

	public static Card from(String id, String name, String printCode, String languageCode, String releaseDate)
			throws NotSupportedLanguageException {
		UUID guid = UUID.fromString(id);
		LocalDate parsedReleaseDate = LocalDate.parse(releaseDate, DateTimeFormatter.ofPattern(DATE_PATTERN));
		Language language = Language.fromCode(languageCode);
		return new Card(guid, name, printCode, language, parsedReleaseDate);
	}

	public UUID getGuid() {
		return guid;
	}

	public String getName() {
		return name;
	}

	public String getPrintCode() {
		return printCode;
	}

	public Language getLanguage() {
		return language;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	@Override
	public String toString() {
		return "Card{" +
				"guid=" + guid +
				", name='" + name + '\'' +
				", printCode='" + printCode + '\'' +
				", language='" + language + '\'' +
				", releaseDate=" + releaseDate +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Card card = (Card) o;
		return Objects.equals(guid, card.guid) &&
				Objects.equals(name, card.name) &&
				Objects.equals(printCode, card.printCode) &&
				language == card.language &&
				Objects.equals(releaseDate, card.releaseDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(guid, name, printCode, language, releaseDate);
	}

	@Override
	public int compareTo(Card o) {
		int byLength = Integer.compare(name.length(), o.name.length());
		int byName = name.compareTo(o.name);
		if (byLength == 0) {
			return byName;
		}
		return byLength;
	}

}
