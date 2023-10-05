
public class Titanic {

    public static enum Attribute {
        NAME,
        PORT,
        CLASS,
        SEX,
        AGE,
        SIBLINGS,
        PARENTS,
        FARE
    }

    public static enum Sex   { MALE, FEMALE, UNKNOWN }
    public static enum Class { FIRST, SECOND, THIRD, UNKNOWN }
    public static enum Port  { CHERBOURG, QUEENSTOWN, SOUTHAMPTON, UNKNOWN }

    public static class Passenger {

        private int     id;
        private String  name;
        private Port    port;
        private Class   pclass;
        private Sex     sex;
        private double  age;
        private int     siblings;
        private int     parents;
        private double  fare;
        private Boolean survived;

        public Passenger(
                int     id,
                String  name,
                Boolean survived,
                Port    port,
                Class   pclass,
                Sex     sex,
                double  age,
                int     siblings,
                int     parents,
                double  fare)
        {
            this.id       = id;
            this.name     = name;
            this.survived = survived;
            this.port     = port;
            this.pclass   = pclass;
            this.sex      = sex;
            this.age      = age;
            this.siblings = siblings;
            this.parents  = parents;
            this.fare     = fare;
        }

        public int     id()       { return this.id; }
        public String  name()     { return this.name; }
        public Boolean survived() { return this.survived; }
        public Port    port()     { return this.port; }
        public Class   pclass()   { return this.pclass; }
        public Sex     sex()      { return this.sex; }
        public double  age()      { return this.age; }
        public int     siblings() { return this.siblings; }
        public int     parents()  { return this.parents; }
        public double  fare()     { return this.fare; }

        public boolean missing(Attribute attribute) {
            // Determines if the data for this attribute is missing for this passenger.
            switch(attribute) {
                case NAME:     return this.name.length() == 0;
                case CLASS:    return this.pclass == Class.UNKNOWN;
                case PORT:     return this.port == Port.UNKNOWN;
                case SEX:      return this.sex == Sex.UNKNOWN;
                case AGE:      return this.age < 0.0;
                case SIBLINGS: return this.siblings < 0;
                case PARENTS:  return this.parents < 0;
                case FARE:     return this.fare < 0.0;
            }
            throw new IllegalArgumentException("Invalid attribute: " + attribute);
        }

        public double get(Attribute attribute) {
            // Returns the value of the specified attribute for this passenger.
            switch(attribute) {
                case CLASS:    return this.pclass.ordinal();
                case PORT:     return this.port.ordinal();
                case SEX:      return this.sex.ordinal();
                case AGE:      return this.age;
                case SIBLINGS: return this.siblings;
                case PARENTS:  return this.parents;
                case FARE:     return this.fare;
            }
            throw new IllegalArgumentException("Invalid attribute: " + attribute);
        }


    }
}


/*

public class Titanic {

    public static enum Attribute {
        NAME,
        PORT,
        CLASS,
        SEX,
        AGE,
        SIBLINGS,
        PARENTS,
        FARE
    }

    public static enum Sex   { MALE, FEMALE, UNKNOWN }
    public static enum Class { FIRST, SECOND, THIRD, UNKNOWN }
    public static enum Port  { CHERBOURG, QUEENSTOWN, SOUTHAMPTON, UNKNOWN }

    public static class Passenger {

        private int     id;
        private String  name;
        private Port    port;
        private Class   pclass;
        private Sex     sex;
        private double  age;
        private int     siblings;
        private int     parents;
        private double  fare;
        private Boolean survived;

        public Passenger(
                int     id,
                String  name,
                Boolean survived,
                Port    port,
                Class   pclass,
                Sex     sex,
                double  age,
                int     siblings,
                int     parents,
                double  fare)
        {
            this.id       = id;
            this.name     = name;
            this.survived = survived;
            this.port     = port;
            this.pclass   = pclass;
            this.sex      = sex;
            this.age      = age;
            this.siblings = siblings;
            this.parents  = parents;
            this.fare     = fare;
        }

        public int     id()       { return this.id; }
        public String  name()     { return this.name; }
        public Boolean survived() { return this.survived; }
        public Port    port()     { return this.port; }
        public Class   pclass()   { return this.pclass; }
        public Sex     sex()      { return this.sex; }
        public double  age()      { return this.age; }
        public int     siblings() { return this.siblings; }
        public int     parents()  { return this.parents; }
        public double  fare()     { return this.fare; }

        public boolean missing(Attribute attribute) {
            // Determines if the data for this attribute is missing for this passenger.
            switch(attribute) {
                case NAME:     return this.name.length() == 0;
                case CLASS:    return this.pclass == Class.UNKNOWN;
                case PORT:     return this.port == Port.UNKNOWN;
                case SEX:      return this.sex == Sex.UNKNOWN;
                case AGE:      return this.age < 0.0;
                case SIBLINGS: return this.siblings < 0;
                case PARENTS:  return this.parents < 0;
                case FARE:     return this.fare < 0.0;
            }
            throw new IllegalArgumentException("Invalid attribute: " + attribute);
        }

        public double sexNumerical() {
            int sexValue = -1;

            if (sex() == Titanic.Sex.FEMALE) {
                sexValue = -1;
            } else {
                sexValue = 1;
            }

            return sexValue;
        }

        public double embarkedNumerical() {
            int embarkedValue = 1;

            if (port() == Titanic.Port.CHERBOURG) {
                embarkedValue = 2;
            } else if (port() == Titanic.Port.SOUTHAMPTON) {
                embarkedValue = 1;
            } else if (port() == Titanic.Port.QUEENSTOWN) {
                embarkedValue = 3;
            }

            return embarkedValue;
        }

        public double get(Attribute attribute) {
            // Returns the value of the specified attribute for this passenger.
            switch(attribute) {
                case CLASS:    return this.pclass.ordinal();
                case PORT:     return this.port.ordinal();
                case SEX:      return this.sex.ordinal();
                case AGE:      return this.age;
                case SIBLINGS: return this.siblings;
                case PARENTS:  return this.parents;
                case FARE:     return this.fare;
            }
            throw new IllegalArgumentException("Invalid attribute: " + attribute);
        }

        public double distance(Passenger passenger, TitanicScoring1.DistanceFunc func) {
            double sum = 0;
            switch (func) {
                case L1:
                    sum += Math.pow(Math.abs((passenger.embarkedNumerical() - embarkedNumerical())), 1);
                    sum += Math.pow(Math.abs((passenger.pclass.ordinal() + 1 - pclass.ordinal() + 1)), 1);
                    sum += Math.pow(Math.abs((passenger.sexNumerical() - sexNumerical())), 1);
                    sum += Math.pow(Math.abs((passenger.age() - age())), 1);
                    sum += Math.pow(Math.abs((passenger.siblings() - siblings())), 1);
                    sum += Math.pow(Math.abs((passenger.parents() - parents())), 1);
                    sum += Math.pow(Math.abs((passenger.fare() - fare())), 1);
                    break;
                case L2:
                    sum += Math.pow((passenger.embarkedNumerical() - embarkedNumerical()), 2);
                    sum += Math.pow((passenger.pclass.ordinal() + 1 - pclass.ordinal() + 1), 2);
                    sum += Math.pow((passenger.sexNumerical() - sexNumerical()), 2);
                    sum += Math.pow((passenger.age() - age()), 2);
                    sum += Math.pow((passenger.siblings() - siblings()), 2);
                    sum += Math.pow((passenger.parents() - parents()), 2);
                    sum += Math.pow((passenger.fare() - fare()), 2);
                    break;
                case L0:
                    if (embarkedNumerical() != passenger.embarkedNumerical()) {
                        sum += 1;
                    }
                    if (pclass.ordinal() != passenger.pclass.ordinal()) {
                        sum += 1;
                    }
                    if (sexNumerical() != passenger.sexNumerical()) {
                        sum += 1;
                    }
                    if (Math.abs(age() - passenger.age()) > 5) {
                        sum += 1;
                    }
                    if (Math.abs(parents() - passenger.parents()) > 1) {
                        sum += 1;
                    }
                    if (Math.abs(siblings() - passenger.siblings()) > 1) {
                        sum += 1;
                    }
                    if (Math.abs(fare() - passenger.fare()) > 5) {
                        sum += 1;
                    }
                case F:
                    sum += Math.pow((passenger.embarkedNumerical() - embarkedNumerical()), 2);
                    sum += Math.pow((passenger.pclass.ordinal() + 1 - pclass.ordinal() + 1), 2);
                    sum += Math.pow((passenger.sexNumerical() - sexNumerical()), 2);
                    sum += Math.pow((passenger.age() - age()), 2);
                    sum += Math.pow((passenger.siblings() - siblings()), 2);
                    sum += Math.pow((passenger.parents() - parents()), 2);
                    sum += Math.pow((passenger.fare() - fare()), 2);
                    sum = Math.sqrt(sum);
                    break;
                case CD:
                    double passengerMagnitude = Math.sqrt(Math.pow(passenger.embarkedNumerical(), 2) + Math.pow(passenger.pclass.ordinal(), 2)
                            + Math.pow(passenger.sexNumerical(), 2) + Math.pow(passenger.age(), 2) + Math.pow(passenger.siblings(), 2)
                            + Math.pow(passenger.parents(), 2) + Math.pow(passenger.fare(), 2));
                    double selfMagnitude = Math.sqrt(Math.pow(embarkedNumerical(), 2) + Math.pow(pclass.ordinal(), 2)
                            + Math.pow(sexNumerical(), 2) + Math.pow(age(), 2) + Math.pow(siblings(), 2)
                            + Math.pow(parents(), 2) + Math.pow(fare(), 2));
                    sum = 1.0 - (passenger.embarkedNumerical() * embarkedNumerical() + passenger.pclass.ordinal() * pclass.ordinal() + passenger.sexNumerical() * sexNumerical()
                            + passenger.age() * age() + passenger.siblings() * siblings() + passenger.parents() * parents() + passenger.fare() * fare())/(passengerMagnitude * selfMagnitude);
            }
            return sum;
        }
    }
}
*/


