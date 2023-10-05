
import java.util.*;

public class TitanicScoring {

    private static final double W0 = -0.860123911006183;
    private static final double W1 = -0.9623969687798633;
    private static final double W2 = -1.40968138013041;
    private static final double W3 = -0.205797222931508;
    private static final double W4 = -0.244434412885803;
    private static final double W5 = -0.0760575906961791;
    private static final double W6 = 0.058133354932487;
    private static final double W7 = 0.339992612539595;

    private static Titanic.Class getMissingPClass(Titanic.Passenger passenger) {
        Map<Titanic.Class, Integer> pClassMap = new HashMap<>();
        pClassMap.put(Titanic.Class.FIRST, 0);
        pClassMap.put(Titanic.Class.SECOND, 0);
        pClassMap.put(Titanic.Class.THIRD, 0);
        for (Titanic.Passenger trainPassenger : TitanicTrainingData.passengers) {
            if (trainPassenger.id() != passenger.id() && !trainPassenger.missing(Titanic.Attribute.CLASS)) {
                if (trainPassenger.sex() != Titanic.Sex.UNKNOWN && trainPassenger.sex() == passenger.sex()) {
                    pClassMap.put(trainPassenger.pclass(), pClassMap.get(trainPassenger.pclass()) + 1);
                }
                if (trainPassenger.port() != Titanic.Port.UNKNOWN && trainPassenger.port() == passenger.port()) {
                    pClassMap.put(trainPassenger.pclass(), pClassMap.get(trainPassenger.pclass()) + 1);
                }
                if (trainPassenger.age() != -1 && Math.abs(trainPassenger.age() - passenger.age()) <= 5) {
                    pClassMap.put(trainPassenger.pclass(), pClassMap.get(trainPassenger.pclass()) + 1);
                }
                //siblings, parents, fare
                if (trainPassenger.siblings() != -1 && Math.abs(trainPassenger.siblings() - passenger.siblings()) <= 1) {
                    pClassMap.put(trainPassenger.pclass(), pClassMap.get(trainPassenger.pclass()) + 1);
                }
                if (trainPassenger.parents() != -1 && Math.abs(trainPassenger.parents() - passenger.parents()) <= 1) {
                    pClassMap.put(trainPassenger.pclass(), pClassMap.get(trainPassenger.pclass()) + 1);
                }
                if (trainPassenger.fare() != -1 && Math.abs(trainPassenger.fare() - passenger.fare()) <= 10) {
                    pClassMap.put(trainPassenger.pclass(), pClassMap.get(trainPassenger.pclass()) + 1);
                }
            }
        }

        int maxCount = pClassMap.get(Titanic.Class.SECOND);
        Titanic.Class finalClass = Titanic.Class.SECOND;
        for (Titanic.Class pClass : Titanic.Class.values()) {
            if (pClassMap.get(pClass) > maxCount) {
                maxCount = pClassMap.get(pClass);
                finalClass = pClass;
            }
        }
        return finalClass;
    }

    private static Titanic.Port getMissingPort(Titanic.Passenger passenger) {
        Map<Titanic.Port, Integer> portMap = new HashMap<>();
        portMap.put(Titanic.Port.SOUTHAMPTON, 0);
        portMap.put(Titanic.Port.CHERBOURG, 0);
        portMap.put(Titanic.Port.QUEENSTOWN, 0);
        portMap.put(Titanic.Port.UNKNOWN, 0);
        for (Titanic.Passenger trainPassenger : TitanicTrainingData.passengers) {
            if (trainPassenger.id() != passenger.id() && !trainPassenger.missing(Titanic.Attribute.PORT)) {
                if (trainPassenger.sex() != Titanic.Sex.UNKNOWN && trainPassenger.sex() == passenger.sex()) {
                    portMap.put(trainPassenger.port(), portMap.get(trainPassenger.port()) + 1);
                }
                if (trainPassenger.pclass() != Titanic.Class.UNKNOWN && trainPassenger.pclass() == passenger.pclass()) {
                    portMap.put(trainPassenger.port(), portMap.get(trainPassenger.port()) + 1);
                }
                if (trainPassenger.age() != -1 && Math.abs(trainPassenger.age() - passenger.age()) <= 5) {
                    portMap.put(trainPassenger.port(), portMap.get(trainPassenger.port()) + 1);
                }
                //siblings, parents, fare
                if (trainPassenger.siblings() != -1 && Math.abs(trainPassenger.siblings() - passenger.siblings()) <= 1) {
                    portMap.put(trainPassenger.port(), portMap.get(trainPassenger.port()) + 1);
                }
                if (trainPassenger.parents() != -1 && Math.abs(trainPassenger.parents() - passenger.parents()) <= 1) {
                    portMap.put(trainPassenger.port(), portMap.get(trainPassenger.port()) + 1);
                }
                if (trainPassenger.fare() != -1 && Math.abs(trainPassenger.fare() - passenger.fare()) <= 10) {
                    portMap.put(trainPassenger.port(), portMap.get(trainPassenger.port()) + 1);
                }
            }
        }

        int maxCount = portMap.get(Titanic.Port.SOUTHAMPTON);
        Titanic.Port finalPort = Titanic.Port.SOUTHAMPTON;
        for (Titanic.Port port : Titanic.Port.values()) {
            if (portMap.get(port) > maxCount) {
                maxCount = portMap.get(port);
                finalPort = port;
            }
        }
        return finalPort;
    }

    private static int getMissingAge(Titanic.Passenger passenger) {
        int finalAge = 30;
        int n = 1;
        for (Titanic.Passenger trainPassenger : TitanicTrainingData.passengers) {
            if (trainPassenger.id() != passenger.id() && !trainPassenger.missing(Titanic.Attribute.AGE)) {
                if (trainPassenger.sex() != Titanic.Sex.UNKNOWN && trainPassenger.sex() == passenger.sex()) {
                    finalAge += trainPassenger.age();
                    n++;
                }
                if (trainPassenger.port() != Titanic.Port.UNKNOWN && trainPassenger.port() == passenger.port()) {
                    finalAge += trainPassenger.age();
                    n++;
                }
                if (trainPassenger.pclass() != Titanic.Class.UNKNOWN && trainPassenger.pclass() == passenger.pclass()) {
                    finalAge += trainPassenger.age();
                    n++;
                }
                //siblings, parents, fare
                if (trainPassenger.siblings() != -1 && Math.abs(trainPassenger.siblings() - passenger.siblings()) <= 1) {
                    finalAge += trainPassenger.age();
                    n++;
                }
                if (trainPassenger.parents() != -1 && Math.abs(trainPassenger.parents() - passenger.parents()) <= 2) {
                    finalAge += trainPassenger.age();
                    n++;
                }
                if (trainPassenger.fare() != -1 && Math.abs(trainPassenger.fare() - passenger.fare()) <= 10) {
                    finalAge += trainPassenger.age();
                    n++;
                }
            }
        }

        return finalAge / n;
    }

    private static int getMissingSiblings(Titanic.Passenger passenger) {
        int finalSiblings = 1;
        int n = 1;
        for (Titanic.Passenger trainPassenger : TitanicTrainingData.passengers) {
            if (trainPassenger.id() != passenger.id() && !trainPassenger.missing(Titanic.Attribute.SIBLINGS)) {
                if (trainPassenger.sex() != Titanic.Sex.UNKNOWN && trainPassenger.sex() == passenger.sex()) {
                    finalSiblings += trainPassenger.siblings();
                    n++;
                }
                if (trainPassenger.port() != Titanic.Port.UNKNOWN && trainPassenger.port() == passenger.port()) {
                    finalSiblings += trainPassenger.siblings();
                    n++;
                }
                if (trainPassenger.pclass() != Titanic.Class.UNKNOWN && trainPassenger.pclass() == passenger.pclass()) {
                    finalSiblings += trainPassenger.siblings();
                    n++;
                }
                //siblings, parents, fare
                if (trainPassenger.age() != -1 && Math.abs(trainPassenger.age() - passenger.age()) <= 5) {
                    finalSiblings += trainPassenger.siblings();
                    n++;
                }
                if (trainPassenger.parents() != -1 && Math.abs(trainPassenger.parents() - passenger.parents()) <= 1) {
                    finalSiblings += trainPassenger.siblings();
                    n++;
                }
                if (trainPassenger.fare() != -1 && Math.abs(trainPassenger.fare() - passenger.fare()) <= 10) {
                    finalSiblings += trainPassenger.siblings();
                    n++;
                }
            }
        }

        return finalSiblings / n;
    }

    private static int getMissingParents(Titanic.Passenger passenger) {
        int finalParents = 0;
        int n = 1;
        for (Titanic.Passenger trainPassenger : TitanicTrainingData.passengers) {
            if (passenger.id() != trainPassenger.id() && !trainPassenger.missing(Titanic.Attribute.PARENTS)) {
                if (trainPassenger.sex() != Titanic.Sex.UNKNOWN && trainPassenger.sex() == passenger.sex()) {
                    finalParents += trainPassenger.parents();
                    n++;
                }
                if (trainPassenger.port() != Titanic.Port.UNKNOWN && trainPassenger.port() == passenger.port()) {
                    finalParents += trainPassenger.parents();
                    n++;
                }
                if (trainPassenger.pclass() != Titanic.Class.UNKNOWN && trainPassenger.pclass() == passenger.pclass()) {
                    finalParents += trainPassenger.parents();
                    n++;
                }
                //siblings, parents, fare
                if (trainPassenger.age() != -1 && Math.abs(trainPassenger.age() - passenger.age()) <= 5) {
                    finalParents += trainPassenger.parents();
                    n++;
                }
                if (trainPassenger.parents() != -1 && Math.abs(trainPassenger.siblings() - passenger.siblings()) <= 1) {
                    finalParents += trainPassenger.parents();
                    n++;
                }
                if (trainPassenger.fare() != -1 && Math.abs(trainPassenger.fare() - passenger.fare()) <= 10) {
                    finalParents += trainPassenger.parents();
                    n++;
                }
            }
        }

        return finalParents / n;
    }

    private static double getMissingFare(Titanic.Passenger passenger) {
        double finalFare = 33.04;
        int n = 1;
        for (Titanic.Passenger trainPassenger : TitanicTrainingData.passengers) {
            if (passenger.id() != trainPassenger.id() && !trainPassenger.missing(Titanic.Attribute.FARE)) {
                if (trainPassenger.sex() != Titanic.Sex.UNKNOWN && trainPassenger.sex() == passenger.sex()) {
                    finalFare += trainPassenger.fare();
                    n++;
                }
                if (trainPassenger.port() != Titanic.Port.UNKNOWN && trainPassenger.port() == passenger.port()) {
                    finalFare += trainPassenger.fare();
                    n++;
                }
                if (trainPassenger.pclass() != Titanic.Class.UNKNOWN && trainPassenger.pclass() == passenger.pclass()) {
                    finalFare += trainPassenger.fare();
                    n++;
                }
                //siblings, parents, fare
                if (trainPassenger.age() != -1 && Math.abs(trainPassenger.age() - passenger.age()) <= 5) {
                    finalFare += trainPassenger.fare();
                    n++;
                }
                if (trainPassenger.parents() != -1 && Math.abs(trainPassenger.siblings() - passenger.siblings()) <= 1) {
                    finalFare += trainPassenger.fare();
                    n++;
                }
                if (trainPassenger.fare() != -1 && Math.abs(trainPassenger.parents() - passenger.parents()) <= 1) {
                    finalFare += trainPassenger.siblings();
                    n++;
                }
            }
        }

        return finalFare / n;
    }

    public static double hypothesis(Titanic.Passenger passenger) {

        // Bias, Class, Age, SibSp, ParCh, Sex
        double[] weightsV = new double[]{-1.8924250047, -2.1502751734, -0.5183133769, -.4071039077, -0.4496445941, -2.0445300930};

        // Class, Age, SibSp, ParCh, Sex
        double[] averages = new double[]{2.2291993721, 29.8711459969, 0.5227629513, 0.4285714286, 0.6342229199};
        double[] stdevs = new double[]{0.8397896671, 14.5443008389, 0.9324006491, 0.8541426979, 0.4820258943};

        // Missing Data (fills with average for entire dataset)
        double pclass = passenger.missing(Titanic.Attribute.CLASS) ? averages[0] : passenger.pclass().ordinal() + 1;
        double age = passenger.missing(Titanic.Attribute.AGE) ? averages[1] : passenger.age();
        double sibSp = passenger.missing(Titanic.Attribute.SIBLINGS) ? averages[2] : passenger.siblings();
        double parCh = passenger.missing(Titanic.Attribute.PARENTS) ? averages[3] : passenger.parents();
        double sex = passenger.missing(Titanic.Attribute.SEX) ? averages[4] : (passenger.sex().ordinal() + 1) % 2;

        // Normalization
        double[] attributes = new double[]{1, pclass, age, sibSp, parCh, sex};
        for (int i = 1; i < attributes.length; i++) {
            attributes[i] = (attributes[i] - averages[i - 1]) / stdevs[i - 1];
        }

        double z = 0;
        for (int i = 0; i < weightsV.length; i++) z += weightsV[i] * attributes[i];

        return 1 / (1 + Math.exp(-z));
    }

    public static boolean log1(Titanic.Passenger passenger) {
        return hypothesis(passenger) > 0.55;
    }

    public static double[] weights = {
            1.62315113405381, 1.44111870627265, 1.3847255728576,
            3.15069090190793, 0.523948538891192, 0.303170224201001, 0.305365208975262
    };


    public static boolean log2(Titanic.Passenger passenger) {

        double normPClass = 0.0;

        switch (passenger.pclass()) {
            case FIRST:
                normPClass = 1;
                break;
            case SECOND:
                normPClass = 2;
                break;
            case THIRD:
                normPClass = 3;
                break;
            case UNKNOWN:
                System.out.println("uh oh");
                break;
        }
        normPClass = (normPClass - 2.2291993721) / 0.8397896671;

        double normSex = 0.0;

        switch (passenger.sex()) {
            case MALE:
                normSex = 1;
                break;
            case FEMALE:
                normSex = 0;
                break;
            case UNKNOWN:
                normSex = 1;
        }
        normSex = (normSex - 0.634222919937206) / 0.482025894332642;

        double normAge = (passenger.age() - 29.8711459968603) / 14.5443008388913;

        double normSibSp = (passenger.siblings() - 0.5227629513) / 0.9324006491;

        double normParCh = (passenger.parents() - 0.4285714286) / 0.8541426979;

        double normFare = (passenger.fare() - 35.9516742543171) / 55.059240047363;

        double XdotW = weights[0] + weights[1] * normPClass + weights[2] * normAge
                + weights[3] * normSex + weights[4] * normSibSp + weights[5] * normParCh
                + weights[6] * normFare;

        double logRegression = 1 / (1 + Math.exp(-1 * XdotW));

        return logRegression > 0.5;
    }

    public static int pluralityAgeGivenSex(Titanic.Passenger p) {
        List<Integer> passengerArrayList = new ArrayList<>();
        for (Titanic.Passenger passenger : TitanicTestingData.passengers) {
            if (!passenger.sex().equals(Titanic.Sex.UNKNOWN)) {
                if (passenger.sex().equals(p.sex())) {
                    int age = (int) passenger.age();
                    passengerArrayList.add(age);
                }
            }
        }
        Integer[] passengerArray = passengerArrayList.toArray(new Integer[0]);
        return mode(passengerArray);
    }

    public static Titanic.Class fareClassRegression(double fare) {

        if (fare < 0) return Titanic.Class.THIRD;

        int pclass = (int) Math.round(-0.0090808629297 * fare + 3);

        if (pclass == 1) return Titanic.Class.FIRST;
        else if (pclass == 2) return Titanic.Class.SECOND;
        else return Titanic.Class.THIRD;
    }

    public static int mode(Integer[] ints) {
        int maxValue = 0;
        int maxCount = 0;
        for (Integer anInt : ints) {
            int count = 0;
            for (Integer integer : ints) {
                if (integer.equals(anInt) && integer != -1)
                    ++count; //-1 check because the plurality is the missing data
            }
            if (count > maxCount) {
                maxCount = count;
                maxValue = anInt;
            }
        }

        return maxValue;
    }

    public static Titanic.Sex pluralityVoteForSex() {
        List<Titanic.Sex> passengerArrayList = new ArrayList<>();
        for (Titanic.Passenger passenger : TitanicTestingData.passengers) {
            if (!passenger.sex().equals(Titanic.Sex.UNKNOWN))
                passengerArrayList.add(passenger.sex());
        }

        Titanic.Sex[] passengerArray = (Titanic.Sex[]) passengerArrayList.toArray();

        return mode(passengerArray);
    }

    public static Titanic.Sex mode(Titanic.Sex[] sexes) {
        Titanic.Sex maxValue = null;
        int maxCount = 0;
        for (Titanic.Sex sex : sexes) {
            int count = 0;
            for (Titanic.Sex value : sexes) {
                if (value == sex) ++count; //-1 check because the plurality is the missing data
            }
            if (count > maxCount) {
                maxCount = count;
                maxValue = sex;
            }
        }

        return maxValue;
    }

    public static int pluralitySiblingsGivenSexAndAge(Titanic.Sex sex) {
        List<Integer> passengerArrayList = new ArrayList<>();
        for (Titanic.Passenger passenger : TitanicTestingData.passengers) {
            if (!passenger.sex().equals(Titanic.Sex.UNKNOWN) && passenger.age() != -1) { //ignore missing data
                if (passenger.sex().equals(sex) && passenger.age() > 13) {
                    int siblings = passenger.siblings();
                    passengerArrayList.add(siblings);
                }
            }
        }

        Integer[] passengerArray = passengerArrayList.toArray(new Integer[0]);

        return mode(passengerArray);
    }

    public static boolean decisionTree1(Titanic.Passenger passenger) { //TO DO

        Titanic.Sex passengerSex = passenger.sex(); //Sex variable
        if (passengerSex.equals(Titanic.Sex.UNKNOWN)) {
            passengerSex = pluralityVoteForSex();
        }

        if (passengerSex.equals(Titanic.Sex.MALE)) {
            //males

            double passengerAge = passenger.age();
            if (passengerAge == -1) { //age is unknown
                passengerAge = pluralityAgeGivenSex(passenger);
            }

            Titanic.Class passengerClass = passenger.pclass();
            if (passengerClass.equals(Titanic.Class.UNKNOWN)) {
                passengerClass = fareClassRegression(passenger.fare());
            }

            int passengerSibilings = passenger.siblings();
            if (passengerSibilings == -1) {
                passengerSibilings = pluralitySiblingsGivenSexAndAge(Titanic.Sex.MALE);
            }

            if (passengerAge >= 13.0) {
                if (passengerClass.equals(Titanic.Class.SECOND) || passengerClass.equals(Titanic.Class.THIRD)) {
                    return false;
                } else if (passengerClass.equals(Titanic.Class.FIRST)) {
                    if (passengerAge >= 36) {
                        return false;
                    } else return !(passengerAge < 30);
                }

            } else {

                //male child
                //male child with few siblings/spouses
                //male child with a lot of siblings
                return passengerSibilings < 2;
            }
        } else if (passengerSex.equals(Titanic.Sex.FEMALE)) {


            // Missing Data Variables
            Titanic.Class passengerClass = passenger.pclass();
            double passengerAge = passenger.age();
            int passengerParents = passenger.parents();
            Titanic.Port passengerPort = passenger.port();
            int passengerSiblings = passenger.siblings();


            if (passengerClass.equals(Titanic.Class.UNKNOWN))
                passengerClass = fareClassRegression(passenger.fare()); // Runs linear regression in the case of missing class
            switch (passengerClass) {

                case FIRST: {
                    // 79 out of 82 passengers survive here
                    return (int) (Math.random() * 82) < 79;
                }

                case SECOND: {

                    if (passengerAge < 0) passengerAge = 28.36; // Average age of second class females

                    if (passengerAge <= 23) return true;

                    if (passengerParents < 0) passengerParents = 1; // Average parCh for second class females over 23

                    if (passengerParents >= 2) return true;

                    return (int) (Math.random() * 41) < 36;

                }
                case THIRD: {


                    if (passengerPort.equals(Titanic.Port.UNKNOWN))
                        passengerPort = Titanic.Port.SOUTHAMPTON;// Most popular port for third class females

                    switch (passengerPort) {

                        case QUEENSTOWN: {

                            if (passengerAge < 0)
                                passengerAge = 21.05; // Average age of third class females from Queenstown

                            if (passengerAge >= 23) return false;

                            return (int) (Math.random() * 7) < 5;

                        }

                        case CHERBOURG: {

                            if (passengerAge < 0)
                                passengerAge = 13.5; // Average age of third class females from Cherbourg;

                            if (passengerAge <= 14) return true;

                            return (int) (Math.random() * 5) < 1;


                        }

                        case SOUTHAMPTON: {

                            if (passengerSiblings < 0)
                                passengerSiblings = 1; // Average sibSp for third class females from Southampton

                            switch (passengerSiblings) {

                                case 0: {

                                    if (passengerAge < 0)
                                        passengerAge = 25; // Average age for third class females from Southampton with no sibSp

                                    if (passengerAge < 28) return (int) (Math.random() * 22) < 22;

                                    return (int) (Math.random() * 11) < 3;
                                }

                                case 1: {
                                    return (int) (Math.random() * 23) < 8;
                                }

                                default: {
                                    return (int) (Math.random() * 19) < 3;
                                }
                            }
                        }
                    }
                }
            }
        }
        //should never be here

        return false;
    }

    public static Titanic.Passenger setPassengerMissingData(Titanic.Passenger passenger) {
        if (passenger.missing(Titanic.Attribute.CLASS)) {
            passenger = new Titanic.Passenger(passenger.id(), passenger.name(), passenger.survived(), passenger.port(), TitanicScoring.getMissingPClass(passenger), passenger.sex(),
                    passenger.age(), passenger.siblings(), passenger.parents(), passenger.fare());
        }

        if (passenger.missing(Titanic.Attribute.PORT)) {
            passenger = new Titanic.Passenger(passenger.id(), passenger.name(), passenger.survived(), TitanicScoring.getMissingPort(passenger), passenger.pclass(), passenger.sex(),
                    passenger.age(), passenger.siblings(), passenger.parents(), passenger.fare());
        }
        if (passenger.missing(Titanic.Attribute.AGE)) {
            passenger = new Titanic.Passenger(passenger.id(), passenger.name(), passenger.survived(), passenger.port(), passenger.pclass(), passenger.sex(),
                    TitanicScoring.getMissingAge(passenger), passenger.siblings(), passenger.parents(), passenger.fare());
        }
        if (passenger.missing(Titanic.Attribute.SIBLINGS)) {
            passenger = new Titanic.Passenger(passenger.id(), passenger.name(), passenger.survived(), passenger.port(), passenger.pclass(), passenger.sex(),
                    passenger.age(), TitanicScoring.getMissingSiblings(passenger), passenger.parents(), passenger.fare());
        }
        if (passenger.missing(Titanic.Attribute.PARENTS)) {
            passenger = new Titanic.Passenger(passenger.id(), passenger.name(), passenger.survived(), passenger.port(), passenger.pclass(), passenger.sex(),
                    passenger.age(), passenger.siblings(), TitanicScoring.getMissingParents(passenger), passenger.fare());
        }
        if (passenger.missing(Titanic.Attribute.FARE)) {
            passenger = new Titanic.Passenger(passenger.id(), passenger.name(), passenger.survived(), passenger.port(), passenger.pclass(), passenger.sex(),
                    passenger.age(), passenger.siblings(), passenger.parents(), TitanicScoring.getMissingFare(passenger));
        }

        return passenger;
    }

    public static boolean decisionTree2(Titanic.Passenger passenger) {
        passenger = setPassengerMissingData(passenger);
        if (passenger.sex() == Titanic.Sex.FEMALE) {
            if (passenger.pclass() == Titanic.Class.FIRST) {

                if (passenger.age() == TitanicTrainingData.NA) {

                }

                if (passenger.age() < 10) {
                    return true;
                } else if (passenger.age() >= 10) {
                    if (passenger.fare() >= 50) {
                        if (passenger.parents() >= 0 && passenger.parents() <= 1) {
                            return false;
                        } else if (passenger.parents() == 2) {
                            if (passenger.siblings() >= 0 && passenger.siblings() <= 3 && passenger.siblings() != 1) {
                                return false;
                            } else if (passenger.siblings() == 1) {
                                return false;
                            } else return passenger.siblings() > 4;
                        } else return passenger.parents() > 3;
                    } else if (passenger.fare() < 50) {
                        if (passenger.port() == Titanic.Port.SOUTHAMPTON) {
                            return false;
                        } else if (passenger.port() == Titanic.Port.CHERBOURG) {
                            return false;
                        } else return passenger.port() == Titanic.Port.QUEENSTOWN;
                    }
                }

            } else if (passenger.pclass() == Titanic.Class.SECOND) {

                if (passenger.parents() == 2 || passenger.parents() == 3) {
                    return false;
                } else if (passenger.parents() >= 4 && passenger.parents() <= 6) {
                    return true;
                } else if (passenger.parents() == 0) {
                    if (passenger.port() == Titanic.Port.SOUTHAMPTON) {
                        return false;
                    } else if (passenger.port() == Titanic.Port.CHERBOURG || passenger.port() == Titanic.Port.QUEENSTOWN) {
                        return false;
                    }
                } else if (passenger.parents() == 1) {
                    if (passenger.siblings() >= 0 && passenger.siblings() <= 2 && passenger.siblings() != 1) {
                        return false;
                    } else if (passenger.siblings() == 1) {
                        return false;
                    } else return passenger.siblings() > 2;
                }

            } else if (passenger.pclass() == Titanic.Class.THIRD) {

                if (passenger.age() >= 10) {
                    if (passenger.siblings() == 0) {
                        if (passenger.parents() == 0) {

                            if (passenger.port() == Titanic.Port.SOUTHAMPTON) {
                                if (passenger.fare() >= 50) {
                                    return true;
                                } else if (passenger.fare() < 50) {
                                    return false;
                                }
                            } else if (passenger.port() == Titanic.Port.CHERBOURG) {
                                return false;
                            } else return passenger.port() == Titanic.Port.QUEENSTOWN;


                        } else if (passenger.parents() == 1 || passenger.parents() == 5) {
                            return false; //50/50
                        } else return passenger.parents() == 2 || passenger.parents() == 3 || passenger.parents() == 4 || passenger.parents() == 6;
                    } else if (passenger.siblings() == 1) {
                        if (passenger.parents() == 0) {

                            if (passenger.port() == Titanic.Port.SOUTHAMPTON) {
                                if (passenger.fare() >= 50) {
                                    return true;
                                } else if (passenger.fare() < 50) {
                                    return false;
                                }
                            } else if (passenger.port() == Titanic.Port.CHERBOURG) {
                                return true;
                            } else return passenger.port() == Titanic.Port.QUEENSTOWN;


                        } else if (passenger.parents() == 1 || passenger.parents() == 5) {
                            return true;
                        } else return passenger.parents() == 2 || passenger.parents() == 3 || passenger.parents() == 4 || passenger.parents() == 6;
                    } else if (passenger.siblings() == 2 || passenger.siblings() == 5 || passenger.siblings() == 8) {
                        return true;
                    } else if (passenger.siblings() == 3) {
                        return false;
                    } else return passenger.siblings() == 4;
                } else if (passenger.age() < 10) {
                    if (passenger.siblings() == 0) {
                        if (passenger.parents() == 0 || passenger.parents() == 2) {
                            return false;
                        } else if (passenger.parents() == 1) {
                            return false;
                        } else return passenger.parents() > 3;
                    } else if (passenger.siblings() == 2) {
                        if (passenger.port() == Titanic.Port.SOUTHAMPTON || passenger.port() == Titanic.Port.QUEENSTOWN) {
                            return true;
                        } else if (passenger.port() == Titanic.Port.CHERBOURG) {
                            return false;
                        }
                    } else if (passenger.siblings() == 3 || passenger.siblings() == 5 || passenger.siblings() == 8) {
                        return true;
                    } else if (passenger.siblings() == 1) {
                        return false;
                    } else if (passenger.siblings() == 4) {
                        if (passenger.port() == Titanic.Port.CHERBOURG || passenger.port() == Titanic.Port.QUEENSTOWN) {
                            return true;
                        } else return passenger.port() == Titanic.Port.SOUTHAMPTON;
                    }
                }

            } else if (passenger.pclass() == Titanic.Class.UNKNOWN) {

            }
        } else if (passenger.sex() == Titanic.Sex.MALE) {
            if (passenger.pclass() == Titanic.Class.FIRST) {
                if (passenger.age() >= 50) {
                    if (passenger.siblings() == 0 || passenger.siblings() > 2 || passenger.siblings() == 1) {
                        return true;
                    } else if (passenger.siblings() == 2) {
                        return false;
                    }
                } else if (passenger.age() < 50) {
                    if (passenger.age() >= 10) {
                        if (passenger.siblings() == 0) {
                            return true; //50/50
                        } else if (passenger.siblings() == 1) {
                            return false; //50/50
                        } else return passenger.siblings() > 1;
                    } else if (passenger.age() < 10) {
                        return false;
                    }
                }
            } else if (passenger.pclass() == Titanic.Class.SECOND) {
                if (passenger.age() >= 10) {
                    if (passenger.age() >= 30) {
                        return true;
                    } else return passenger.age() < 30;
                } else if (passenger.age() < 10) {
                    return false;
                }
            } else if (passenger.pclass() == Titanic.Class.THIRD) {
                if (passenger.fare() >= 50) {
                    if (passenger.siblings() == 0) {
                        return false;
                    } else return passenger.siblings() > 0;
                } else return passenger.fare() < 50;
            }
        }

        return false;
    }

    public static boolean log3(Titanic.Passenger passenger) {

        passenger = setPassengerMissingData(passenger);

        int sexValue = -1;
        int embarkedValue = 1;

        if (passenger.sex() == Titanic.Sex.FEMALE) {
            sexValue = -1;
        } else {
            sexValue = 1;
        }

        if (passenger.port() == Titanic.Port.CHERBOURG) {
            embarkedValue = 2;
        } else if (passenger.port() == Titanic.Port.SOUTHAMPTON) {
            embarkedValue = 1;
        } else if (passenger.port() == Titanic.Port.QUEENSTOWN) {
            embarkedValue = 3;
        }

        double linearFunctionValue = W1 * ((passenger.pclass().ordinal() + 1 - 2.305)/0.836869427119643) +
                W2 * ((sexValue - 0.2925)/0.956863749597673) +
                W3 * ((passenger.age() - 29.8711459968603)/14.5443008388913) +
                W4 * ((passenger.siblings() - 0.51875)/1.06351411148685) +
                W5 * ((passenger.parents() - 0.37375)/0.801476070987436) +
                W6 * ((passenger.fare() - 33.03853575)/51.5249501775987) +
                W7 * ((embarkedValue - 1.37125)/0.647261246639834) + W0;


        double logisticFunction = 1.0/(1.0 + Math.exp(-1 * linearFunctionValue));

        double lambdaConstant = 2.00;

        return logisticFunction > 0.50;
    }

    public static boolean fourOfFive(boolean[] survived){
        int trueCount = 0;
        int falseCount = 0;

        for (int i = 0; i < survived.length; i++){
            if (survived[i] == true){
                trueCount++;
            }
            else{
                falseCount++;
            }
        }
        return trueCount >= 4 || falseCount >= 4;
    }

    private static class BooleanAndIntPair{
        private final boolean survived;
        private final int id;

        public BooleanAndIntPair(boolean b, int i){
            this.id = i;
            this.survived = b;
        }
    }

    public static BooleanAndIntPair[] confidentPassengers(Titanic.Passenger passenger){
       ArrayList<BooleanAndIntPair> passengerIDs = new ArrayList<>();

        boolean[] preds = new boolean[5];
        preds[0] = log1(passenger);
        preds[1] = decisionTree1(passenger);
        preds[2] = log2(passenger);
        preds[3] = decisionTree2(passenger);
        preds[4] = log3(passenger);

        boolean survived = mode(preds);

        if(fourOfFive(preds)){
            passengerIDs.add(new BooleanAndIntPair(survived, passenger.id()));
        }


        BooleanAndIntPair[] BooleanAndIntPair = new BooleanAndIntPair[0];
        BooleanAndIntPair[] result = passengerIDs.toArray(BooleanAndIntPair);

        return result;

    }

    public static boolean survived(Titanic.Passenger passenger) {
        boolean[] preds = new boolean[3];
        preds[0] = log1(passenger);
        preds[1] = decisionTree1(passenger);
        preds[2] = log2(passenger);
        preds[3] = decisionTree2(passenger);
        preds[4] = log3(passenger);

        return mode(preds);
    }

    public static boolean mode(boolean[] b){
        int trueCount = 0;
        int falseCount = 0;

        for (int i = 0; i < b.length; i++){
            if (b[i]){ //if index is true
                trueCount++;
            }
            else if(!b[i]){ //if index is false
                falseCount++;
            }
            else{
                System.out.println("weird sized array thing");
            }
        }
        //the mode
        if ((max(trueCount, falseCount)) == trueCount){
            return true;
        }
        else if ((max(trueCount, falseCount)) == falseCount){
            return false;
        }
        else{
            System.out.println("error");
            return false;
        }
    }

    public static int max(int a, int b){
        if (a > b){
            return a;
        }
        else if(b>a){
            return b;
        }
        else{
            System.out.println("no max - values are equal");
            return -1;
        }
    }

    public static void main(String[] args) {
        if (args[0].equals("-train")) {
            int c = 0;
            for (Titanic.Passenger passenger : TitanicTrainingData.passengers) {
                try {
                    boolean survived = survived(passenger);
                    if (survived == passenger.survived()) {
                        c++;
                    }
                    System.out.println(passenger.id() + "," + survived);
                } catch (Exception e) {
                    System.out.println(passenger.id() + ",null");
                }
            }
            System.out.println(c);
            System.out.println("percent correct: " + (c * 100 / 800) + "%");
        } else {
            for (Titanic.Passenger passenger : TitanicTestingData.passengers) {
                try {
                    boolean survived = survived(passenger);

                    System.out.println(passenger.id() + "," + survived);
                } catch (Exception e) {
                    System.out.println(passenger.id() + ",null");
                }
            }
        }
    }

}