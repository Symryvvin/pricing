package shop.discard.rest.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Collection<String>> autocomplete(
			@PathVariable(value = "lang") String langCode,
			@RequestParam(value = "name") String partOfName
	) {
		try {
			if (checkMinNumberOfCharacters(partOfName)) {
				return new ResponseEntity<>(
						cardCollectionService.searchByPartOfName(partOfName, langCode),
						HttpStatus.OK
				);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(
					Collections.singleton(e.toString()),
					HttpStatus.OK
			);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	private boolean checkMinNumberOfCharacters(String partOfName) {
		return partOfName.length() >= MINIMUM_NUMBER_OF_CHARS;
	}
}
