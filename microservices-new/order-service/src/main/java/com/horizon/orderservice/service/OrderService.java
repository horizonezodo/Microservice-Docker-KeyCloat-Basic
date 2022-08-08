package com.horizon.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.horizon.orderservice.dto.InventoryResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.horizon.orderservice.dto.OrderLineItemsDto;
import com.horizon.orderservice.dto.OrderRequest;
import com.horizon.orderservice.model.Order;
import com.horizon.orderservice.model.OrderLineItems;
import com.horizon.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final WebClient.Builder webClientBuilder;
	
	public String placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
				.stream()
				.map(this::mapToDto)
				.toList();
		
		order.setOrderLineItemsList(orderLineItems);

		List<String> skuCode = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();

		//Call inventory service, and place order if product is in
		// stock
		InventoryResponse[] inventoryResponsesArray = webClientBuilder.build().get()
						.uri("http://inventory-service/api/inventory",
								uriBuilder -> uriBuilder.queryParam("skuCode" , skuCode).build())
						.retrieve()
						.bodyToMono(InventoryResponse[].class)
						.block();
		boolean allProductInStock =Arrays.stream(inventoryResponsesArray)
				.allMatch(InventoryResponse::isInStock);

		if(allProductInStock){
			orderRepository.save(order);
			return "Order Placed Successfully!";
		}
		else{
			throw new IllegalArgumentException("Product is not in stock , please try again later");
		}
		
	}
	
	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuanity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		return orderLineItems;
	}
}
