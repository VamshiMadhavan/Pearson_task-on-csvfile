package com.pearson.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.pearson.dto.Store;
import com.pearson.responsemessage.StoreResponse;
import com.pearson.responsemessage.StoresResponse;

@WebMvcTest(value = StoreController.class)
class ControllerTest {

	@MockBean
	private StoreController controller;

	@Autowired
	private MockMvc mockMvc;

	Store store = new Store("1525eec4-7bed-4597-bf5a-e06fcede5f4f", "AB11 5PS", "Aberdeen",
			"LSU 1A Union Square Guild Street", "21/02/1965");

	List<Store> list = Arrays.asList(store);
	StoreResponse storeResponse = new StoreResponse("success", store);
	// StoresResponse storesResponse = new StoresResponse("success", list);

	@Test
	public void testGetAll() throws Exception {

		Mockito.when(controller.getAll()).thenReturn(new ResponseEntity<StoresResponse>(HttpStatus.OK));

		String myString = "/api/v1/stores/getAll";

		MockHttpServletRequestBuilder resultBuilder = MockMvcRequestBuilders.get(myString)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(resultBuilder).andReturn();

		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

	}

	@Test
	public void testOrderedByCities() throws Exception {
		Mockito.when(controller.orderedByCity()).thenReturn(new ResponseEntity<StoresResponse>(HttpStatus.OK));

		String myString = "/api/v1/stores/orederedByCities";

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(myString).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

	@Test
	public void testOrderedByDate() throws Exception {
		Mockito.when(controller.orderedByOpeningDate()).thenReturn(new ResponseEntity<StoresResponse>(HttpStatus.OK));

		String myString = "/api/v1/stores/orederedByOpeningDate";

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(myString).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

	@Test
	public void testGetByStoreId() throws Exception {

		Mockito.when(controller.getByStoreId(Mockito.anyString()))
				.thenReturn(new ResponseEntity<StoreResponse>(storeResponse, HttpStatus.OK));

		String myString = "/api/v1/stores/getByStoreId?storeId=1525eec4-7bed-4597-bf5a-e06fcede5f4f";

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(myString).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}



}
