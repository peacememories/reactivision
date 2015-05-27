import processing.core.PVector;

import java.util.HashSet;

/**
 * Created by moru on 5/27/2015.
 */

import java.util.HashSet;
import java.util.Collections;
import java.util.Set;

public class FacilitiesStore {

    private static FacilitiesStore store;
    public static FacilitiesStore getStore() {
        if(store == null) {
            store = new FacilitiesStore();
        }
        return store;
    }

    private Set<Facility> facilities;

    private FacilitiesStore() {
        facilities = new HashSet<>();

        //TODO replace dummy facilities
        Facility f1 = new Facility();
        f1.type = FacilityType.CLUB;
        f1.radius = 130;
        //f1.radius = 630;
        f1.position = new PVector(Conf.SCREEN_WIDTH * 3 / 4, Conf.SCREEN_HEIGHT * 3 / 4);
        facilities.add(f1);

        Facility f2 = new Facility();
        f2.type = FacilityType.SPORT;
        f2.radius = 110;
        //f2.radius = 310;
        f2.position = new PVector(Conf.SCREEN_WIDTH / 4, Conf.SCREEN_HEIGHT / 4);
        facilities.add(f2);
    }
    public Set<Facility> getFacilities() {
        return Collections.unmodifiableSet(facilities);
    }

}

