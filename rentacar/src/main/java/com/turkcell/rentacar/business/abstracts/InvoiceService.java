package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.getDto.GetInvoiceDto;
import com.turkcell.rentacar.business.dtos.listDto.InvoiceListDto;
import com.turkcell.rentacar.business.requests.create.CreateInvoiceRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteInvoiceRequest;
import com.turkcell.rentacar.business.requests.update.UpdateInvoiceRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.results.DataResult;
import com.turkcell.rentacar.core.results.Result;

import java.util.List;

public interface InvoiceService {

    Result add(CreateInvoiceRequest createInvoiceRequest) throws BusinessException;
    Result update(UpdateInvoiceRequest updateInvoiceRequest) throws BusinessException;
    Result delete(DeleteInvoiceRequest deleteInvoiceRequest) throws BusinessException;

    DataResult<List<InvoiceListDto>> getAll() throws BusinessException;
    DataResult<List<GetInvoiceDto>> getByInvoiceId(int invoiceId) throws BusinessException;
    DataResult<List<InvoiceListDto>> getByUserId(int userId) throws BusinessException;

}
