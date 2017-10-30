/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculo;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Irandir
 */
public class ProximaMenstruacao {
    private Date uM;
    private Date pM;
    private byte duracao;
    public ProximaMenstruacao(Date uM,byte duracao) {
        this.uM = uM;
        this.duracao = duracao;
        this.pM = calculo();
    }
    
    private Date calculo(){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(uM);
        calendario.add(Calendar.DAY_OF_MONTH, duracao);
        return calendario.getTime();
    }

    public Date getpM() {
        return pM;
    }

    public void setpM(Date pM) {
        this.pM = pM;
    }
    
    
    
}
