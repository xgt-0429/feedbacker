package com.example.feedbacker.dto.request.merchant;

import jakarta.validation.constraints.NotNull;
public class MergeRequest {

    @NotNull public Long targetId;

    @NotNull public Long postId;

}
