/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Date;
import java.util.List;
import model.Mulher;
import util.MulherDAO;

/**
 *
 * @author Irandir
 */
public class Teste {
    public static void main(String[] args) {
        /*Mulher m = new Mulher();
        m.setNome("Mia2tytyuuy");
        m.setNascimento(new Date());
        m.setTipo("Irregular");
        new MulherDAO().insert(m);*/
        List<Mulher> m = new MulherDAO().selectAll();
        for (Mulher mulher : m) {
            System.out.println(mulher);
        }
    }
}
