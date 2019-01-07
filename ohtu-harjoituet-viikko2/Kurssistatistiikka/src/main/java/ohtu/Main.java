package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {

    public static void main(String[] args) throws IOException {

        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/" + studentNr + "/submissions";
        String url2 = "https://studies.cs.helsinki.fi/courses/courseinfo";
        String url3 = "https://studies.cs.helsinki.fi/courses/ohtu2018/stats";
        String url4 = "https://studies.cs.helsinki.fi/courses/rails2018/stats";

        String bodyText = Request.Get(url).execute().returnContent().asString();
        String bodyText2 = Request.Get(url2).execute().returnContent().asString();
        String ohtuResponse =  Request.Get(url3).execute().returnContent().asString();
        String railsResponse =  Request.Get(url4).execute().returnContent().asString();
//        System.out.println("json-muotoinen data:");
//        System.out.println( bodyText );
        Gson mapper = new Gson();
        Gson mapper2 = new Gson();

        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        CourseStat[] stat = mapper2.fromJson(bodyText2, CourseStat[].class);

        JsonParser parser = new JsonParser();
        JsonObject parsittuOhtuData = parser.parse(ohtuResponse).getAsJsonObject();
        JsonParser parser2 = new JsonParser();
        JsonObject parsittuRailsData = parser2.parse(railsResponse).getAsJsonObject();
        
        
        System.out.println("opiskelijanumero: " + studentNr);
        System.out.println("");

        System.out.println(stat[2].getFullName() + "\n");

        int amount = 0;
        int yht = 0;
        int railsyht = 0;
        int railshours = 0;
        int ohtuyht = 0;
        int ohtuhours = 0;
        int max = 0;
        max = stat[2].countExercises();
        int ohtumax = 0;
        ohtumax = stat[0].countExercises();
        //System.out.println(max+ "\n");

        for (Submission submission : subs) {
//            System.out.print(submission);
//            submission.printExercises();
//            System.out.print("\n ");
            amount += submission.gethours();
            yht += submission.getExercises().size();

            if (submission.getCourse().equals("rails2018")) {
                System.out.println(" ");
                System.out.println("viikko: " + submission.getWeek());
                ArrayList<Integer> maxvkotehtv = new ArrayList();
                maxvkotehtv = stat[2].getExercises();
                railsyht += submission.getExercises().size();
                railshours += submission.gethours();
                System.out.print(" tehtyjätehtäviä " + submission.getExercises().size() + "/" + maxvkotehtv.get(submission.getWeek()) + " aikaa kului " + submission.gethours() + ", tehdyt tehtävät:");
                submission.printExercises();
           
            
            }
             if(submission.getCourse().equals("rails2018")){
             getCoursedata(parsittuRailsData);
        }
        }
        System.out.println("");
        System.out.println("yhteensä: " + railsyht + "/" + max + " tehtävää " + railshours + " tuntia");

        System.out.println("");
        System.out.println(stat[0].getFullName() + "\n");

        for (Submission submission : subs) {
            if (submission.getCourse().equals("ohtu2018")) {
                System.out.println(" ");
                System.out.println("viikko: " + submission.getWeek());
                ArrayList<Integer> maxohtutehtv = new ArrayList();
                maxohtutehtv = stat[0].getExercises();
                ohtuyht += submission.getExercises().size();
                ohtuhours += submission.gethours();
                System.out.print(" tehtyjätehtäviä yhteensä " + submission.getExercises().size() + "/" + maxohtutehtv.get(submission.getWeek()) + " aikaa kului " + submission.gethours() + ", tehdyt tehtävät:");
                submission.printExercises();
            } 
            if(submission.getCourse().equals("ohtu2018")){
                getCoursedata(parsittuOhtuData);
        } 
       
           
    }      System.out.println("");
        System.out.println("yhteensä: " + ohtuyht + "/" + ohtumax + " tehtävää " + ohtuhours + " tuntia");
            }
         
        static void getCoursedata(JsonObject courseData ){
        int palautukset=0;
        int  tunnit=0;
        int tehtavat=0;
        for (String key:courseData.keySet()){
            palautukset+=courseData.get(key).getAsJsonObject().get("students").getAsInt();
            tehtavat+=courseData.get(key).getAsJsonObject().get("exercise_total").getAsInt();
            tunnit+=courseData.get(key).getAsJsonObject().get("hour_total").getAsInt();
           // System.out.println(courseData.get(key).getAsJsonObject().get("students").getAsInt());
           //System.out.println("paaaaaaaaaaaaaa"+palautukset);      
            
        }
                System.out.println("");
           System.out.println("Kurssilla yhteensä " +palautukset+ " palautusta, palautettuja tehtavia " +tehtavat+" kpl, aikaa käytetty yhteensä " +tunnit+ " tuntia");
    }
}