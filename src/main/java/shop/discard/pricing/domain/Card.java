package shop.discard.pricing.domain;

import java.util.UUID;

public class Card {
	private UUID guid;
	private String name;
	private String printCode;
	private String language;

	private Card(String guid, String name, String printCode, String language) {
		this.guid = UUID.fromString(guid);
		this.name = name;
		this.printCode = printCode;
		this.language = language;
	}

	public static Card from(String id, String name, String printCode, String language) {
		return new Card(id, name, printCode, language);
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

	@Override
	public String toString() {
		return "Card{" +
				"guid=" + guid +
				", name='" + name + '\'' +
				", printCode='" + printCode + '\'' +
				", language='" + language + '\'' +
				'}';
	}
}
