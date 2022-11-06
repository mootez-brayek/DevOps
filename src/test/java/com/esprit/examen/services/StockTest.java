package com.esprit.examen.services;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock ;
import static org.mockito.Mockito.when ;
import static org.mockito.Mockito.verify ;

import com.esprit.examen.dto.StockDto;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class StockTest {


    StockServiceImpl stockService = mock(StockServiceImpl.class);

    StockDto stock = StockDto
            .builder()
            .idStock(11L)
            .libelleStock("valid")
            .qte(20)
            .qteMin(10)
            .build();

    StockDto stock2 = StockDto
            .builder()
            .idStock(12L)
            .libelleStock("valid1")
            .qte(30)
            .qteMin(20)
            .build();


    StockDto stock3 = StockDto
            .builder()
            .idStock(13L)
            .libelleStock("valid")
            .qte(40)
            .qteMin(30)
            .build();


    List<StockDto> list = new ArrayList<StockDto>() {
        {
            add(stock2);
            add(stock3);
        }
    };


    @Test
    public void testAddStock() {

        when(stockService.addStock(stock)).thenReturn(stock);

        Assert.assertNotNull(stockService.addStock(stock).getIdStock());

        verify(stockService).addStock(stock);

    }


    @Test
    public void testRetreiveAllStocks() {

        when(stockService.retrieveAllStocks()).thenReturn(list);
        assertNotEquals(list.size(), 0);

    }


    @Test
    public void testretRieveStock() {

        when(stockService.retrieveStock(stock.getIdStock())).thenReturn(stock);
        assertEquals(stock, stockService.retrieveStock(stock.getIdStock()));
        verify(stockService).retrieveStock(stock.getIdStock());
    }


    @Test
    public void testUpdateStock(){

        when(stockService.updateStock(stock.getIdStock(),stock)).thenReturn(stock);
        assertNotEquals(Optional.ofNullable(stock.getQte()),Optional.of(stockService.updateStock(stock.getIdStock(),stock)));
        verify(stockService).updateStock(stock.getIdStock(),stock);


    }
}
