package com.esprit.examen.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.esprit.examen.entities.Stock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceImplTest {
	@Autowired
	IStockService stockService;



	@Test
	public void testRetreiveAllStocks(){

		List<Stock> s = new ArrayList<Stock>(stockService.retrieveAllStocks());
		assertNotNull(s);
	}

	@Test
	public void testretRieveStock(){
		Stock s = new Stock("stock ",12,123);
		Stock saved = stockService.addStock(s);
		Stock retrieved = stockService.retrieveStock(saved.getIdStock());
		assertNotNull(retrieved);
		assertEquals(saved.getIdStock(),retrieved.getIdStock());
	}

	@Test
	public void testUpdateStock(){
		Stock s = new Stock ("stock  ",22,111);
		Stock updatedStock= stockService.updateStock(s.getIdStock(), s);
		assertNotNull(updatedStock.getIdStock());
		assertSame(s.getIdStock(),updatedStock.getIdStock());

	}


	@Test
	public void testDeleteStock() {
		Stock s = new Stock("stock ",30,60);
		Stock savedStock= stockService.addStock(s);
		stockService.deleteStock(savedStock.getIdStock());
		assertNull(stockService.retrieveStock(savedStock.getIdStock()));
	}


	@Test
	public void testAddStock() {

		Stock s = new Stock("stock ",10,100);
		Stock savedStock= stockService.addStock(s);
		assertNotNull(savedStock.getIdStock());
		assertSame(s.getQte(), savedStock.getQte());
		assertTrue(savedStock.getQteMin()>0);
		stockService.deleteStock(savedStock.getIdStock());

	} 




}
