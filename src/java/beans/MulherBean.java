/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import calculo.ProximaMenstruacao;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import model.Mulher;
import util.MulherDAO;

/**
 *
 * @author Irandir
 */
@ManagedBean
public class MulherBean {
    private Mulher mulher = new Mulher();
    private List<Mulher> mulheres;
    public String cria(){
        byte d = 28;
        byte d2 = 31;
        Date pM; 
        
        if(mulher.getTipo().equals("Regular")){
            pM = new ProximaMenstruacao(mulher.getUM(), d).getpM();
            mulher.setPM(pM);
            new MulherDAO().insert(mulher);

        }else if(mulher.getTipo().equals("Irregular")){
            pM = new ProximaMenstruacao(mulher.getUM(), d2).getpM();
            mulher.setPM(pM);
            new MulherDAO().insert(mulher);
        }
        
       // MulherDAO.insert(mulher);
        return "concluido";
    }
    
    
    
    public Mulher getMulher() {
        return mulher;
    }

    public void setMulher(Mulher mulher) {
        this.mulher = mulher;
    }

    public List<Mulher> getMulheres() {
        mulheres = new MulherDAO().selectAll();
        return mulheres;
    }

    public void setMulheres(List<Mulher> mulheres) {
        this.mulheres = mulheres;
    }
    
}
