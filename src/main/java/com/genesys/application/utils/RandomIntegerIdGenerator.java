package com.genesys.application.utils;

import java.io.Serializable;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class RandomIntegerIdGenerator implements IdentifierGenerator{

	/**
	 * @return random id from 0 - 999999
	 */
	public Integer generateIntegerId () {
		Random rd = new Random();
		return Integer.valueOf(rd.nextInt(999999));
	}
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		return this.generateIntegerId();
	}

}
