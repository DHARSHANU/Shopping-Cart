package org.jsp.Spring_boot_project.helper;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class Merchantid implements IdentifierGenerator {

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		String randomnumber = String.valueOf((int)(Math.random()*1000));
		return "mid"+randomnumber;
	}

}
