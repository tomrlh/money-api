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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import moneyapi.model.Entry;
import moneyapi.model.Entry_;
import moneyapi.repository.filter.EntryFilter;

public class EntryRepositoryQueryImpl implements EntryRepositoryQuery {
	
	@PersistenceContext
	private EntityManager em;
	

	@Override
	public Page<Entry> filter(EntryFilter entryFilter, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Entry> criteria = builder.createQuery(Entry.class);
		Root<Entry> root = criteria.from(Entry.class); // to add filters
		
		Predicate[] predicates = createRestrictions(entryFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Entry> query = em.createQuery(criteria);
		addPaginationRules(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(entryFilter));
	}


	private Predicate[] createRestrictions(EntryFilter entryFilter, CriteriaBuilder builder,
			Root<Entry> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(entryFilter.getDescription())) {
			predicates.add(builder.like(
					builder.lower(root.get(Entry_.DESCRIPTION)), "%" + entryFilter.getDescription().toLowerCase() + "%"));
		}
		
		if (entryFilter.getDueDateSince() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get(Entry_.DUE_DATE), entryFilter.getDueDateSince()));
		}
		
		if (entryFilter.getDueDateUntil() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Entry_.DUE_DATE), entryFilter.getDueDateUntil()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	
	
	private void addPaginationRules(TypedQuery<Entry> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	
	
	private Long total(EntryFilter entryFilter) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Entry> root = criteria.from(Entry.class);
		
		Predicate[] predicates = createRestrictions(entryFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return em.createQuery(criteria).getSingleResult();
	}
}
