package com.hummersoft.eira.service;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManagerFactory;

@Service
public class ParameterComparisionServiceImpl implements ParameterComparisionService {

	private final EntityManagerFactory entityManagerFactory;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;

	@Autowired
	public ParameterComparisionServiceImpl(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Transactional(readOnly = true, propagation=Propagation.NOT_SUPPORTED)
		public List<Object[]> getCompValue(int siteid, String range, Timestamp fDate, Timestamp tdate,
			List<Integer> listEquipmentIds, JSONArray parameters) {
//		SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
//		Session session = sessionFactory.openSession();
		session=sessionFactory.getCurrentSession();
		String params = null;
		for (int i = 0; i < parameters.length(); i++) {
			String value = parameters.getString(i);

			if (null == params)
				params = value + ",";
			else
				params = params + value + ",";
		}

		
		StringBuilder stringBuilder = new StringBuilder("(");
		for (int i = 0; i < listEquipmentIds.size(); i++) {
			stringBuilder.append(listEquipmentIds.get(i));
			if (i < listEquipmentIds.size() - 1) {
				stringBuilder.append(", ");
			}
		}
		stringBuilder.append(")");

//		session.beginTransaction();

		String hqlQuery = "SELECT  " + params + "equipmentid,"
				+ " DATE_TRUNC('minute',timestamp)+ INTERVAL '5 hours 30 minutes' AS timestamp"
				+ " from eampm.tdatatransaction " + "where siteid=" + siteid + " and equipmentid in "
				+ stringBuilder.toString() + "and " + " timestamp between '" + fDate + "' and '" + tdate
				+ "' order by equipmentid, timestamp";
		System.out.println(hqlQuery);
		List<Object[]> arrayOfArrays = session.createNativeQuery(hqlQuery).getResultList();
		return arrayOfArrays;
	}
}
