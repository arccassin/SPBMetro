import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 29 Июль, 2019
 */
public class RouteCalculatorTest extends TestCase
{
    List<Station> route;
    List<Station> actualShortestRoute;
    List<Station> expectedShortestRoute;
    StationIndex stationIndex;
    List<Station> connectionStations;
    RouteCalculator routeCalculator;
    Station station1;
    Station station6;

    @Override
    protected void setUp() throws Exception
    {
        route = new ArrayList();
        actualShortestRoute = new ArrayList();
        expectedShortestRoute = new ArrayList();

        Line line1 = new Line(1, "Красная");
        Line line2 = new Line(2, "Синяя");

        station1 = new Station("Нарвская", line1);
        Station station2 = new Station("Балтийская", line1);
        Station station3 = new Station("Технологический институт 1", line1);
        Station station31 = new Station("Пушкинская", line1);
        route.add(station1);
        route.add(station2);
        route.add(station3);
        line1.addStation(station1);
        line1.addStation(station2);
        line1.addStation(station3);
        line1.addStation(station31);

        Station station32 = new Station("Сенная", line2);
        Station station4 = new Station("Технологический институт 2", line2);
        Station station5 = new Station("Фрунзенская", line2);
        station6 = new Station("Московские ворота", line2);
        route.add(station4);
        route.add(station5);
        route.add(station6);
        line2.addStation(station32);
        line2.addStation(station4);
        line2.addStation(station5);
        line2.addStation(station6);

        stationIndex = new StationIndex();
        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addStation(station1);
        stationIndex.addStation(station2);
        stationIndex.addStation(station3);
        stationIndex.addStation(station4);
        stationIndex.addStation(station5);
        stationIndex.addStation(station6);
        connectionStations = new ArrayList<>();
        connectionStations.add(station3);
        connectionStations.add(station4);
        stationIndex.addConnection(connectionStations);

    }

    public void testGetShortestRoute()
    {
        routeCalculator = new RouteCalculator(stationIndex);
        actualShortestRoute = routeCalculator.getShortestRoute(station1, station6);
        assertEquals(route, actualShortestRoute);
//        RouteCalculator routeCalculator = get
//        List<Station> actualRoute = RouteCalculator.getShortestRoute

    }

    public void testCalculateDuration()
    {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 13.5;
        assertEquals(expected, actual);
    }
}
