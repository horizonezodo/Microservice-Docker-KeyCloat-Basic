package com.horizon.productservice;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.horizon.productservice.dto.ProductRequest;
import com.horizon.productservice.repository.ProductRepository;
import com.mongodb.assertions.Assertions;

@SpringBootTest

@AutoConfigureMockMvc
class ProductServiceApplicationTests {


}
