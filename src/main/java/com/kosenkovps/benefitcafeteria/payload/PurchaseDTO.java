package com.kosenkovps.benefitcafeteria.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {
    @JsonProperty("employeeId")
    private Long employeeId;
    @JsonProperty("productId")
    private Long productId;
}