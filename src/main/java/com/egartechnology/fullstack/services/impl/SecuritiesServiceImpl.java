package com.egartechnology.fullstack.services.impl;

import com.egartechnology.fullstack.dao.SecuritiesDao;
import com.egartechnology.fullstack.dto.SecuritiesDto;
import com.egartechnology.fullstack.services.SecuritiesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecuritiesServiceImpl implements SecuritiesService {

  private SecuritiesDao dao;

  @Autowired
  public SecuritiesServiceImpl(SecuritiesDao dao) {
    this.dao = dao;
  }

  @Override
  public SecuritiesDto changeSecurities(SecuritiesDto dto) {
    if (dto.getUuid() == null) {
      return dao.createSecurities(dto);
    } else {
      return dao.updateSecurities(dto);
    }
  }

  @Override
  public List<SecuritiesDto> getAllSecurities() {
    return dao.getAllSecurities();
  }
}
