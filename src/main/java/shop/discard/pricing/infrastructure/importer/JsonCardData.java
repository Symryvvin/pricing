package shop.discard.pricing.infrastructure.importer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = JsonCardDeserializer.class)
public class JsonCardData {
	private String id;
	private String printedName;
	private String oracleName;
	private String printCode;
	private String lang;
	private String releaseDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrintedName() {
		return printedName;
	}

	public void setPrintedName(String printedName) {
		this.printedName = printedName;
	}

	public String getOracleName() {
		return oracleName;
	}

	public void setOracleName(String oracleName) {
		this.oracleName = oracleName;
	}

	public String getPrintCode() {
		return printCode;
	}

	public void setPrintCode(String printCode) {
		this.printCode = printCode;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
}
