package moneyapi.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Address.class)
public abstract class Address_ {

	public static volatile SingularAttribute<Address, Integer> number;
	public static volatile SingularAttribute<Address, String> city;
	public static volatile SingularAttribute<Address, String> street;
	public static volatile SingularAttribute<Address, String> district;
	public static volatile SingularAttribute<Address, Integer> postalCode;
	public static volatile SingularAttribute<Address, String> state;
	public static volatile SingularAttribute<Address, String> complement;

	public static final String NUMBER = "number";
	public static final String CITY = "city";
	public static final String STREET = "street";
	public static final String DISTRICT = "district";
	public static final String POSTAL_CODE = "postalCode";
	public static final String STATE = "state";
	public static final String COMPLEMENT = "complement";

}

