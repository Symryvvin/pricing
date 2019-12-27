package shop.discard.rest.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.discard.pricing.domain.lang.Language;
import shop.discard.pricing.infrastructure.persistence.InMemoryCardNamesStore;

@RestController()
@RequestMapping("/search")
public class SearchController {

	private static final int MINIMUM_NUMBER_OF_CHARS = 3;

	private final InMemoryCardNamesStore namesStore;

	@Autowired
	public SearchController(InMemoryCardNamesStore namesStore) {
		this.namesStore = namesStore;
	}

	@GetMapping(value = "/autocomplete/{lang}")
	public ResponseEntity autocomplete(
			@PathVariable(value = "lang") String langCode,
			@RequestParam(value = "name") String partOfName
	) {
		try {
			Language language = Language.fromCode(langCode);
			if (checkMinNumberOfCharacters(partOfName, language)) {
				return new ResponseEntity<>(
						namesStore.findByPartOfName(partOfName, language),
						HttpStatus.OK
				);
			}
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(e.toString());
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	private boolean checkMinNumberOfCharacters(String partOfName, Language language) {
		return partOfName.length() >= MINIMUM_NUMBER_OF_CHARS || (language.isHieroglyphic() && partOfName.length() >= 1);
	}
}
