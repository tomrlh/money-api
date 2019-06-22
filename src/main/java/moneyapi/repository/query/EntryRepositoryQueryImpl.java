package moneyapi.repository.query;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import moneyapi.model.Entry;
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


	private Predicate[] createRestrictions(EntryFilter entryFilter, CriteriaBuilder builder, Root<Entry> root) {
		// TODO Auto-generated method stub
		return null;
	}
}
