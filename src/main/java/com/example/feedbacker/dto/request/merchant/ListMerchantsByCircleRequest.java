package com.example.feedbacker.dto.request.merchant;

import java.util.List;

public class ListMerchantsByCircleRequest {

    private List<Long> circleIds;

    public List<Long> getCircleIds() { return circleIds; }
    public void setCircleIds(List<Long> circleIds) { this.circleIds = circleIds; }

}
