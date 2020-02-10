package startApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;
import sun.misc.ClassLoaderUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainClass {

    public static void main(String[] args) {
        //ObjectMapper to convert text file to Object as the text format is written like a json format
        ObjectMapper mapper = new ObjectMapper();
        MainClass mainClass = new MainClass();
        try {
            File jsonFile = new File("src/main/resources/CandidateInputExample.txt");
            InputModel mod = mapper.readValue(jsonFile, InputModel.class);
            List<TapsInformations> taps = mod.getTaps();

            //Sort the list by CustomerId
            taps.sort(Comparator.comparing(TapsInformations::getCustomerId));

            //Groupping collection by customerId returning a map with customerIds as key an list of taps as value
            Map<Integer, List<TapsInformations>> customerIds = taps.stream().collect(Collectors.groupingBy(t -> t.getCustomerId()));

            OutputModel outputModel = new OutputModel();
            List<CustomerSummaries> summariesList = new ArrayList<>();

            //looping through the map
            customerIds.entrySet().stream().forEach(integerListEntry -> {
                Integer key = integerListEntry.getKey();
                List<TapsInformations> tapsInfo = integerListEntry.getValue();
                CustomerSummaries customerSummaries = new CustomerSummaries();
                customerSummaries.setCustomerId(key);
                int zoneFrom = findZoneByGivenStation(tapsInfo.get(0).getStation());
                int zoneTo = findZoneByGivenStation(tapsInfo.get(tapsInfo.size() - 1).getStation());
                int price = findPriceByZone(zoneFrom, zoneTo);

                List<Trips> tripsList = new ArrayList<>();
                List<Integer> event = evenNumber(tapsInfo.size());
                //looping through list of taps
                for (int i = 0; i < tapsInfo.size() / 2; i++) {
                    int counter = event.get(i);
                    Trips trips = new Trips();
                    trips.setStationStart(tapsInfo.get(counter - 2).getStation());
                    trips.setStationEnd(tapsInfo.get(counter - 1).getStation());
                    trips.setStartedJourneyAt(tapsInfo.get(0).getUnixTimestamp());
                    trips.setZoneFrom(zoneFrom);
                    trips.setZoneTo(zoneTo);
                    trips.setCostInCents(price);
                    tripsList.add(trips);
                }
                int sumPrice = 0;
                for (int i = 0; i < tripsList.size(); i++) {
                    sumPrice = sumPrice + tripsList.get(i).getCostInCents();
                }
                customerSummaries.setTotalCostInCents(sumPrice);
                customerSummaries.setTrips(tripsList);
                summariesList.add(customerSummaries);
            });
            outputModel.setCustomerSummaries(summariesList);
            mapper.writeValue(new File("src/main/resources/CandidateOutput.json"), outputModel);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Find Zone by a given station
     *
     * @param station
     * @return zone
     */
    public static int findZoneByGivenStation(String station) {
        int zone = 0;
        if (station.equals("A") || station.equals("B")) zone = 1;
        else if (station.equals("C") || station.equals("D") || station.equals("E")) zone = 2;
        else if (station.equals("F") || station.equals("G")) zone = 3;
        else zone = 4;
        return zone;
    }

    //

    /**
     * example of some prices by from a Zone to another
     *
     * @param zoneFrom
     * @param zoneTo
     * @return price
     */
    private static int findPriceByZone(int zoneFrom, int zoneTo) {
        int price = 0;
        if (zoneFrom == 1 && zoneTo == 2) price = 240;
        else if (zoneFrom == 3 && zoneTo == 4) price = 200;
        else if (zoneFrom == 3 && (zoneTo == 1 || zoneTo == 2)) price = 280;
        else if (zoneFrom == 4 && (zoneTo == 1 || zoneTo == 2)) price = 300;
        else if ((zoneFrom == 1 || zoneFrom == 2) && zoneTo == 3) price = 280;
        else if ((zoneFrom == 1 || zoneFrom == 2) && zoneTo == 4) price = 300;
        return price;
    }

    /**
     * calculate the pair numbers to iterate in the list values of the map
     *
     * @param number
     * @return event
     */
    private static List<Integer> evenNumber(int number) {
        List<Integer> event = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            if (i % 2 == 0) event.add(i);
        }
        return event;
    }

}
