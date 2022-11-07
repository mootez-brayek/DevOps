package com.esprit.examen.controllers;



import java.util.List;

import com.esprit.examen.dto.StockDto;
import org.springframework.web.bind.annotation.*;

import com.esprit.examen.services.IStockService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "Gestion des stocks")
@RequestMapping("/stock")
@CrossOrigin("*")
public class StockRestController {


	IStockService stockService;

	public StockRestController(IStockService stockService){
		this.stockService=stockService ;
	}

	// http://localhost:8089/SpringMVC/stock/retrieve-all-stocks
	@GetMapping("/retrieve-all-stocks")
	@ResponseBody
	public List<StockDto> getStocks() {

		return stockService.retrieveAllStocks();
	}

	// http://localhost:8089/SpringMVC/stock/retrieve-stock/8
	@GetMapping("/retrieve-stock/{stock-id}")
	@ResponseBody
	public StockDto retrieveStock(@PathVariable("stock-id") Long stockId) {
		return stockService.retrieveStock(stockId);
	}

	// http://localhost:8089/SpringMVC/stock/add-stock
	@PostMapping("/add-stock")
	@ResponseBody
	public StockDto addStock(@RequestBody StockDto s) {

		return stockService.addStock(s) ;
	}


	@DeleteMapping("/remove-stock/{stock-id}")
	@ResponseBody
	public void removeStock(@RequestBody StockDto r) {
		stockService.deleteStock(r);
	}

	// http://localhost:8089/SpringMVC/stock/modify-stock
	@PutMapping("/modify-stock/{id}")
	@ResponseBody
	public StockDto modifyStock(@PathVariable Long id  ,  @RequestBody StockDto stock) {

		return stockService.updateStock(id , stock);
	}


}