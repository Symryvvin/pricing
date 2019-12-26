package shop.discard.pricing.infrastructure.persistence;

import org.springframework.stereotype.Repository;
import shop.discard.pricing.domain.Card;
import shop.discard.pricing.domain.CardName;
import shop.discard.pricing.domain.CardRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Repository(value = "memory")
public class InMemoryCardRepository implements CardRepository {

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
	public long save(Card card) {
		long id = sequence++;
		store.put(id, card);
		return id;
	}

	@Override
	public void update(long id, Card card) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<CardName> getUniqueCardNames() {
		return store.values()
				.stream()
				.map(card -> CardName.from(card.getName(), card.getLanguage()))
				.distinct()
				.collect(Collectors.toList());
	}
}