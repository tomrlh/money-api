package moneyapi.repository.query;

import java.util.List;

import moneyapi.model.Entry;
import moneyapi.repository.filter.EntryFilter;

public interface EntryRepositoryQuery {

	public List<Entry> filter(EntryFilter entryFilter);
}
