package com.egartechnology.fullstack.dao;

import com.egartechnology.fullstack.dto.SecuritiesDto;
import java.util.List;

public interface SecuritiesDao {
  SecuritiesDto createSecurities(SecuritiesDto dto);
  SecuritiesDto updateSecurities(SecuritiesDto dto);
  List<SecuritiesDto> getAllSecurities();
}
