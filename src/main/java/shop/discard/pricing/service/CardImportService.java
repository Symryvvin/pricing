package shop.discard.pricing.service;

import shop.discard.pricing.domain.Card;
import shop.discard.pricing.domain.CardRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public class CardImportService {

	private CardImportSource<InputStream> source;
	private CardParser<InputStream> parser;
	private CardRepository repository;

	public CardImportService(
			CardImportSource<InputStream> source,
			CardParser<InputStream> parser,
			CardRepository repository
	) {
		this.source = source;
		this.parser = parser;
		this.repository = repository;
	}

	public void importCards() throws CardImportException {
		try (InputStream json = source.getSource()) {
			Collection<Card> cards = parser.parse(json);
			save(cards);
		} catch (IOException e) {
			throw new CardImportException("Error during import cards", e);
		}
	}

	private void save(Collection<Card> cards) {
		for (Card card : cards) {
			repository.save(card);
		}
	}

}
