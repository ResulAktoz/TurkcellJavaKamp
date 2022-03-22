package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.getDto.GetOrderedAdditionalServiceDto;
import com.turkcell.rentacar.business.dtos.listDto.OrderedAdditionalServiceListDto;
import com.turkcell.rentacar.business.requests.create.CreateOrderedAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteOrderedAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.update.UpdateOrderedAdditionalServiceRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

import java.util.List;

public interface OrderedAdditionalServiceService {

    Result add(CreateOrderedAdditionalServiceRequest createOrderedAdditionalServiceRequest) throws BusinessException;
    Result update(UpdateOrderedAdditionalServiceRequest updateOrderedAdditionalServiceRequest) throws BusinessException;
    Result delete(DeleteOrderedAdditionalServiceRequest deleteOrderedAdditionalServiceRequest) throws BusinessException;

    DataResult<List<OrderedAdditionalServiceListDto>> getAll() throws BusinessException;
    DataResult<GetOrderedAdditionalServiceDto> getByOrderedAdditionalServiceId(Integer id) throws BusinessException;
    DataResult<List<OrderedAdditionalServiceListDto>> getByRentId(Integer id);

    double calculateOrderedServicePrice(int rentId);
}
