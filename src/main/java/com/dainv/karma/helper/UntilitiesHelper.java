package com.dainv.karma.helper;

import com.dainv.karma.model.Bill_detail;
import com.dainv.karma.model.Cart;
import com.dainv.karma.model.Product;
import com.dainv.karma.sevice.IService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UntilitiesHelper {



    public static int gettotal(List<Cart> carts){
        int total = 0;
        for (Cart c: carts) {
            total += ((c.getProduct().getPrice() -(c.getProduct().getPrice()/100 * c.getProduct().getSale()) )* c.getQuantity());
        }
        return total;
    }

    public static Date stringToDate(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MM/dd/yyyy");
       Date date1 =   simpleDateFormat.parse(date);
       String date2= simpleDateFormat2.format(date1);
       return simpleDateFormat2.parse(date2);
    }



}
