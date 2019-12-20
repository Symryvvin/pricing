package shop.discard.rest.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.discard.pricing.service.CardCollectionService;

import java.util.Collection;

@RestController()
@RequestMapping("/search")
public class SearchController {

	@Autowired
	CardCollectionService cardCollectionService;

	@GetMapping(value = "/autocomplete")
	public Collection<CardPresenter> autocompleteResult(
			@RequestParam(value = "name") String partOfName
	) {
		return cardCollectionService.searchByPartOfName(partOfName);
	}
}
