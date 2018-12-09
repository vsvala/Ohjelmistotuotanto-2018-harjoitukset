/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

import statistics.Player;

/**
 *
 * @author svsv
 */
public class Or implements Matcher {
    
    private Matcher[] matchers;
    
    public Or(Matcher... matchers) {
    this.matchers = matchers;
    }

    @Override
    public boolean matches(Player p) {
      boolean match = false;
        for (Matcher matcher : matchers) {
            if (matcher.matches(p)) {
                match = true;
            }
        }
        return match;
    }  
        
         
        
// for (Matcher matcher : matchers) {
//            if (!matcher.matches(p)) {
//                return false;
//            }
//        }
//
//        return true;
//    }    
}
