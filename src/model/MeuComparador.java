package model;

import java.util.Comparator;
import model.Contas;

public class MeuComparador implements Comparator<Contas> {
    
    @Override
    public int compare(Contas c1, Contas c2) {
        return c1.getName().compareTo(c2.getName());
    }
}
