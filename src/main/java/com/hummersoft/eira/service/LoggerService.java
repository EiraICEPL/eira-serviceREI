package com.hummersoft.eira.service;

import java.math.BigInteger;
import java.util.List;

public interface LoggerService {

	List<Object> getParameterNamesBySiteId(BigInteger siteId);

}
