package com.momentum.active.store.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Customer {
    @NonNull int customerId;
    @NonNull String customerName;
    @NonNull int activeDaysPoints;
}
