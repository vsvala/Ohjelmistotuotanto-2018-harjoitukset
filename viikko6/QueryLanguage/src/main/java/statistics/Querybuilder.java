/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics;

import statistics.matcher.All;
import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.Matcher;
import statistics.matcher.PlaysIn;

/**
 *
 * @author svsv
 */
class Querybuilder {

    Matcher matcher;

    public Querybuilder() {
        matcher = new All();

    }

    public Querybuilder playsIn(String nyr) {
       Matcher matcher  = new And(new PlaysIn(nyr));
        return this;
    }

    Matcher build() {
        Matcher m = new All(new HasAtLeast(10, "goals"));         
        return m;

    }

}
