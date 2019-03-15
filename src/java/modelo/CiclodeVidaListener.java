/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author nailton viana gloria
 */
public class CiclodeVidaListener implements PhaseListener{

    @Override
    public void afterPhase(PhaseEvent event) {
         // System.out.println("Depois da fase: "+event.getPhaseId());
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void beforePhase(PhaseEvent event) {
       // System.out.println("antes da fase: "+event.getPhaseId());
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 //   @Override
  public PhaseId getPhaseId() {
      
   return null;
        
    }
    
}
