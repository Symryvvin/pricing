package shop.discard.pricing.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import shop.discard.pricing.domain.Card;
import shop.discard.pricing.domain.CardRepository;
import shop.discard.rest.search.CardPresenter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CardCollectionService implements InitializingBean {

	private CardImportSource<InputStream> source;
	private CardParser<InputStream> parser;
	private CardRepository repository;

	@Autowired
	public CardCollectionService(
			@Qualifier("system") CardImportSource<InputStream> source,
			CardParser<InputStream> parser,
			@Qualifier("memory") CardRepository repository
	) {
		this.source = source;
		this.parser = parser;
		this.repository = repository;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		importCards();
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

	public Collection<CardPresenter> searchByPartOfName(String partOfName) {
		return repository.findByPartOfName(partOfName).stream()
				.map(CardPresenter::from)
				.collect(Collectors.toList());
	}
}
