package com.politecnicomalaga.NasdaqOilPrices.Control;

import com.politecnicomalaga.NasdaqOilPrices.Model.Price;

import java.util.ArrayList;
import java.util.List;

public class ControlGrafico {

    List<Double> Precios = new ArrayList<>();

    public ControlGrafico() {
        createListDouble(MainController.getSingleton().getDataFromNasdaq());
        }

    public List<Double> getPrecioDouble() {
        return this.Precios;
    }

    private void createListDouble (List<Price> nuevaLista) {

        for (Price item:nuevaLista) {
            Precios.add(Double.parseDouble(item.getPrice()));
        }
    }
}
