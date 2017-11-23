/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import calculo.ProximaMenstruacao;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import model.DAO.hibernates.MulherHibernate;
import model.pojo.Ciclo;
import model.pojo.Mulher;
import model.pojo.Pesquisador;


/**
 *
 * @author Irandir
 */
@ManagedBean
public class FormularioBean {
    private Mulher mulher = new Mulher();
    private Ciclo ciclo = new Ciclo();
    //private List<Mulher> mulheres;
    public String cria(){
        
        byte d = 28;
        byte d2 = 31;
        Date pM;
        Pesquisador pesquisador = (Pesquisador) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pesquisador");
        if(ciclo.getTipo().equals("Regular")){
            
            pM = new ProximaMenstruacao(mulher.getUltimaMenstruacao(), d).getpM();
            mulher.setPM(pM);
            mulher.setTipo(ciclo);
            mulher.setPesquisador(pesquisador);
            new MulherHibernate().insert(mulher);

        }else if(ciclo.getTipo().equals("Inregular")){
            pM = new ProximaMenstruacao(mulher.getUltimaMenstruacao(), d2).getpM();
  
            mulher.setPM(pM);
            mulher.setTipo(ciclo);
            mulher.setPesquisador(pesquisador);
            new MulherHibernate().insert(mulher);
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

    public Ciclo getCiclo() {
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }

    
    
}
