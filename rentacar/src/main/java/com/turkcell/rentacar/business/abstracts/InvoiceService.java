package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.getDto.GetInvoiceDto;
import com.turkcell.rentacar.business.dtos.listDto.InvoiceListDto;
import com.turkcell.rentacar.business.requests.create.CreateInvoiceRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteInvoiceRequest;
import com.turkcell.rentacar.business.requests.update.UpdateInvoiceRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.entities.concretes.Invoice;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceService {

    Result addForCustomer(CreateInvoiceRequest createInvoiceRequest);
    Result update(UpdateInvoiceRequest updateInvoiceRequest) ;
    Result delete(DeleteInvoiceRequest deleteInvoiceRequest) ;
    Invoice add(int rentId);

    DataResult<List<InvoiceListDto>> getAll() ;
    DataResult<List<InvoiceListDto>> getByUserId(int userId) ;
    DataResult<List<InvoiceListDto>> findByCreationDayBetween(LocalDate startDate, LocalDate endDate);

    void setInvoiceFields(int rentId, Invoice invoice);

}
