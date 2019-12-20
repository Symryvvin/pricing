package shop.discard.rest.search;

import shop.discard.pricing.domain.Card;

public class CardPresenter {
	private String name;
	private String expansion;

	private CardPresenter(String name, String expansion) {
		this.name = name;
		this.expansion = expansion;
	}

	public static CardPresenter from(Card card) {
		return new CardPresenter(card.getName(), card.getPrintCode());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpansion() {
		return expansion;
	}

	public void setExpansion(String expansion) {
		this.expansion = expansion;
	}
}
