/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restWebServices;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author luismendoza
 */
@Path("curr")
public class CurrResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CurrResource
     */
    public CurrResource() {
    }

    /**
     * Retrieves representation of an instance of restWebServices.CurrResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/html")
    public String getHtml(@QueryParam("from")String from, @QueryParam("to")String to, @QueryParam("amount")String amount) {
        
        net.webservicex.Currency fromC;
        net.webservicex.Currency toC;
        
        if(from.equals("Pesos"))
            fromC = net.webservicex.Currency.MXN;
        else if(from.equals("Libras"))
            fromC = net.webservicex.Currency.GBP;
        else if(from.equals("Dolares C"))
            fromC = net.webservicex.Currency.CAD;
        else
            fromC = net.webservicex.Currency.USD;
        
        if(to.equals("Pesos"))
            toC = net.webservicex.Currency.MXN;
        else if(from.equals("Libras"))
            toC = net.webservicex.Currency.GBP;
        else if(from.equals("Dolares C"))
            toC = net.webservicex.Currency.CAD;
        else
            toC = net.webservicex.Currency.USD;
        
        double rate = conversionRate(fromC, toC);
        
        return (rate * Double.parseDouble(amount)) + "";
    }

    private static double conversionRate(net.webservicex.Currency fromCurrency, net.webservicex.Currency toCurrency) {
        net.webservicex.CurrencyConvertor service = new net.webservicex.CurrencyConvertor();
        net.webservicex.CurrencyConvertorSoap port = service.getCurrencyConvertorSoap12();
        return port.conversionRate(fromCurrency, toCurrency);
    }
    
    
}
