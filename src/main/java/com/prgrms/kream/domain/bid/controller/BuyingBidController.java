package com.prgrms.kream.domain.bid.controller;

import com.prgrms.kream.common.api.ApiResponse;
import com.prgrms.kream.domain.bid.dto.request.BuyingBidCreateRequest;
import com.prgrms.kream.domain.bid.dto.response.BuyingBidCreateResponse;
import com.prgrms.kream.domain.bid.dto.response.BuyingBidFindResponse;
import com.prgrms.kream.domain.bid.facade.BuyingBidFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/buying-bids")
@RequiredArgsConstructor
@Api(tags = "구매 입찰 컨트롤러")
public class BuyingBidController {
	private final BuyingBidFacade facade;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "구매 입찰 등록")
	public ApiResponse<BuyingBidCreateResponse> register(
			@RequestBody @Valid
			@ApiParam(value = "구매 입찰을 등록하기 위한 정보", required = true)
			BuyingBidCreateRequest buyingBidCreateRequest) {
		return ApiResponse.of(facade.register(buyingBidCreateRequest));
	}

	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "구매 입찰 조회")
	public ApiResponse<BuyingBidFindResponse> findOne(
			@PathVariable("id")
			@ApiParam(value = "찾고자 하는 구매 입찰의 id", required = true, example = "1")
			Long id) {
		return ApiResponse.of(facade.findById(id));
	}

	@PutMapping("/delete/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "구매 입찰 삭제")
	public ApiResponse<String> delete(
			@PathVariable("id")
			@ApiParam(value = "삭제 하고자 하는 구매 입찰의 id", required = true, example = "1")
			Long id) {
		facade.deleteById(id);
		return ApiResponse.of("구매 입찰이 삭제되었습니다");
	}

	@PutMapping("/restore/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "구매 입찰 복구")
	public ApiResponse<String> restore(
			@PathVariable("id")
			@ApiParam(value = "복구 하고자 하는 구매 입찰의 id", required = true, example = "1")
			Long id) {
		facade.restoreById(id);
		return ApiResponse.of("구매 입찰이 복구되었습니다");
	}
}
