package moneyapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Entry.class)
public abstract class Entry_ {

	public static volatile SingularAttribute<Entry, String> note;
	public static volatile SingularAttribute<Entry, Person> person;
	public static volatile SingularAttribute<Entry, LocalDate> dueDate;
	public static volatile SingularAttribute<Entry, String> description;
	public static volatile SingularAttribute<Entry, Long> id;
	public static volatile SingularAttribute<Entry, LocalDate> paymentDate;
	public static volatile SingularAttribute<Entry, EntryType> type;
	public static volatile SingularAttribute<Entry, Category> category;
	public static volatile SingularAttribute<Entry, BigDecimal> value;

	public static final String NOTE = "note";
	public static final String PERSON = "person";
	public static final String DUE_DATE = "dueDate";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String PAYMENT_DATE = "paymentDate";
	public static final String TYPE = "type";
	public static final String CATEGORY = "category";
	public static final String VALUE = "value";

}

