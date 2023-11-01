package com.hummersoft.eira.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hummersoft.eira.repository.LoggerRepository;

import jakarta.transaction.Transactional;

@Service
public class LoggerServiceImpl implements LoggerService {

	@Autowired
	private LoggerRepository loggerRepository;

	@Transactional
	@Override
	 public List<Object> getParameterNamesBySiteId(BigInteger siteId) {
        return loggerRepository.findParameterNamesBySiteId(siteId);
    }

}
