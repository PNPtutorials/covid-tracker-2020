package com.covid.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.*;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Component
@Controller
@RequestMapping("/")
public class CovidController {

    public Object hitAPI(String url) {
        System.out.println("URL is: " + url);
        RestTemplate restTemplate = new RestTemplate();

        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("x-rapidapi-key", "a9xxxx9e213xxxx3333313xxaep1232xxxxx2c6bxxxxxx7xxx");
        headers.set("x-rapidapi-host", "covid-19-data.p.rapidapi.com");

        // build the request
        HttpEntity request = new HttpEntity(headers);

        // make an HTTP GET request with headers
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                String.class,
                2
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Request Successful.");
            System.out.println(response.getBody());
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
        return response.getBody();
    }

    public ModelAndView setModel(String jspFileName, List<JSONObject> response) {
        ModelAndView mv = new ModelAndView(jspFileName);
        mv.addObject("result", response);
        return mv;
    }

    public List<JSONObject> convertResponseIntoJsonObject(String stringResponse) {
        List<JSONObject> listOfJSONObject = new ArrayList<JSONObject>();
        JSONArray jsonArray = new JSONArray(stringResponse);
        for(int i=0; i < jsonArray.length(); i++) {
            listOfJSONObject.add((JSONObject) jsonArray.get(i));
        }
        System.out.println("listOfJSONObject is: " + listOfJSONObject);
        return listOfJSONObject;
    }

    @RequestMapping(value = "/getDataByCountryName")
    public ModelAndView getDataByCountry(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Inside getDataByCountry");
        String countryName = request.getParameter("countryName");
        System.out.print("country Name is: " + countryName);
        final String url = "https://covid-19-data.p.rapidapi.com/country"+"?name="+countryName;
        Object result = hitAPI(url);
        List<JSONObject> returnedList = convertResponseIntoJsonObject((String) result);
        ModelAndView modelAndView = setModel("dataReturnedFromServer.jsp", returnedList);
        System.out.println(result.toString());
        return modelAndView;
    }

    @RequestMapping(value = "/getDataOfAllCountries")
    public ModelAndView getDataOfAllCountries(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Inside getDataOfAllCountries");
        final String url = "https://covid-19-data.p.rapidapi.com/country/all";
        Object result = (String) hitAPI(url);
        List<JSONObject> returnedList = convertResponseIntoJsonObject((String) result);
        ModelAndView modelAndView = setModel("dataReturnedFromServer.jsp", returnedList);
        System.out.println(result.toString());
        return modelAndView;
    }
}
