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
import java.util.stream.Collectors;

@Component
@DependsOn("collectionService")
public class InMemoryCardNamesStore {

	private static final int MAX_SEARCH_RESULT_COUNT = 15;

	private Collection<CardName> cardNameCollection;

	private CardRepository repository;

	@Autowired
	public InMemoryCardNamesStore(@Qualifier("memory") CardRepository repository) {
		this.repository = repository;
	}

	@PostConstruct
	private void postConstruct() {
		cardNameCollection = repository.getUniqueCardNames();
	}

	public Collection<String> findByPartOfName(String findString, String languageCode) throws NotSupportedLanguageException {
		Language language = Language.fromCode(languageCode);
		return cardNameCollection.stream()
				.filter(cn -> filter(cn, findString, language))
				.sorted()
				.map(CardName::getName)
				.limit(MAX_SEARCH_RESULT_COUNT)
				.collect(Collectors.toList());
	}

	private boolean filter(CardName cardName, String findString, Language language) {
		boolean byLanguage = cardName.getLanguage() == language;
		if (byLanguage) {
			return Arrays.stream(cardName.getName().split(" "))
					.anyMatch(s -> s.toLowerCase().startsWith(findString.toLowerCase()));
		}
		return false;
	}

}