package com.esprit.examen.dto;

import com.esprit.examen.Exceptions.CatchException;
import com.esprit.examen.entities.Stock;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class StockDto {

    private Long idStock;
    private String libelleStock;
    private Integer qte;
    private Integer qteMin;



    public static StockDto fromEntity(Stock stock){
        if(stock == null) throw  new CatchException(" stock is empty");
        return StockDto.builder()
                .idStock(stock.getIdStock())
                .libelleStock(stock.getLibelleStock())
                .qte(stock.getQte())
                .qteMin(stock.getQteMin())
                .build();
    }


    public static Stock toEntity(StockDto stockDto){
        if(stockDto == null) throw new CatchException(" stockDto is empty");
        return Stock.builder()
                .idStock(stockDto.getIdStock())
                .libelleStock(stockDto.getLibelleStock())
                .qte(stockDto.getQte())
                .qteMin(stockDto.getQteMin())
                .build();
    }


    public static List<StockDto> fromEntityList(List<Stock> stocks) {
        List<StockDto> list = new ArrayList<>();
        stocks.forEach(stock -> list.add(StockDto.fromEntity(stock)));
        return list ;


    }


}
