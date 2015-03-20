/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserviceclient;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author luismendoza
 */
public class WebServiceClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String citiesRes = getCitiesByCountry("Mexico");
        Document doc = getDocumentToBeParsed(citiesRes);
        String[] cities = parseXML(doc, "Table", "City");

        String airportRes = getAirportInformationByCountry("Mexico");
        doc = getDocumentToBeParsed(airportRes);
        String[] airports = parseXML(doc, "Table", "CityOrAirportName");
                
        String currencyRes = getCurrencyByCountry("Mexico");
        doc = getDocumentToBeParsed(currencyRes);
        String[] currency = parseXML(doc, "Table", "Currency");
        
        
        StringBuilder respuesta = new StringBuilder();
        
        System.out.println("Pais: México");
        System.out.println("Currency: " + currency[0]);
        
        respuesta.append("Pais: México");
        respuesta.append("Currency: " + currency[0]);
        
        System.out.println("\nAiport Info:\n");
        
        for (int i = 0; i < airports.length; i++) {
            System.out.println("Airport: " + airports[i]);
            respuesta.append("Airport: " + airports[i]);
        }
        
        for (int i = 0; i < cities.length; i++) {
            try{
                System.out.println("City: " + cities[i]);
                respuesta.append("City: " + cities[i]);

                String weatherRes = getWeather(cities[i], "Mexico");
                doc = getDocumentToBeParsed(weatherRes);
                String[] weather = parseXML(doc, "CurrentWeather", "Temperature");

                System.out.println("Temperature: " + weather[0]);
                respuesta.append("Temperature: " + weather[0]);
            }catch(Exception e){
                
            }
        }
        
        convertBytesToImage("braile", brailleText(respuesta.toString(), 12));
    }

    

    private static String getCitiesByCountry(java.lang.String countryName) {
        WeatherClient.GlobalWeather service = new WeatherClient.GlobalWeather();
        WeatherClient.GlobalWeatherSoap port = service.getGlobalWeatherSoap();
        return port.getCitiesByCountry(countryName);
    }
    
    private static Document getDocumentToBeParsed(String result){
        try {
            if (result.startsWith("<?xml")){
                result=result.substring(result.indexOf("?>")+2);
            }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            PrintWriter out = new PrintWriter("xmlfile.txt");
            out.println(result);
            out.close();
            File f = new File("xmlfile.txt");
            return dBuilder.parse(f);
        } catch(Exception e){
            System.out.println(e);
            return null;
        }
    } 

    private static String getWeather(java.lang.String cityName, java.lang.String countryName) {
        WeatherClient.GlobalWeather service = new WeatherClient.GlobalWeather();
        WeatherClient.GlobalWeatherSoap port = service.getGlobalWeatherSoap();
        return port.getWeather(cityName, countryName);
    }
    
    private static String[] parseXML(Document doc, String tag, String child){
        doc.getDocumentElement().normalize();
        
        NodeList nList = doc.getElementsByTagName(tag);
        
        String[] res = new String[nList.getLength()];
        
        for (int i = 0; i < nList.getLength(); i++) {
            try{
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String elem = eElement.getElementsByTagName(child).item(0).getTextContent();
                    res[i] = elem;
                }
            }catch(Exception e){
                
            }
        }
        
        return res;
    }

    private static String getAirportInformationByCountry(java.lang.String country) {
        net.webservicex.Airport service = new net.webservicex.Airport();
        net.webservicex.AirportSoap port = service.getAirportSoap();
        return port.getAirportInformationByCountry(country);
    }

    private static String getCurrencyByCountry(java.lang.String countryName) {
        net.webservicex.Country service = new net.webservicex.Country();
        net.webservicex.CountrySoap port = service.getCountrySoap();
        return port.getCurrencyByCountry(countryName);
    }

    private static byte[] brailleText(java.lang.String inText, float textFontSize) {
        net.webservicex.Braille service = new net.webservicex.Braille();
        net.webservicex.BrailleSoap port = service.getBrailleSoap();
        return port.brailleText(inText, textFontSize);
    }
    
    private static void convertBytesToImage(String filename, byte[] imageInBytes){
        try {
            InputStream in = new ByteArrayInputStream(imageInBytes);
            BufferedImage bImageFromConvert = ImageIO.read(in);
            ImageIO.write(bImageFromConvert, "jpg", new File(filename+".jpg"));
        } catch(Exception ex){
            System.out.println(ex);
        }
    }
}
