package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CustomerService;
import com.turkcell.rentacar.business.dtos.getDto.GetCustomerDto;
import com.turkcell.rentacar.business.dtos.listDto.CustomerListDto;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.mapping.ModelMapperService;
import com.turkcell.rentacar.core.results.DataResult;
import com.turkcell.rentacar.core.results.SuccessDataResult;
import com.turkcell.rentacar.dataAccess.abstracts.CustomerDao;
import com.turkcell.rentacar.entities.concretes.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerManager implements CustomerService {

    private CustomerDao customerDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public CustomerManager(CustomerDao customerDao, ModelMapperService modelMapperService) {
        this.customerDao = customerDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<CustomerListDto>> getAll() throws BusinessException {
        List<Customer> result = this.customerDao.findAll();
        List<CustomerListDto> response = result.stream()
                .map(customer -> modelMapperService.forDto()
                        .map(customer, CustomerListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<CustomerListDto>>(response, "Müşteriler başarıyla sıralandı.");
    }

    @Override
    public void checkIfCustomerExists(int customerId) throws BusinessException {
        if(!this.customerDao.existsById(customerId)){
            throw new BusinessException("Bu id'ye ait müşteri bulunamadı.");
        }
    }


}
