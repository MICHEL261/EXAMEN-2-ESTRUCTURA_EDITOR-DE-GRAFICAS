import javax.swing.JComboBox;

public class trazo {

    tipotrazo tipo;
    int x1, y1, x2, y2;

    trazo siguiente;
    

    public trazo(String tipo, int x1, int y1, int x2, int y2) {
        tipotrazo tipo2= tipotrazo. LINEA;
        for(int i=0;i<tipotrazo.values().length;i++){
            if(tipotrazo.values()[i].toString().equals(tipo)){
                tipo2=tipotrazo.values()[i];
                
            }
        }
        
        this.tipo = tipo2;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    
    

}
