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
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@DependsOn("collectionService")
public class InMemoryCardNamesStore {

	private static final int MAX_SEARCH_RESULT_COUNT = 15;
	private static final Pattern NOT_WORD_PATTERN = Pattern.compile("\\W", Pattern.UNICODE_CHARACTER_CLASS);

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
		Stream<CardName> nameStream = cardNameCollection.get(language).stream();
		if (NOT_WORD_PATTERN.matcher(findString).find()) {
			return findByPartOfName(findString, nameStream);
		} else {
			return findByStartOfWord(findString, nameStream);
		}
	}

	private Collection<String> findByPartOfName(String findString, Stream<CardName> nameStream) {
		return nameStream.filter(cn -> contains(cn, findString))
				.sorted()
				.map(CardName::getName)
				.limit(MAX_SEARCH_RESULT_COUNT)
				.collect(Collectors.toList());
	}

	private boolean contains(CardName cardName, String findString) {
		return cardName.getName().toLowerCase().contains(findString.toLowerCase());
	}

	private Collection<String> findByStartOfWord(String findString, Stream<CardName> nameStream) {
		return nameStream.filter(cn -> filter(cn, findString))
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
