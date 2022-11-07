package com.esprit.examen.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.esprit.examen.dto.StockDto;
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
	public List<StockDto> retrieveAllStocks() {
		// récuperer la date à l'instant t1
		log.info("Begin Processing - Retreive all Stock Method ! ");
		List<StockDto> stocks =  StockDto.fromEntityList( stockRepository.findAll());
		stocks.forEach(stock -> log.info("Stock : " +stock) );
		log.info("End Processing - Retreive all Stock Method ! \"");
		// récuperer la date à l'instant t2
		// temps execution = t2 - t1
		return stocks;
	}

	@Override
	public StockDto addStock(StockDto s) {
		// récuperer la date à l'instant t1
		log.info("Begin Processing addStock");
		stockRepository.save(StockDto.toEntity(s));
		log.info("End Processing addStock");
		return s ;
		
	}

	@Override
	public void deleteStock(StockDto r) {
		long start = System.currentTimeMillis();
		log.info("In method deleteStock ");
		stockRepository.delete(StockDto.toEntity(r));
		log.info("out of method deleteStock ");
		long elapsedTime = System.currentTimeMillis() - start;
		log.info("Method execution time: " + elapsedTime + " milliseconds.");
	}

	@Override
	public StockDto updateStock(Long id ,  StockDto s) {
		log.info("Begin Processing updateStock");
		s.setIdStock(id);
		stockRepository.save(StockDto.toEntity(s));
		log.info("End Processing updateStock");
		return s ;
	}

	public StockDto retrieveStock(Long id) {
		long start = System.currentTimeMillis();
		log.info("In method retrieveReglement of ReglementServic");
		StockDto result =  stockRepository
				.findById(id)
				.map(StockDto::fromEntity)
				.orElse(null);
		log.info("out of method retrieveReglement ReglementServic");
		long elapsedTime = System.currentTimeMillis() - start;
		log.info("Method execution time: " + elapsedTime + " milliseconds.");
		return result;
	}

	@Override
	public String retrieveStatusStock() {
		log.info("Begin Processing - retrieveStatusStock () ");
		var sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		var now =  new Date();
		String msgDate = sdf.format(now);
		var finalMessage =  new StringBuilder();
		String newLine = System.getProperty("line.separator");
		List<Stock> stocksEnRouge =  stockRepository.retrieveStatusStock();
		for (var i = 0; i < stocksEnRouge.size(); i++) {
			finalMessage.append(newLine + finalMessage + msgDate + newLine + ": le stock "
					+ stocksEnRouge.get(i).getLibelleStock() + " a une quantité de " + stocksEnRouge.get(i).getQte()
					+ " inférieur à la quantité minimale a ne pas dépasser de " + stocksEnRouge.get(i).getQteMin()
					+ newLine);

		}
		log.info(finalMessage.toString());
		log.info("End Processing - retrieveStatusStock ()");
		return finalMessage.toString();
	}



}