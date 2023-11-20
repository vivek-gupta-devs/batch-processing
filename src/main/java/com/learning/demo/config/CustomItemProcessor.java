package com.learning.demo.config;

import com.learning.demo.entity.Product;
import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor implements ItemProcessor<Product, Product> {


    @Override
    public Product process(Product item) throws Exception {
        /**
         * transformation logic.
         * will calculate discount price based on parameter ( price, discount )
         */
        int discount_percent = Integer.parseInt(item.getDiscount());
        int price = Integer.parseInt(item.getPrice());
        double final_price = price  * (1 - (discount_percent/100f));
        item.setDiscountedPrice(String.valueOf(final_price));
        return item;

    }
}
