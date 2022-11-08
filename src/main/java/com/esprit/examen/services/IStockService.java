package com.esprit.examen.services;

import java.util.List;

import com.esprit.examen.dto.StockDto;

public interface IStockService {

	List<StockDto> retrieveAllStocks();

	StockDto addStock(StockDto s);

	void deleteStock(StockDto r);

	StockDto updateStock(Long id , StockDto u);

	StockDto retrieveStock(Long id);

	String retrieveStatusStock();
}
