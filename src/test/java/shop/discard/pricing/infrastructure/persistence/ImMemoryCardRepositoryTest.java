package shop.discard.pricing.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import shop.discard.pricing.domain.CardRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class ImMemoryCardRepositoryTest {

	@Autowired
	@Qualifier("memory")
	CardRepository repository;

	@Test
	void findById() {
	}

	@Test
	void findByName() {
	}

	@Test
	void findNameByPartOfName() throws Exception {
		String partOfName = "лес";
		System.out.println(repository.findNameByPartOfName(partOfName, "ru"));
		List<String> result = new ArrayList<>(repository.findNameByPartOfName(partOfName, "ru"));
		Collections.sort(result);
		System.out.println(result);
	}

	@Test
	void save() {
	}

	@Test
	void update() {
	}
}