package com.egartechnology.fullstack.controllers;

import com.egartechnology.fullstack.dto.SecuritiesDto;
import com.egartechnology.fullstack.model.CreateSecuritiesRequestModel;
import com.egartechnology.fullstack.model.SecuritiesRequestModel;
import com.egartechnology.fullstack.model.SecuritiesResponseModel;
import com.egartechnology.fullstack.services.SecuritiesService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/securities")
public class SecuritiesControllers {

  private SecuritiesService service;

  @Autowired
  public SecuritiesControllers(SecuritiesService service) {
    this.service = service;
  }

  @RequestMapping(
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      path = ""
  )
  public List<SecuritiesResponseModel> getAllSecurities() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    List<SecuritiesDto> dto = service.getAllSecurities();
    List<SecuritiesResponseModel> responseModel = dto.stream().map(item -> modelMapper.map(item,SecuritiesResponseModel.class)).collect(Collectors.toList());
    return responseModel;
  }

  @RequestMapping(
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      path = ""
  )
  public ResponseEntity<SecuritiesResponseModel> addNewSecurity(@RequestBody @Valid CreateSecuritiesRequestModel requestModel) {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    SecuritiesDto dto = modelMapper.map(requestModel, SecuritiesDto.class);
    SecuritiesDto postData = service.changeSecurities(dto);
    SecuritiesResponseModel responseModel = modelMapper.map(postData,SecuritiesResponseModel.class);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
  }

  @RequestMapping(
      method = RequestMethod.PUT,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE,
      path = ""
  )
  public ResponseEntity<SecuritiesResponseModel> updateSecurity(@RequestBody @Valid SecuritiesRequestModel requestModel) {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    SecuritiesDto dto = modelMapper.map(requestModel, SecuritiesDto.class);
    SecuritiesDto updateData = service.changeSecurities(dto);
    SecuritiesResponseModel responseModel = modelMapper.map(updateData,SecuritiesResponseModel.class);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseModel);
  }

}
