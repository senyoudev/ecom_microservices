package com.senyoudev.orderservice.dto;
import com.senyoudev.orderservice.model.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    public List<OrderLineItemsDto> orderLineItemsDtos;
}
