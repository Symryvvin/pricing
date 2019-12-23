package shop.discard.pricing.infrastructure.persistence;

import org.springframework.stereotype.Repository;
import shop.discard.pricing.domain.Card;
import shop.discard.pricing.domain.CardRepository;
import shop.discard.pricing.domain.CardRepositoryException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Repository(value = "memory")
public class ImMemoryCardRepository implements CardRepository {

	private static final int MAX_SEARCH_RESULT_COUNT = 15;

	private Map<Long, Card> store = new HashMap<>();
	private long sequence = 1;

	@Override
	public Card findById(long id) {
		return store.get(id);
	}

	@Override
	public Card findByName(String name) {
		return store.values()
				.stream()
				.filter(c -> c.getName().equalsIgnoreCase(name))
				.findFirst()
				.orElse(null);
	}

	@Override
	public Collection<String> findNameByPartOfName(String partOfName, String langCode) throws CardRepositoryException {
		try {
			CardFilter filter = CardFilter.filterBy(partOfName, langCode);
			return store.values()
					.stream()
					.filter(filter::filter)
					.map(Card::getName)
					.distinct()
					.sorted()
					.limit(MAX_SEARCH_RESULT_COUNT)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new CardRepositoryException("Error during search by name", e);
		}
	}

	@Override
	public long save(Card card) {
		long id = sequence++;
		store.put(id, card);
		return id;
	}

	@Override
	public void update(long id, Card card) {

	}
}
