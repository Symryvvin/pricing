package shop.discard.rest.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.discard.pricing.service.CardCollectionService;

import java.util.Collection;
import java.util.Collections;

@RestController()
@RequestMapping("/search")
public class SearchController {

	private static final int MINIMUM_NUMBER_OF_CHARS = 3;

	@Autowired
	CardCollectionService cardCollectionService;

	@GetMapping(value = "/autocomplete/{lang}")
	public Collection<String> autocompleteResult(
			@PathVariable(value = "lang") String langCode,
			@RequestParam(value = "name") String partOfName
	) {
		try {
			if (checkMinNumberOfCharacters(partOfName)) {
				return cardCollectionService.searchByPartOfName(partOfName, langCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	private boolean checkMinNumberOfCharacters(String partOfName) {
		return partOfName.length() >= MINIMUM_NUMBER_OF_CHARS;
	}
}
