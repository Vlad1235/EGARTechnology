package com.egartechnology.fullstack.dao.impl;

import com.egartechnology.fullstack.dao.SecuritiesDao;
import com.egartechnology.fullstack.dto.SecuritiesDto;
import com.egartechnology.fullstack.entities.SecuritiesEntity;
import com.egartechnology.fullstack.repository.SecuritiesRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SecuritiesDaoImpl implements SecuritiesDao {

  private SecuritiesRepository repository;

  @Autowired
  public SecuritiesDaoImpl(SecuritiesRepository repository) {
    this.repository = repository;
  }

  @Override
  public SecuritiesDto createSecurities(SecuritiesDto dto) {
    dto.setUuid(UUID.randomUUID().toString());
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    SecuritiesEntity securitiesEntity = modelMapper.map(dto, SecuritiesEntity.class);
    repository.save(securitiesEntity);
    SecuritiesDto returnedDto = modelMapper.map(securitiesEntity, SecuritiesDto.class);
    return returnedDto;
  }

  @Override
  public SecuritiesDto updateSecurities(SecuritiesDto dto) {
    String uuid = dto.getUuid();
    SecuritiesEntity byUuid = repository.findByUuid(uuid);
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    SecuritiesEntity securitiesEntity = modelMapper.map(dto, SecuritiesEntity.class);
    byUuid.setUuid(securitiesEntity.getUuid());
    byUuid.setSecurityName(securitiesEntity.getSecurityName());
    byUuid.setDateIssue(securitiesEntity.getDateIssue());
    byUuid.setPrice(securitiesEntity.getPrice());
    repository.save(byUuid);
    SecuritiesDto returnedDto = modelMapper.map(byUuid, SecuritiesDto.class);
    return returnedDto;
  }

  @Override
  public List<SecuritiesDto> getAllSecurities() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    List<SecuritiesEntity> securitiesEntity = repository.findAll();
    List<SecuritiesDto> dto = securitiesEntity.stream().map(item -> modelMapper.map(item, SecuritiesDto.class)).collect(Collectors.toList());
    return dto;
  }
}
