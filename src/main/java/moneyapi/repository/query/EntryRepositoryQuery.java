package moneyapi.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import moneyapi.model.Entry;
import moneyapi.repository.filter.EntryFilter;

public interface EntryRepositoryQuery {

	public Page<Entry> filter(EntryFilter entryFilter, Pageable pageable);
}
