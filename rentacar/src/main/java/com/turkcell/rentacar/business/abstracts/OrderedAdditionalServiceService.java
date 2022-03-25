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

    Result add(CreateOrderedAdditionalServiceRequest createOrderedAdditionalServiceRequest);
    Result update(UpdateOrderedAdditionalServiceRequest updateOrderedAdditionalServiceRequest);
    Result delete(DeleteOrderedAdditionalServiceRequest deleteOrderedAdditionalServiceRequest);

    DataResult<List<OrderedAdditionalServiceListDto>> getAll();
    DataResult<List<OrderedAdditionalServiceListDto>> getByRentId(Integer id);
    double calculateOrderedServicePrice(int rentId);
}
