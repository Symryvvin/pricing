package shop.discard.pricing.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Card implements Comparable<Card> {
	private static final String DATE_PATTERN = "yyyy-MM-dd";

	private UUID guid;
	private String name;
	private String printCode;
	private String language;
	private LocalDate releaseDate;

	public Card(UUID guid, String name, String printCode, String language, LocalDate releaseDate) {
		this.guid = guid;
		this.name = name;
		this.printCode = printCode;
		this.language = language;
		this.releaseDate = releaseDate;
	}

	public static Card from(String id, String name, String printCode, String language, String releaseDate) {
		UUID guid = UUID.fromString(id);
		LocalDate parsedReleaseDate = LocalDate.parse(releaseDate, DateTimeFormatter.ofPattern(DATE_PATTERN));
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

	public String getLanguage() {
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
	public int compareTo(Card o) {
		int byLength = Integer.compare(name.length(), o.name.length());
		int byName = name.compareTo(o.name);
		if (byLength == 0) {
			return byName;
		}
		return byLength;
	}

}
