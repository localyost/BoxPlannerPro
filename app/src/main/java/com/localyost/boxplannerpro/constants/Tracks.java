package com.localyost.boxplannerpro.constants;

import com.localyost.boxplannerpro.remote.data.CrossfitClass;

public class Tracks {

    private final static String ENDURANCE = "2f865811-32a1-4302-9ae1-2742b611943c";
    private final static String CROSSFIT = "38e1eac9-cfd7-45ee-ac0f-439b3dba96b1";
    private final static String WEIGHTLIFTING = "5cb795bc-a2b6-4f34-befa-3c919fb7f7a1";
    private final static String OPEN_GYM = "6b40b247-8c1d-4954-8d6a-a38dc9a3aa0d";
    private final static String GYMNASTICS = "fc341b8c-1c64-4790-9315-efd9d448f19f";

    public static boolean isOpenGym(CrossfitClass crossfitClass) {
        return crossfitClass.getTrackId().equals(OPEN_GYM);
    }
    public static boolean isEnduranceClass(CrossfitClass crossfitClass) {
        return crossfitClass.getTrackId().equals(ENDURANCE);
    }

    public static boolean isCrossfitClass(CrossfitClass crossfitClass) {
        return crossfitClass.getTrackId().equals(CROSSFIT);
    }

    public static boolean isWeightliftingClass(CrossfitClass crossfitClass) {
        return crossfitClass.getTrackId().equals(WEIGHTLIFTING);
    }

    public static boolean isGymnasticsClass(CrossfitClass crossfitClass) {
        return crossfitClass.getTrackId().equals(GYMNASTICS);
    }
}
