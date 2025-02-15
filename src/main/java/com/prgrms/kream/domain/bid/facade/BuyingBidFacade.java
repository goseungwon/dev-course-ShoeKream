package com.prgrms.kream.domain.bid.facade;

import com.prgrms.kream.domain.bid.dto.request.BuyingBidCreateRequest;
import com.prgrms.kream.domain.bid.dto.request.BuyingBidFindRequest;
import com.prgrms.kream.domain.bid.dto.response.BuyingBidCreateResponse;
import com.prgrms.kream.domain.bid.dto.response.BuyingBidFindResponse;
import com.prgrms.kream.domain.bid.service.BuyingBidService;
import com.prgrms.kream.domain.product.service.ProductService;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BuyingBidFacade {
	private final BuyingBidService buyingBidService;

	private final ProductService productService;

	@Transactional
	public BuyingBidCreateResponse register(BuyingBidCreateRequest buyingBidCreateRequest) {
		productService.compareHighestPrice(buyingBidCreateRequest.productOptionId(), buyingBidCreateRequest.price());
		return buyingBidService.register(buyingBidCreateRequest);
	}

	@Transactional(readOnly = true)
	public BuyingBidFindResponse findById(Long id) {
		List<Long> ids = Collections.singletonList(id);
		BuyingBidFindRequest buyingBidFindRequest = new BuyingBidFindRequest(ids);
		return buyingBidService.findById(buyingBidFindRequest);
	}

	@Transactional
	public void deleteById(Long id) {
		buyingBidService.deleteById(id);
	}

	@Transactional
	public void restoreById(Long id) {
		buyingBidService.restoreById(id);
	}
}
