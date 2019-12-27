package shop.discard.pricing.infrastructure.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import shop.discard.pricing.domain.CardName;
import shop.discard.pricing.domain.CardRepository;
import shop.discard.pricing.domain.lang.Language;
import shop.discard.pricing.domain.lang.NotSupportedLanguageException;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@DependsOn("collectionService")
public class InMemoryCardNamesStore {

	private static final int MAX_SEARCH_RESULT_COUNT = 15;

	private Map<Language, Collection<CardName>> cardNameCollection;

	private CardRepository repository;

	@Autowired
	public InMemoryCardNamesStore(@Qualifier("memory") CardRepository repository) {
		this.repository = repository;
	}

	@PostConstruct
	private void postConstruct() {
		cardNameCollection = new EnumMap<>(Language.class);
		Arrays.stream(Language.values())
				.forEach(lang ->
						cardNameCollection.put(lang, repository.getUniqueCardNames(lang))
				);
	}

	public Collection<String> findByPartOfName(String findString, String languageCode) throws NotSupportedLanguageException {
		Language language = Language.fromCode(languageCode);
		return cardNameCollection.get(language).stream()
				.filter(cn -> filter(cn, findString))
				.sorted()
				.map(CardName::getName)
				.limit(MAX_SEARCH_RESULT_COUNT)
				.collect(Collectors.toList());
	}

	private boolean filter(CardName cardName, String findString) {
		return Arrays.stream(cardName.getName().split(" "))
				.anyMatch(s -> s.toLowerCase().startsWith(findString.toLowerCase()));
	}

}
