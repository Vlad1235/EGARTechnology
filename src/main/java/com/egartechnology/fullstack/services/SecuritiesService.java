package com.egartechnology.fullstack.services;

import com.egartechnology.fullstack.dto.SecuritiesDto;
import java.util.List;

public interface SecuritiesService {

  SecuritiesDto changeSecurities(SecuritiesDto dto);
  List<SecuritiesDto> getAllSecurities();
}
