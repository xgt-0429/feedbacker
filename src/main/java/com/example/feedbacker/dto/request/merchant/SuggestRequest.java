package com.example.feedbacker.dto.request.merchant;

import jakarta.validation.constraints.NotBlank;

public class SuggestRequest {

    @NotBlank public String query;

}
