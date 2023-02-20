package com.kosenkovps.benefitcafeteria.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDTO {
    @JsonProperty("idSender")
    private Long idSender;
    @JsonProperty("idTaker")
    private Long idTaker;
    @NonNull
    @JsonProperty("benefit")
    @DecimalMin(value = "0", message = "balance should not be less 0")
    private BigDecimal benefit;
}
