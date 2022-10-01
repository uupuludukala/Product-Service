//package com.coolbook.erp.rest.controller;
//
//import com.coolbook.erp.model.EasyPaymentPost;
//import com.coolbook.erp.model.InvoiceGet;
//import com.coolbook.erp.model.InvoicePost;
//import com.coolbook.erp.rest.assembler.EasyPaymentAssembler;
//import com.coolbook.erp.rest.assembler.InvoiceAssembler;
//import com.coolbook.erp.rest.service.EasyPaymentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
//import org.springframework.hateoas.Link;
//import org.springframework.hateoas.PagedResources;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
//import static org.springframework.http.ResponseEntity.ok;
//
//@RestController
//public class EasyPaymentController {
//
//    @Autowired
//    private EasyPaymentService easyPaymentService;
//
//    @Autowired
//    private EasyPaymentAssembler easyPaymentAssembler;
//
//    @RequestMapping(value = "saveInvoice", method = RequestMethod.POST)
//    public ResponseEntity<Long> saveInvoice(@RequestBody @Valid EasyPaymentPost easyPayment) throws Exception {
//        long easyPaymentId = easyPaymentService.saveEasyPayment((easyPaymentAssembler.assembleEasyPaymentEntity(easyPayment)));
//        HttpHeaders header = new HttpHeaders();
//        header.add("easyPaymentId", String.valueOf(easyPaymentId));
//        header.setLocation(linkTo(InvoiceController.class).slash(easyPaymentId).toUri());
//        return new ResponseEntity<>(easyPaymentId, header, HttpStatus.CREATED);
//    }
//
//    @RequestMapping(value = "getInvoiceById/{id}", method = RequestMethod.GET)
//    public ResponseEntity<InvoiceGet> getInvoiceById(@PathVariable("id") long id) {
//        return ResponseEntity.ok().body(invoiceAssembler.assembleInvoiceGet(this.invoiceService.getInvoiceById(id)));
//    }
//
//    @RequestMapping(value = "searchInvoice", method = RequestMethod.GET)
//    public ResponseEntity<PagedResources<InvoiceGet>> searchInvoice(Pageable pageable, HttpServletRequest request,
//                                                                    String searchValue) {
//        String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
//        String basePath = StringUtils.isEmpty(proxyRequestUri)
//                ? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
//                : proxyRequestUri;
//        return ok(pagedResourcesAssembler.toResource(this.invoiceService.searchInvoice(pageable, searchValue), assembler,
//                new Link(basePath)));
//    }
//
//    @RequestMapping(value = "cancelInvoice/{id}", method = RequestMethod.POST)
//    public ResponseEntity<Void> cancelInvoice(@PathVariable long id) throws Exception {
//        invoiceService.cancelInvoice(id);
//        return new ResponseEntity<Void>(HttpStatus.OK);
//    }
//}
