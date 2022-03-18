package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.InvoiceService;
import com.turkcell.rentacar.business.dtos.listDto.InvoiceListDto;
import com.turkcell.rentacar.business.requests.create.CreateInvoiceRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteInvoiceRequest;
import com.turkcell.rentacar.business.requests.update.UpdateInvoiceRequest;
import com.turkcell.rentacar.core.results.DataResult;
import com.turkcell.rentacar.core.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoicesController {

    private InvoiceService invoiceService;

    @Autowired
    public InvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/add")
    public Result add(CreateInvoiceRequest createInvoiceRequest) {

        return this.invoiceService.add(createInvoiceRequest);
    }

    @PutMapping("/update")
    public Result update(UpdateInvoiceRequest updateInvoiceRequest) {

        return this.invoiceService.update(updateInvoiceRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {

        return this.invoiceService.delete(deleteInvoiceRequest);
    }

    @GetMapping("/getAll")
    public DataResult<List<InvoiceListDto>> getAll() {

        return this.invoiceService.getAll();
    }
}
