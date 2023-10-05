//
//import java.util.*;
//
//public class TitanicScoring1 {
//
//    private static double W0 = -0.860123911006183;
//    private static double W1 = -0.9623969687798633;
//    private static double W2 = -1.40968138013041;
//    private static double W3 = -0.205797222931508;
//    private static double W4 = -0.244434412885803;
//    private static double W5 = -0.0760575906961791;
//    private static double W6 = 0.058133354932487;
//    private static double W7 = 0.339992612539595;
//
//    private static int knnThreshold = 5;
//
//    private static DistanceFunc func = DistanceFunc.L0;
//
//    public enum DistanceFunc {
//        L0,
//        L1,
//        L2,
//        F,
//        CD
//    }
//
//    public static class Output {
//        boolean survived;
//        double percentageSure;
//
//        public Output(boolean survived, double percentageSure) {
//            this.survived = survived;
//            this.percentageSure = percentageSure;
//        }
//    }
//
//    public static class PassengerComparator implements Comparator<Titanic.Passenger> {
//
//        private Titanic.Passenger testPassenger;
//        private TitanicScoring1.DistanceFunc func;
//
//        public PassengerComparator(Titanic.Passenger passenger, TitanicScoring1.DistanceFunc func) {
//            this.testPassenger = passenger;
//            this.func = func;
//        }
//
//        public int compare(Titanic.Passenger firstPlayer, Titanic.Passenger secondPlayer) {
//            return Double.compare(testPassenger.distance(firstPlayer, func), testPassenger.distance(secondPlayer, func));
//        }
//
//    }
//
//    private static Titanic.Class getMissingPClass(Titanic.Passenger passenger) {
//        Map<Titanic.Class, Integer> pClassMap = new HashMap<>();
//        pClassMap.put(Titanic.Class.FIRST, 0);
//        pClassMap.put(Titanic.Class.SECOND, 0);
//        pClassMap.put(Titanic.Class.THIRD, 0);
//        for (Titanic.Passenger trainPassenger : TitanicTrainingData.passengers) {
//            if (trainPassenger.id() != passenger.id() && !trainPassenger.missing(Titanic.Attribute.CLASS)) {
//                if (trainPassenger.sex() != Titanic.Sex.UNKNOWN && trainPassenger.sex() == passenger.sex()) {
//                    pClassMap.put(trainPassenger.pclass(), pClassMap.get(trainPassenger.pclass()) + 1);
//                }
//                if (trainPassenger.port() != Titanic.Port.UNKNOWN && trainPassenger.port() == passenger.port()) {
//                    pClassMap.put(trainPassenger.pclass(), pClassMap.get(trainPassenger.pclass()) + 1);
//                }
//                if (trainPassenger.age() != -1 && Math.abs(trainPassenger.age() - passenger.age()) <= 5) {
//                    pClassMap.put(trainPassenger.pclass(), pClassMap.get(trainPassenger.pclass()) + 1);
//                }
//                //siblings, parents, fare
//                if (trainPassenger.siblings() != -1 && Math.abs(trainPassenger.siblings() - passenger.siblings()) <= 1) {
//                    pClassMap.put(trainPassenger.pclass(), pClassMap.get(trainPassenger.pclass()) + 1);
//                }
//                if (trainPassenger.parents() != -1 && Math.abs(trainPassenger.parents() - passenger.parents()) <= 1) {
//                    pClassMap.put(trainPassenger.pclass(), pClassMap.get(trainPassenger.pclass()) + 1);
//                }
//                if (trainPassenger.fare() != -1 && Math.abs(trainPassenger.fare() - passenger.fare()) <= 10) {
//                    pClassMap.put(trainPassenger.pclass(), pClassMap.get(trainPassenger.pclass()) + 1);
//                }
//            }
//        }
//
//        int maxCount = pClassMap.get(Titanic.Class.SECOND);
//        Titanic.Class finalClass = Titanic.Class.SECOND;
//        for (Titanic.Class pClass : Titanic.Class.values()) {
//            if (pClassMap.get(pClass) > maxCount) {
//                maxCount = pClassMap.get(pClass);
//                finalClass = pClass;
//            }
//        }
//        return finalClass;
//    }
//
//    private static Titanic.Port getMissingPort(Titanic.Passenger passenger) {
//        Map<Titanic.Port, Integer> portMap = new HashMap<>();
//        portMap.put(Titanic.Port.SOUTHAMPTON, 0);
//        portMap.put(Titanic.Port.CHERBOURG, 0);
//        portMap.put(Titanic.Port.QUEENSTOWN, 0);
//        portMap.put(Titanic.Port.UNKNOWN, 0);
//        for (Titanic.Passenger trainPassenger : TitanicTrainingData.passengers) {
//            if (trainPassenger.id() != passenger.id() && !trainPassenger.missing(Titanic.Attribute.PORT)) {
//                if (trainPassenger.sex() != Titanic.Sex.UNKNOWN && trainPassenger.sex() == passenger.sex()) {
//                    portMap.put(trainPassenger.port(), portMap.get(trainPassenger.port()) + 1);
//                }
//                if (trainPassenger.pclass() != Titanic.Class.UNKNOWN && trainPassenger.pclass() == passenger.pclass()) {
//                    portMap.put(trainPassenger.port(), portMap.get(trainPassenger.port()) + 1);
//                }
//                if (trainPassenger.age() != -1 && Math.abs(trainPassenger.age() - passenger.age()) <= 5) {
//                    portMap.put(trainPassenger.port(), portMap.get(trainPassenger.port()) + 1);
//                }
//                //siblings, parents, fare
//                if (trainPassenger.siblings() != -1 && Math.abs(trainPassenger.siblings() - passenger.siblings()) <= 1) {
//                    portMap.put(trainPassenger.port(), portMap.get(trainPassenger.port()) + 1);
//                }
//                if (trainPassenger.parents() != -1 && Math.abs(trainPassenger.parents() - passenger.parents()) <= 1) {
//                    portMap.put(trainPassenger.port(), portMap.get(trainPassenger.port()) + 1);
//                }
//                if (trainPassenger.fare() != -1 && Math.abs(trainPassenger.fare() - passenger.fare()) <= 10) {
//                    portMap.put(trainPassenger.port(), portMap.get(trainPassenger.port()) + 1);
//                }
//            }
//        }
//
//        int maxCount = portMap.get(Titanic.Port.SOUTHAMPTON);
//        Titanic.Port finalPort = Titanic.Port.SOUTHAMPTON;
//        for (Titanic.Port port : Titanic.Port.values()) {
//            if (portMap.get(port) > maxCount) {
//                maxCount = portMap.get(port);
//                finalPort = port;
//            }
//        }
//        return finalPort;
//    }
//
//    private static int getMissingAge(Titanic.Passenger passenger) {
//        int finalAge = 30;
//        int n = 1;
//        for (Titanic.Passenger trainPassenger : TitanicTrainingData.passengers) {
//            if (trainPassenger.id() != passenger.id() && !trainPassenger.missing(Titanic.Attribute.AGE)) {
//                if (trainPassenger.sex() != Titanic.Sex.UNKNOWN && trainPassenger.sex() == passenger.sex()) {
//                    finalAge += trainPassenger.age();
//                    n++;
//                }
//                if (trainPassenger.port() != Titanic.Port.UNKNOWN && trainPassenger.port() == passenger.port()) {
//                    finalAge += trainPassenger.age();
//                    n++;
//                }
//                if (trainPassenger.pclass() != Titanic.Class.UNKNOWN && trainPassenger.pclass() == passenger.pclass()) {
//                    finalAge += trainPassenger.age();
//                    n++;
//                }
//                //siblings, parents, fare
//                if (trainPassenger.siblings() != -1 && Math.abs(trainPassenger.siblings() - passenger.siblings()) <= 1) {
//                    finalAge += trainPassenger.age();
//                    n++;
//                }
//                if (trainPassenger.parents() != -1 && Math.abs(trainPassenger.parents() - passenger.parents()) <= 2) {
//                    finalAge += trainPassenger.age();
//                    n++;
//                }
//                if (trainPassenger.fare() != -1 && Math.abs(trainPassenger.fare() - passenger.fare()) <= 10) {
//                    finalAge += trainPassenger.age();
//                    n++;
//                }
//            }
//        }
//
//        return (int) finalAge/n;
//    }
//
//    private static int getMissingSiblings(Titanic.Passenger passenger) {
//        int finalSiblings = 1;
//        int n = 1;
//        for (Titanic.Passenger trainPassenger : TitanicTrainingData.passengers) {
//            if (trainPassenger.id() != passenger.id() && !trainPassenger.missing(Titanic.Attribute.SIBLINGS)) {
//                if (trainPassenger.sex() != Titanic.Sex.UNKNOWN && trainPassenger.sex() == passenger.sex()) {
//                    finalSiblings += trainPassenger.siblings();
//                    n++;
//                }
//                if (trainPassenger.port() != Titanic.Port.UNKNOWN && trainPassenger.port() == passenger.port()) {
//                    finalSiblings += trainPassenger.siblings();
//                    n++;
//                }
//                if (trainPassenger.pclass() != Titanic.Class.UNKNOWN && trainPassenger.pclass() == passenger.pclass()) {
//                    finalSiblings += trainPassenger.siblings();
//                    n++;
//                }
//                //siblings, parents, fare
//                if (trainPassenger.age() != -1 && Math.abs(trainPassenger.age() - passenger.age()) <= 5) {
//                    finalSiblings += trainPassenger.siblings();
//                    n++;
//                }
//                if (trainPassenger.parents() != -1 && Math.abs(trainPassenger.parents() - passenger.parents()) <= 1) {
//                    finalSiblings += trainPassenger.siblings();
//                    n++;
//                }
//                if (trainPassenger.fare() != -1 && Math.abs(trainPassenger.fare() - passenger.fare()) <= 10) {
//                    finalSiblings += trainPassenger.siblings();
//                    n++;
//                }
//            }
//        }
//
//        return (int) (finalSiblings/n);
//    }
//
//    private static int getMissingParents(Titanic.Passenger passenger) {
//        int finalParents = 0;
//        int n = 1;
//        for (Titanic.Passenger trainPassenger : TitanicTrainingData.passengers) {
//            if (passenger.id() != trainPassenger.id() && !trainPassenger.missing(Titanic.Attribute.PARENTS)) {
//                if (trainPassenger.sex() != Titanic.Sex.UNKNOWN && trainPassenger.sex() == passenger.sex()) {
//                    finalParents += trainPassenger.parents();
//                    n++;
//                }
//                if (trainPassenger.port() != Titanic.Port.UNKNOWN && trainPassenger.port() == passenger.port()) {
//                    finalParents += trainPassenger.parents();
//                    n++;
//                }
//                if (trainPassenger.pclass() != Titanic.Class.UNKNOWN && trainPassenger.pclass() == passenger.pclass()) {
//                    finalParents += trainPassenger.parents();
//                    n++;
//                }
//                //siblings, parents, fare
//                if (trainPassenger.age() != -1 && Math.abs(trainPassenger.age() - passenger.age()) <= 5) {
//                    finalParents += trainPassenger.parents();
//                    n++;
//                }
//                if (trainPassenger.parents() != -1 && Math.abs(trainPassenger.siblings() - passenger.siblings()) <= 1) {
//                    finalParents += trainPassenger.parents();
//                    n++;
//                }
//                if (trainPassenger.fare() != -1 && Math.abs(trainPassenger.fare() - passenger.fare()) <= 10) {
//                    finalParents += trainPassenger.parents();
//                    n++;
//                }
//            }
//        }
//
//        return (int) (finalParents/n);
//    }
//
//    private static double getMissingFare(Titanic.Passenger passenger) {
//        double finalFare = 33.04;
//        int n = 1;
//        for (Titanic.Passenger trainPassenger : TitanicTrainingData.passengers) {
//            if (passenger.id() != trainPassenger.id() && !trainPassenger.missing(Titanic.Attribute.FARE)) {
//                if (trainPassenger.sex() != Titanic.Sex.UNKNOWN && trainPassenger.sex() == passenger.sex()) {
//                    finalFare += trainPassenger.fare();
//                    n++;
//                }
//                if (trainPassenger.port() != Titanic.Port.UNKNOWN && trainPassenger.port() == passenger.port()) {
//                    finalFare += trainPassenger.fare();
//                    n++;
//                }
//                if (trainPassenger.pclass() != Titanic.Class.UNKNOWN && trainPassenger.pclass() == passenger.pclass()) {
//                    finalFare += trainPassenger.fare();
//                    n++;
//                }
//                //siblings, parents, fare
//                if (trainPassenger.age() != -1 && Math.abs(trainPassenger.age() - passenger.age()) <= 5) {
//                    finalFare += trainPassenger.fare();
//                    n++;
//                }
//                if (trainPassenger.parents() != -1 && Math.abs(trainPassenger.siblings() - passenger.siblings()) <= 1) {
//                    finalFare += trainPassenger.fare();
//                    n++;
//                }
//                if (trainPassenger.fare() != -1 && Math.abs(trainPassenger.parents() - passenger.parents()) <= 1) {
//                    finalFare += trainPassenger.siblings();
//                    n++;
//                }
//            }
//        }
//
//        return finalFare/n;
//    }
//
//    public static double linearFunction(Titanic.Passenger passenger, double[] currentWeights) {
//        passenger = setPassengerMissingData(passenger);
//
//        int sexValue = -1;
//        int embarkedValue = 1;
//
//        if (passenger.sex() == Titanic.Sex.FEMALE) {
//            sexValue = -1;
//        } else {
//            sexValue = 1;
//        }
//
//        if (passenger.port() == Titanic.Port.CHERBOURG) {
//            embarkedValue = 2;
//        } else if (passenger.port() == Titanic.Port.SOUTHAMPTON) {
//            embarkedValue = 1;
//        } else if (passenger.port() == Titanic.Port.QUEENSTOWN) {
//            embarkedValue = 3;
//        }
//
//        double linearFunctionValue = currentWeights[1] * ((passenger.pclass().ordinal() + 1 - 2.305)/0.836869427119643) +
//                currentWeights[2] * ((sexValue - 0.2925)/0.956863749597673) +
//                currentWeights[3] * ((passenger.age() - 29.8711459968603)/14.5443008388913) +
//                currentWeights[4] * ((passenger.siblings() - 0.51875)/1.06351411148685) +
//                currentWeights[5] * ((passenger.parents() - 0.37375)/0.801476070987436) +
//                currentWeights[6] * ((passenger.fare() - 33.03853575)/51.5249501775987) +
//                currentWeights[7] * ((embarkedValue - 1.37125)/0.647261246639834) + currentWeights[0];
//
//        return linearFunctionValue;
//    }
//
//    public static double logisticFunction(Titanic.Passenger passenger, double[] currentWeights) {
//        return 1.0/(1.0 + Math.exp(-1.0 * linearFunction(passenger, currentWeights)));
//    }
//
//    public static double[] computeGradients(double[] currentWeights) {
//        double[] sumResidualGradients = new double[8];
//        for (Titanic.Passenger passenger : TitanicTrainingData.passengers) {
//            double survivedValue = passenger.survived() ? 0.0 : 1.0;
//            double logisticFunctionValue = logisticFunction(passenger, currentWeights);
//
//            sumResidualGradients[0] += -2 * (survivedValue - logisticFunctionValue) * (logisticFunctionValue * (1 - logisticFunctionValue));
//            sumResidualGradients[1] += -2 * (survivedValue - logisticFunctionValue) * (logisticFunctionValue * (1 - logisticFunctionValue))
//                    * ((passenger.pclass().ordinal() + 1 - 2.305)/0.836869427119643);
//            sumResidualGradients[2] += -2 * (survivedValue - logisticFunctionValue) * (logisticFunctionValue * (1 - logisticFunctionValue))
//                    * ((passenger.sexNumerical() - 0.2925)/0.956863749597673);
//            sumResidualGradients[3] += -2 * (survivedValue - logisticFunctionValue) * (logisticFunctionValue * (1 - logisticFunctionValue))
//                    * ((passenger.age() - 29.8711459968603)/14.5443008388913);
//            sumResidualGradients[4] += -2 * (survivedValue - logisticFunctionValue) * (logisticFunctionValue * (1 - logisticFunctionValue))
//                    * ((passenger.siblings() - 0.51875)/1.06351411148685);
//            sumResidualGradients[5] += -2 * (survivedValue - logisticFunctionValue) * (logisticFunctionValue * (1 - logisticFunctionValue))
//                    * ((passenger.parents() - 0.37375)/0.801476070987436);
//            sumResidualGradients[6] += -2 * (survivedValue - logisticFunctionValue) * (logisticFunctionValue * (1 - logisticFunctionValue))
//                    * ((passenger.fare() - 33.03853575)/51.5249501775987);
//            sumResidualGradients[7] += -2 * (survivedValue - logisticFunctionValue) * (logisticFunctionValue * (1 - logisticFunctionValue))
//                    * ((passenger.embarkedNumerical() - 1.37125)/0.647261246639834);
//        }
//
//        return sumResidualGradients;
//    }
//
//    private static double[] returnNewWeights(double[] currentWeights, double alpha, double[] gradient) {
//        double[] newWeights = currentWeights;
//        for (int i = 0; i < currentWeights.length; i++) {
//            newWeights[i] = currentWeights[i] + (-1 * alpha * gradient[i]);
//        }
//
//        return newWeights;
//    }
//
//    public static void printArray(double[] array) {
//        for (double arr : array) {
//            System.out.print(String.format("%.3f", arr) + " ");
//        }
//        System.out.println();
//    }
//
//    public static boolean validGradient(double[] gradients) {
//        for (double gradient: gradients) {
//            if (gradient > 0.000000001) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//
//    public static void trainWeights() {
//        double[] currentWeights = {1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
//        double[] gradients = {1, 1, 1, 1, 1, 1, 1, 1};
//        double stepSize = 0.003;
//
//        while (validGradient(gradients)){
//
//            gradients = computeGradients(currentWeights);
//            currentWeights = returnNewWeights(currentWeights, stepSize, gradients);
//
//        }
//        printArray(gradients);
//        W1 = currentWeights[1];
//        W2 = currentWeights[2];
//        W3 = currentWeights[3];
//        W4 = currentWeights[4];
//        W5 = currentWeights[5];
//        W6 = currentWeights[6];
//        W7 = currentWeights[7];
//        W0 = currentWeights[0];
//    }
//
//
//    public static Output knn(Titanic.Passenger testPassenger, TitanicScoring1.DistanceFunc func) {
//        testPassenger = setPassengerMissingData(testPassenger);
//        PriorityQueue<Titanic.Passenger> passengerQueue = new PriorityQueue<>(knnThreshold, new TitanicScoring1.PassengerComparator(testPassenger, func));
//
//        for (Titanic.Passenger trainPassenger : TitanicTrainingData.passengers) {
//            trainPassenger = setPassengerMissingData(trainPassenger);
//            passengerQueue.add(trainPassenger);
//        }
//
//        int survivedCounts = 0;
//
//        int i = 0;
//        for (Titanic.Passenger trainPassenger: passengerQueue) {
//            if (i >= knnThreshold) {
//                break;
//            }
//
//            if (trainPassenger.survived()) {
//                survivedCounts++;
//            }
//            i++;
//        }
//
//
//
//        if (survivedCounts > knnThreshold/2) {
//            return new Output(true, ((double) survivedCounts)/((double) knnThreshold) * 100.0);
//        }
//
//        return new Output(false, ((double) (knnThreshold - survivedCounts))/((double) knnThreshold) * 100.0);
//    }
//
//    public static Titanic.Passenger setPassengerMissingData(Titanic.Passenger passenger) {
//        if (passenger.missing(Titanic.Attribute.CLASS)) {
//            passenger = new Titanic.Passenger(passenger.id(), passenger.name(), passenger.survived(), passenger.port(), TitanicScoring1.getMissingPClass(passenger), passenger.sex(),
//                    passenger.age(), passenger.siblings(), passenger.parents(), passenger.fare());
//        }
//
//        if (passenger.missing(Titanic.Attribute.PORT)) {
//            passenger = new Titanic.Passenger(passenger.id(), passenger.name(), passenger.survived(), TitanicScoring1.getMissingPort(passenger), passenger.pclass(), passenger.sex(),
//                    passenger.age(), passenger.siblings(), passenger.parents(), passenger.fare());
//        }
//        if (passenger.missing(Titanic.Attribute.AGE)) {
//            passenger = new Titanic.Passenger(passenger.id(), passenger.name(), passenger.survived(), passenger.port(), passenger.pclass(), passenger.sex(),
//                    TitanicScoring1.getMissingAge(passenger), passenger.siblings(), passenger.parents(), passenger.fare());
//        }
//        if (passenger.missing(Titanic.Attribute.SIBLINGS)) {
//            passenger = new Titanic.Passenger(passenger.id(), passenger.name(), passenger.survived(), passenger.port(), passenger.pclass(), passenger.sex(),
//                    passenger.age(), TitanicScoring1.getMissingSiblings(passenger), passenger.parents(), passenger.fare());
//        }
//        if (passenger.missing(Titanic.Attribute.PARENTS)) {
//            passenger = new Titanic.Passenger(passenger.id(), passenger.name(), passenger.survived(), passenger.port(), passenger.pclass(), passenger.sex(),
//                    passenger.age(), passenger.siblings(), TitanicScoring1.getMissingParents(passenger), passenger.fare());
//        }
//        if (passenger.missing(Titanic.Attribute.FARE)) {
//            passenger = new Titanic.Passenger(passenger.id(), passenger.name(), passenger.survived(), passenger.port(), passenger.pclass(), passenger.sex(),
//                    passenger.age(), passenger.siblings(), passenger.parents(), TitanicScoring1.getMissingFare(passenger));
//        }
//
//        return passenger;
//    }
//
//    public static boolean decisionTree(Titanic.Passenger passenger) {
//        passenger = setPassengerMissingData(passenger);
//        if (passenger.sex() == Titanic.Sex.FEMALE) {
//            if (passenger.pclass() == Titanic.Class.FIRST) {
//
//                if (passenger.age() == TitanicTrainingData.NA) {
//
//                }
//
//                if (passenger.age() < 10) {
//                    return true;
//                } else if (passenger.age() >= 10) {
//                    if (passenger.fare() >= 50) {
//                        if (passenger.parents() >= 0 && passenger.parents() <= 1) {
//                            return false;
//                        } else if (passenger.parents() == 2) {
//                            if (passenger.siblings() >= 0 && passenger.siblings() <= 3 && passenger.siblings() != 1) {
//                                return false;
//                            } else if (passenger.siblings() == 1) {
//                                return false;
//                            } else if (passenger.siblings() > 4) {
//                                return true;
//                            }
//                        } else if (passenger.parents() > 3) {
//                            return true;
//                        }
//                    } else if (passenger.fare() < 50) {
//                        if (passenger.port() == Titanic.Port.SOUTHAMPTON) {
//                            return false;
//                        } else if (passenger.port() == Titanic.Port.CHERBOURG) {
//                            return false;
//                        } else if (passenger.port() == Titanic.Port.QUEENSTOWN) {
//                            return true;
//                        }
//                    }
//                }
//
//            } else if (passenger.pclass() == Titanic.Class.SECOND) {
//
//                if (passenger.parents() == 2 || passenger.parents() == 3) {
//                    return false;
//                } else if (passenger.parents() >= 4 && passenger.parents() <= 6) {
//                    return true;
//                } else if (passenger.parents() == 0) {
//                    if (passenger.port() == Titanic.Port.SOUTHAMPTON) {
//                        return false;
//                    } else if (passenger.port() == Titanic.Port.CHERBOURG || passenger.port() == Titanic.Port.QUEENSTOWN) {
//                        return false;
//                    }
//                } else if (passenger.parents() == 1) {
//                    if (passenger.siblings() >= 0 && passenger.siblings() <= 2 && passenger.siblings() != 1) {
//                        return false;
//                    } else if (passenger.siblings() == 1) {
//                        return false;
//                    } else if (passenger.siblings() > 2) {
//                        return true;
//                    }
//                }
//
//            } else if (passenger.pclass() == Titanic.Class.THIRD) {
//
//                if (passenger.age() >= 10) {
//                    if (passenger.siblings() == 0) {
//                        if (passenger.parents() == 0) {
//
//                            if (passenger.port() == Titanic.Port.SOUTHAMPTON) {
//                                if (passenger.fare() >= 50) {
//                                    return true;
//                                } else if (passenger.fare() < 50) {
//                                    return false;
//                                }
//                            } else if (passenger.port() == Titanic.Port.CHERBOURG) {
//                                return false;
//                            } else if (passenger.port() == Titanic.Port.QUEENSTOWN) {
//                                return true;
//                            }
//
//
//                        } else if (passenger.parents() == 1 || passenger.parents() == 5) {
//                            return false; //50/50
//                        } else if (passenger.parents() == 2 || passenger.parents() == 3 || passenger.parents() == 4 || passenger.parents() == 6) {
//                            return true;
//                        }
//                    } else if (passenger.siblings() == 1) {
//                        if (passenger.parents() == 0) {
//
//                            if (passenger.port() == Titanic.Port.SOUTHAMPTON) {
//                                if (passenger.fare() >= 50) {
//                                    return true;
//                                } else if (passenger.fare() < 50) {
//                                    return false;
//                                }
//                            } else if (passenger.port() == Titanic.Port.CHERBOURG) {
//                                return true;
//                            } else if (passenger.port() == Titanic.Port.QUEENSTOWN) {
//                                return true;
//                            }
//
//
//                        } else if (passenger.parents() == 1 || passenger.parents() == 5) {
//                            return true;
//                        } else if (passenger.parents() == 2 || passenger.parents() == 3 || passenger.parents() == 4 || passenger.parents() == 6) {
//                            return true;
//                        }
//                    } else if (passenger.siblings() == 2 || passenger.siblings() == 5 || passenger.siblings() == 8) {
//                        return true;
//                    } else if (passenger.siblings() == 3) {
//                        return false;
//                    } else if (passenger.siblings() == 4) {
//                        return true;
//                    }
//                } else if (passenger.age() < 10) {
//                    if (passenger.siblings() == 0) {
//                        if (passenger.parents() == 0 || passenger.parents() == 2) {
//                            return false;
//                        } else if (passenger.parents() == 1) {
//                            return false;
//                        } else if (passenger.parents() > 3) {
//                            return true;
//                        }
//                    } else if (passenger.siblings() == 2) {
//                        if (passenger.port() == Titanic.Port.SOUTHAMPTON || passenger.port() == Titanic.Port.QUEENSTOWN) {
//                            return true;
//                        } else if (passenger.port() == Titanic.Port.CHERBOURG) {
//                            return false;
//                        }
//                    } else if (passenger.siblings() == 3 || passenger.siblings() == 5 || passenger.siblings() == 8) {
//                        return true;
//                    } else if (passenger.siblings() == 1) {
//                        return false;
//                    } else if (passenger.siblings() == 4) {
//                        if (passenger.port() == Titanic.Port.CHERBOURG || passenger.port() == Titanic.Port.QUEENSTOWN) {
//                            return true;
//                        } else if (passenger.port() == Titanic.Port.SOUTHAMPTON) {
//                            return true;
//                        }
//                    }
//                }
//
//            } else if (passenger.pclass() == Titanic.Class.UNKNOWN) {
//
//            }
//        } else if (passenger.sex() == Titanic.Sex.MALE) {
//            if (passenger.pclass() == Titanic.Class.FIRST) {
//                if (passenger.age() >= 50) {
//                    if (passenger.siblings() == 0 || passenger.siblings() > 2 || passenger.siblings() == 1) {
//                        return true;
//                    } else if (passenger.siblings() == 2) {
//                        return false;
//                    }
//                } else if (passenger.age() < 50) {
//                    if (passenger.age() >= 10) {
//                        if (passenger.siblings() == 0) {
//                            return true; //50/50
//                        } else if (passenger.siblings() == 1) {
//                            return false; //50/50
//                        } else if (passenger.siblings() > 1) {
//                            return true;
//                        }
//                    } else if (passenger.age() < 10) {
//                        return false;
//                    }
//                }
//            } else if (passenger.pclass() == Titanic.Class.SECOND) {
//                if (passenger.age() >= 10) {
//                    if (passenger.age() >= 30) {
//                        return true;
//                    } else if (passenger.age() < 30) {
//                        return true;
//                    }
//                } else if (passenger.age() < 10) {
//                    return false;
//                }
//            } else if (passenger.pclass() == Titanic.Class.THIRD) {
//                if (passenger.fare() >= 50) {
//                    if (passenger.siblings() == 0) {
//                        return false;
//                    } else if (passenger.siblings() > 0) {
//                        return true;
//                    }
//                } else if (passenger.fare() < 50) {
//                    return true;
//                }
//            }
//        }
//
//        return false;
//    }
//    public static Output survivedLR(Titanic.Passenger passenger) {
//
//        passenger = setPassengerMissingData(passenger);
//
//        int sexValue = -1;
//        int embarkedValue = 1;
//
//        if (passenger.sex() == Titanic.Sex.FEMALE) {
//            sexValue = -1;
//        } else {
//            sexValue = 1;
//        }
//
//        if (passenger.port() == Titanic.Port.CHERBOURG) {
//            embarkedValue = 2;
//        } else if (passenger.port() == Titanic.Port.SOUTHAMPTON) {
//            embarkedValue = 1;
//        } else if (passenger.port() == Titanic.Port.QUEENSTOWN) {
//            embarkedValue = 3;
//        }
//
//        double linearFunctionValue = W1 * ((passenger.pclass().ordinal() + 1 - 2.305)/0.836869427119643) +
//                W2 * ((sexValue - 0.2925)/0.956863749597673) +
//                W3 * ((passenger.age() - 29.8711459968603)/14.5443008388913) +
//                W4 * ((passenger.siblings() - 0.51875)/1.06351411148685) +
//                W5 * ((passenger.parents() - 0.37375)/0.801476070987436) +
//                W6 * ((passenger.fare() - 33.03853575)/51.5249501775987) +
//                W7 * ((embarkedValue - 1.37125)/0.647261246639834) + W0;
//
//
//        double logisticFunction = 1.0/(1.0 + Math.exp(-1 * linearFunctionValue));
//
//        double lambdaConstant = 2.00;
//
//        if (logisticFunction < 0.50) {
//            return new Output(true, (0.5 - logisticFunction) * lambdaConstant * 100.0);
//        }
//
//        return new Output(false, (logisticFunction - 0.5) * lambdaConstant * 100);
//
//    }
//
//    public static void main(String[] args) {
//        trainWeights();
//        if (args.length > 0) {
//            String arg = args[0].replace("-", "");
//            knnThreshold = Integer.valueOf(arg);
//
//            if (args.length > 1) {
//                arg = args[1].replace("-", "");
//                func = DistanceFunc.valueOf(arg);
//            }
//        }
//        for (Titanic.Passenger passenger : TitanicTestingData.passengers) {
////            try {
////                boolean survived = survived(passenger);
////                if (survived) {
////                    System.out.println("0");
////                } else {
////                    System.out.println("1");
////                }
////            } catch (Exception e) {
////                System.out.println(e);
////                System.out.println(passenger.id() + ",null");
////            }
//
//            try {
//                Output survivedLR = survivedLR(passenger);
//                Output survivedKNN = knn(passenger, func);
//                boolean survivedDecisionTree = decisionTree(passenger);
//                String knnOutput = survivedKNN.survived ? "0" : "1";
//                String lrOutput = survivedLR.survived ? "0" : "1";
//
//                //KNN Test
////                if (survivedKNN.survived) {
////                    System.out.println("0");
////                } else {
////                    System.out.println("1");
////                }
//
//                //LR Test
//                if (survivedLR.survived) {
//                    System.out.println("0");
//                } else {
//                    System.out.println("1");
//                }
//
//                //DT Test
////                if (survivedDecisionTree) {
////                    System.out.println("0");
////                } else {
////                    System.out.println("1");
////                }
//
//                //DEBUGGING REFERENCE
////                if (!knnOutput.equals(lrOutput)) {
////                    System.out.println(passenger.id() + ": " + knnOutput + " KNN: " + survivedKNN.percentageSure + ", " + lrOutput + " LR: " + survivedLR.percentageSure);
////                }
//
//                //CURRENT BEST ENSEMBLE LEARNING WITH TWO ALGORITHMS AND WEIGHTED SOLUTION
////                if (survivedLR.percentageSure > survivedKNN.percentageSure) {
////                    System.out.println(lrOutput);
////                } else {
////                    System.out.println(knnOutput);
////                }
//
//                //ORIGINAL ENSEMBLE LEARNING WITH THREE ALGORITHMS
////                if ((survivedLR && survivedKNN) || (survivedLR && survivedDecisionTree) || (survivedKNN && survivedDecisionTree)) {
////                    System.out.println("0");
////                } else {
////                    System.out.println("1");
////                }
//            } catch (Exception e) {
//                System.out.println(e);
//                System.out.println(passenger.id() + ",null");
//            }
//
//
//
//        }
//    }
//}
