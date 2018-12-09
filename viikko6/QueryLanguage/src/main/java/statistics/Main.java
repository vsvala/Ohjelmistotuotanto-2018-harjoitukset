package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));
          
//        Matcher m = new And( new HasAtLeast(10, "goals"),
//                             new HasAtLeast(10, "assists"),
//                             new PlaysIn("PHI")
//        );
//        
//        for (Player player : stats.matches(m)) {
//            System.out.println( player );
//        }
//Matcher m = new Not(new HasAtLeast(1, "goals"));
//
//        for (Player player : stats.matches(m)) {
//            System.out.println(player);
//        }

//Matcher m = new Or( new HasAtLeast(40, "goals"),
//                    new HasAtLeast(60, "assists"),
//                    new HasAtLeast(85, "points")
//); 
//
//for (Player player : stats.matches(m)) {
//    System.out.println( player );
//}
//
//Matcher m = new All( new HasAtLeast(40, "goals"),
//                    new HasAtLeast(60, "assists"),
//                    new HasAtLeast(85, "points")
//); 

//Matcher m = new HasFewerThan(1, "goals");  
//for (Player player : stats.matches(m)) {
//    System.out.println( player );
//}
//
// Querybuilder query = new Querybuilder();
//    Matcher m = query.build();
//
//    for (Player player : stats.matches(m)) {
//        System.out.println( player );
//    }
    Querybuilder query = new Querybuilder();
 
    Matcher m = query.playsIn("NYR").build();
 
    for (Player player : stats.matches(m)) {
        System.out.println( player );
    }
  

    
}
}