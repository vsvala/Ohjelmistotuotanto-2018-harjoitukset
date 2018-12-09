/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

import java.lang.reflect.Method;
import statistics.Player;

/**
 *
 * @author svsv
 */
public class Not implements Matcher {
    
    private Matcher[] matchers;
    
    public Not(Matcher... matchers) {
    this.matchers = matchers;
    }

//    
//    public Not(HasAtLeast hasAtLeast) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public boolean matches(Player p) {
 for (Matcher matcher : matchers) {
            if (matcher.matches(p)) {
                return false;
            }
        }

        return true;
    }    
}
