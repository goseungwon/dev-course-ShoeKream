package com.prgrms.kream.domain.bid.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

import javax.validation.constraints.NotNull;

@ApiModel("구매 입찰 조회 요청")
public record BuyingBidFindRequest(
		@ApiModelProperty(value = "구매 입찰 아이디 목록", required = true, example = "[1, 2, 3]")
		@NotNull(message = "찾고자 하는 ID를 입력해주세요")
		List<Long> ids
) {
}
