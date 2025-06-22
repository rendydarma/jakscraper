package com.jakmall.jakscraper.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jakmall.jakscraper.dao.InvoiceDao;
import com.jakmall.jakscraper.dao.InvoiceDetailDao;
import com.jakmall.jakscraper.dto.InsertResDto;
import com.jakmall.jakscraper.dto.invoice.InvoiceInsertReqDto;
import com.jakmall.jakscraper.dto.invoice.InvoiceReqDto;
import com.jakmall.jakscraper.dto.invoice.InvoiceResDto;
import com.jakmall.jakscraper.dto.invoicedetail.InvoiceDetailInsertReqDto;
import com.jakmall.jakscraper.dto.invoicedetail.InvoiceDetailResDto;
import com.jakmall.jakscraper.dto.soldproduct.SoldProductInsertReqDto;
import com.jakmall.jakscraper.model.Invoice;
import com.jakmall.jakscraper.model.InvoiceDetail;
import com.jakmall.jakscraper.model.Product;
import com.jakmall.jakscraper.model.Store;
import com.jakmall.jakscraper.repo.ProductRepo;
import com.jakmall.jakscraper.repo.StoreRepo;

@Service
public class InvoiceService {

	@Autowired
	private StoreRepo storeRepo;

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private InvoiceDao invoiceDao;

	@Autowired
	private InvoiceDetailDao invoiceDetailDao;
	
	@Autowired
	private SoldProductService soldProductService;

	@Transactional
	public InsertResDto insert(InvoiceInsertReqDto data) {
		try {
			final Invoice invoice = new Invoice();
			invoice.setCode(data.getInvoiceCode());
			invoice.setBuyerName(data.getBuyerName());
			invoice.setBuyerAddress(data.getBuyerAddress());
			invoice.setGrandTotal(data.getGrandTotal());

			final Store store = storeRepo.getReferenceById(data.getStoreId());
			invoice.setStore(store);
			invoice.setCreatedBy(0L);
			final Invoice invoiceDb = invoiceDao.insert(invoice);

			for (InvoiceDetailInsertReqDto d : data.getDetails()) {
				final InvoiceDetail detail = new InvoiceDetail();
				detail.setInvoice(invoiceDb);

				final Product product = productRepo.getBySku(d.getSku().toUpperCase());
				detail.setProduct(product);
				detail.setPrice(product.getPrice());
				detail.setQty(d.getQty());
				detail.setTotalPrice(d.getQty()*product.getPrice());
				detail.setCreatedBy(0L);
				invoiceDetailDao.insert(detail);
				
				final SoldProductInsertReqDto soldProductInsert = new SoldProductInsertReqDto();
				soldProductInsert.setSku(product.getSku());
				soldProductInsert.setQty(detail.getQty());
				soldProductInsert.setGrandTotal(detail.getTotalPrice());
				soldProductService.insert(soldProductInsert);
			}

			if (invoice.getId() != null) {
				final InsertResDto response = new InsertResDto();
				response.setId(invoice.getId());
				response.setMessage("Insert has been successfully");
				return response;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<InvoiceResDto> getAllByStore(InvoiceReqDto data) {
		final List<InvoiceResDto> responses = new ArrayList<>();

		final List<Invoice> invoices = invoiceDao.getAllByStore(data.getStoreId());
		for (Invoice i : invoices) {
			final InvoiceResDto response = new InvoiceResDto();
			response.setId(i.getId());
			response.setInvoiceCode(i.getCode());
			response.setGrandTotal(i.getGrandTotal());

			final List<InvoiceDetail> invoiceDetails = invoiceDetailDao.getDetail(i.getId());
			final List<InvoiceDetailResDto> responseDetail = new ArrayList<>();
			for (InvoiceDetail id : invoiceDetails) {
				final InvoiceDetailResDto detail = new InvoiceDetailResDto();
				detail.setSku(id.getProduct().getSku());
				detail.setProductName(id.getProduct().getProductName());
				detail.setProductPrice(id.getPrice());
				detail.setQty(id.getQty());
				detail.setTotalPrice(id.getPrice());
				responseDetail.add(detail);
			}

			response.setDetails(responseDetail);
			responses.add(response);

		}

		return responses;
	}
	
	public List<InvoiceResDto> getAll() {
		final List<InvoiceResDto> responses = new ArrayList<>();

		final List<Invoice> invoices = invoiceDao.getAll();
		for (Invoice i : invoices) {
			final InvoiceResDto response = new InvoiceResDto();
			response.setId(i.getId());
			response.setStoreName(i.getStore().getStoreName());
			response.setInvoiceCode(i.getCode());
			response.setGrandTotal(i.getGrandTotal());

			final List<InvoiceDetail> invoiceDetails = invoiceDetailDao.getDetail(i.getId());
			final List<InvoiceDetailResDto> responseDetail = new ArrayList<>();
			for (InvoiceDetail id : invoiceDetails) {
				final InvoiceDetailResDto detail = new InvoiceDetailResDto();
				detail.setSku(id.getProduct().getSku());
				detail.setProductName(id.getProduct().getProductName());
				detail.setProductPrice(id.getPrice());
				detail.setQty(id.getQty());
				detail.setTotalPrice(id.getPrice());
				responseDetail.add(detail);
			}

			response.setDetails(responseDetail);
			responses.add(response);

		}

		return responses;
	}
}
