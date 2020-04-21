package com.example.sucursalapp.components;

import com.example.sucursalapp.entities.PointVO;
import org.springframework.stereotype.Component;

@Component
public class DistanceCalculator {

    public static final int EARTH_RADIUS = 6371;

    public double calculateDistance(PointVO pa, PointVO pb){
        double rLatA = Math.toRadians(pa.getLatitude());
        double sinLatA = Math.sin(rLatA);
        double cosLatA = Math.cos(rLatA);

        double rLatB = Math.toRadians(pb.getLatitude());
        double sinLatB = Math.sin(rLatB);
        double cosLatB = Math.cos(rLatB);

        double rDifLong = Math.toRadians( pb.getLongitude() - pa.getLongitude() );
        double cosDifLong = Math.cos(rDifLong);

        double distance =  EARTH_RADIUS * Math.acos( (sinLatB * sinLatA) + (cosDifLong * cosLatB * cosLatA) );
        return distance;
    }
}
