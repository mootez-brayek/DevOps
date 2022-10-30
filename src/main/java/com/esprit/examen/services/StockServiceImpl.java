package com.esprit.examen.services;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.StockRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StockServiceImpl implements IStockService {

	StockRepository stockRepository;

	public StockServiceImpl(StockRepository stockRepository){
		this.stockRepository=stockRepository;
	}


	@Override
	public List<Stock> retrieveAllStocks() {
		// récuperer la date à l'instant t1
		log.info("Begin Processing - Retreive all Stock Method ! ");
		List<Stock> stocks = (List<Stock>) stockRepository.findAll();
		stocks.forEach(stock -> {log.info("Stock : " +stock);} );
		log.info("End Processing - Retreive all Stock Method ! \"");
		// récuperer la date à l'instant t2
		// temps execution = t2 - t1
		return stocks;
	}

	@Override
	public Stock addStock(Stock s) {
		// récuperer la date à l'instant t1
		log.info("Begin Processing addStock");
		return stockRepository.save(s);
		
	}

	@Override
	public void deleteStock(Long stockId) {
		log.info("Begin Processing deleteStock");
		stockRepository.deleteById(stockId);
		log.info("End Processing deleteStock");

	}

	@Override
	public Stock updateStock(Long id ,  Stock s) {
		log.info("Begin Processing updateStock");
		s.setIdStock(id);
		return stockRepository.save(s);
	}

	@Override
	public Stock retrieveStock(Long stockId) {
		long start = System.currentTimeMillis();
		log.info("Begin Processing -  retrieveStock");
		Stock stock = stockRepository.findById(stockId).orElse(null);
		log.info("End  processing  _ retrieveStock");
		 long elapsedTime = System.currentTimeMillis() - start;
		log.info("Method execution time: " + elapsedTime + " milliseconds.");

		return stock;
	}

	@Override
	public String retrieveStatusStock() {
		log.info("Begin Processing - retrieveStatusStock () ");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now =  new Date();
		String msgDate = sdf.format(now);
		String finalMessage = "";
		String newLine = System.getProperty("line.separator");
		List<Stock> stocksEnRouge = (List<Stock>) stockRepository.retrieveStatusStock();
		for (int i = 0; i < stocksEnRouge.size(); i++) {
			finalMessage = newLine + finalMessage + msgDate + newLine + ": le stock "
					+ stocksEnRouge.get(i).getLibelleStock() + " a une quantité de " + stocksEnRouge.get(i).getQte()
					+ " inférieur à la quantité minimale a ne pas dépasser de " + stocksEnRouge.get(i).getQteMin()
					+ newLine;

		}
		log.info(finalMessage);
		log.info("End Processing - retrieveStatusStock ()");
		return finalMessage;
	}

}