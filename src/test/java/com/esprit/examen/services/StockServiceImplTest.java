package com.esprit.examen.services;

import static org.junit.Assert.*;

import java.util.*;

import com.esprit.examen.dto.StockDto;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class StockServiceImplTest {


	@Autowired
	IStockService stockService;


	@Test
	public void testRetreiveAllStocks(){

		StockDto stockDto = StockDto
				.builder()
				.libelleStock("valid")
				.qte(20)
				.qteMin(10)
				.build();
		log.info("add new test stock");
		StockDto savedStock = stockService.addStock(stockDto);
		log.info("retrieve all stocks");
		List<StockDto> list = stockService.retrieveAllStocks();
		log.info("assert that stock list is not empty");
		assertNotEquals(list.size(), 0);

	}


	@Test
	public void testUpdateStock(){
		StockDto stockDto = StockDto
				.builder()
				.idStock(11L)
				.libelleStock("valid")
				.qte(20)
				.qteMin(10)
				.build();
		log.info("add new test stock");
		StockDto savedStock = stockService.addStock(stockDto);
		log.info("set Qte");
		savedStock.setQte(100);
		log.info("update stock ");
		StockDto updateStock = stockService.updateStock(savedStock.getIdStock(),savedStock);
		assertEquals(Optional.ofNullable(updateStock.getQte()),Optional.of(savedStock.getQte()));
		stockService.deleteStock(updateStock);
		log.info("Stock Deleted ");




	}


	@Test
	public void testDeleteStock() {
		StockDto stockDto = StockDto
				.builder()
				.idStock(110l)
				.libelleStock("valid")
				.qte(20)
				.qteMin(10)
				.build();
		log.info("add new test stock");
		StockDto savedStock= stockService.addStock(stockDto);
		log.info("Delete Stock");
		stockService.deleteStock(savedStock);
		log.info("Stock Deleted");
		assertNull(stockService.retrieveStock(savedStock.getIdStock()));
	}



	@Test
	public void testAddStock() {
		StockDto stock = StockDto
				.builder()
				.idStock(11L)
				.libelleStock("valid")
				.qte(20)
				.qteMin(10)
				.build();
		log.info("add new Stock ");
		StockDto savedStock = stockService.addStock(stock);
		Assert.assertNotNull(savedStock.getIdStock());
		Assert.assertEquals(Optional.ofNullable(savedStock.getQteMin()),Optional.of(10));
		stockService.deleteStock(savedStock);
		log.info("Stock Deleted");

	} 




}
