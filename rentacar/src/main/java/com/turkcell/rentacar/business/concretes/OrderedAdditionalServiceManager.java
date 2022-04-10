package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.OrderedAdditionalServiceService;
import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.dtos.listDto.OrderedAdditionalServiceListDto;
import com.turkcell.rentacar.business.requests.create.CreateOrderedAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteOrderedAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.update.UpdateOrderedAdditionalServiceRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.OrderedAdditionalServiceDao;
import com.turkcell.rentacar.entities.concretes.OrderedAdditionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderedAdditionalServiceManager implements OrderedAdditionalServiceService {

    private OrderedAdditionalServiceDao orderedAdditionalServiceDao;
    private ModelMapperService modelMapperService;
    private RentService rentService;

    @Autowired
    public OrderedAdditionalServiceManager(OrderedAdditionalServiceDao orderedAdditionalServiceDao, ModelMapperService modelMapperService, RentService rentService) {
        this.orderedAdditionalServiceDao = orderedAdditionalServiceDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateOrderedAdditionalServiceRequest createOrderedAdditionalServiceRequest) throws BusinessException {

        OrderedAdditionalService orderedAdditionalService = this.modelMapperService.forRequest()
                .map(createOrderedAdditionalServiceRequest, OrderedAdditionalService.class);
        this.orderedAdditionalServiceDao.save(orderedAdditionalService);

        return new SuccessResult(BusinessMessages.ORDERED_ADDITIONAL_SERVICE_ADDED_SUCCESSFULLY);
    }

    @Override
    public Result update(UpdateOrderedAdditionalServiceRequest updateOrderedAdditionalServiceRequest) throws BusinessException {
        checkIfOrderedAdditionalServiceIdExists(updateOrderedAdditionalServiceRequest.getOrderedAdditionalServiceId());
        OrderedAdditionalService orderedAdditionalService=this.modelMapperService.forRequest()
                .map(updateOrderedAdditionalServiceRequest, OrderedAdditionalService.class);
        this.orderedAdditionalServiceDao.save(orderedAdditionalService);
        return new SuccessResult(BusinessMessages.ORDERED_ADDITIONAL_SERVICE_UPDATED_SUCCESSFULLY);
    }

    @Override
    public Result delete(DeleteOrderedAdditionalServiceRequest deleteOrderedAdditionalServiceRequest) throws BusinessException {
        checkIfOrderedAdditionalServiceIdExists(deleteOrderedAdditionalServiceRequest.getOrderedAdditionalServiceId());
        OrderedAdditionalService orderedAdditionalService = this.modelMapperService.forRequest()
                .map(deleteOrderedAdditionalServiceRequest, OrderedAdditionalService.class);
        this.orderedAdditionalServiceDao.deleteById(deleteOrderedAdditionalServiceRequest.getOrderedAdditionalServiceId());
        return new SuccessResult(BusinessMessages.ORDERED_ADDITIONAL_SERVICE_DELETED_SUCCESSFULLY);
    }

    @Override
    public DataResult<List<OrderedAdditionalServiceListDto>> getAll() throws BusinessException {

        List<OrderedAdditionalService> result = this.orderedAdditionalServiceDao.findAll();

        List<OrderedAdditionalServiceListDto> response = result.stream()
                .map(orderedAdditionalService -> this.modelMapperService.forDto()
                        .map(orderedAdditionalService, OrderedAdditionalServiceListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<OrderedAdditionalServiceListDto>>(response, BusinessMessages.ORDERED_ADDITIONAL_SERVICES_LISTED_SUCCESSFULLY);
    }


    @Override
    public List<OrderedAdditionalService> findOrderedAdditionalServicesByRent_RentId(int rentId) {


        return this.orderedAdditionalServiceDao.findOrderedAdditionalServicesByRent_RentId(rentId);
    }

    @Override
    public double calculateOrderedServicePrice(int rentId) {
        List<OrderedAdditionalService> result = this.orderedAdditionalServiceDao.findOrderedAdditionalServicesByRent_RentId(rentId);
        double totalPrice = 0;

        for(OrderedAdditionalService orderedAdditionalService : result){
            totalPrice += orderedAdditionalService.getOrderedAdditionalServiceAmount() * orderedAdditionalService.getAdditionalService().getDailyPrice();
        }
        return totalPrice;
    }

    private void checkIfOrderedAdditionalServiceIdExists(int id) throws BusinessException{
        if(!this.orderedAdditionalServiceDao.existsById(id)){
            throw new BusinessException(BusinessMessages.ORDERED_ADDITIONAL_SERVICE_NOT_FOUND);
        }
    }

}
