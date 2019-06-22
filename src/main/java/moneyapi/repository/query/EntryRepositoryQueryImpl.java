package moneyapi.repository.query;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import moneyapi.model.Entry;
import moneyapi.model.Entry_;
import moneyapi.repository.filter.EntryFilter;

public class EntryRepositoryQueryImpl implements EntryRepositoryQuery {
	
	@PersistenceContext
	private EntityManager em;
	

	@Override
	public List<Entry> filter(EntryFilter entryFilter) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Entry> criteria = builder.createQuery(Entry.class);
		Root<Entry> root = criteria.from(Entry.class); // to add filters
		
		Predicate[] predicates = createRestrictions(entryFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Entry> query = em.createQuery(criteria);
		
		return query.getResultList();
	}


	private Predicate[] createRestrictions(EntryFilter lancamentoFilter, CriteriaBuilder builder,
			Root<Entry> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(lancamentoFilter.getDescription())) {
			predicates.add(builder.like(
					builder.lower(root.get(Entry_.DESCRIPTION)), "%" + lancamentoFilter.getDescription().toLowerCase() + "%"));
		}
		
		if (lancamentoFilter.getDueDateSince() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get(Entry_.DUE_DATE), lancamentoFilter.getDueDateSince()));
		}
		
		if (lancamentoFilter.getDueDateUntil() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Entry_.DUE_DATE), lancamentoFilter.getDueDateUntil()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
