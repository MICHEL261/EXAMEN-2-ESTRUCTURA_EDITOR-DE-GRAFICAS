import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JOptionPane;


public class dibujo {

private trazo cabeza;

public dibujo (){
    cabeza=null;

}
private JComboBox cmbTipo;

public void agregar(trazo n){
    if(n!=null){
        if(cabeza==null){
            cabeza=n;
            
        }else{
            trazo apuntador=cabeza;
            while(apuntador.siguiente != null){
                apuntador=apuntador.siguiente;
            }
            apuntador.siguiente=n;
        }
        n.siguiente=null;
    }
}





public void desdeArchivo(String nombreArchivo) {
        BufferedReader br = Archivo.abrirArchivo(nombreArchivo);
        try {
            String linea = br.readLine();
            while (linea != null) {
                String[] datos = linea.split(";");
                if (datos.length >= 5) {
                    trazo n = new trazo(datos[0],
                    Integer.parseInt(datos[1]), 
                    Integer.parseInt(datos[2]),
                    Integer.parseInt(datos[3]),
                    Integer.parseInt(datos[4]));
                    agregar(n);
                }
                linea = br.readLine();
            }

        } catch (Exception ex) {

        }
    }

    public void guardar(){
        JFileChooser guardarcomo=new JFileChooser();
        guardarcomo.setApproveButtonText("guardar");
        guardarcomo.showSaveDialog(null);
        File archivo=new File(guardarcomo.getSelectedFile()+".txt");
        trazo apuntador=cabeza;
        
        try{
            BufferedWriter salida=new BufferedWriter(new FileWriter(archivo));
            while(apuntador !=null){
                
                    
                    salida.write(apuntador.tipo+";" +apuntador.y1+";"+ apuntador.x1+";"+apuntador.x2+";"+ apuntador.y2+"\n" );
                    
                    
                apuntador=apuntador.siguiente;

                
            }
            

            
            salida.close();
        }catch(Exception e){

        }
    }
    public void seleccionartrazo(JPanel pnl, int x, int y ){
        trazo apuntador=cabeza;
        
        Graphics g=pnl.getGraphics();
        g.setColor(Color.RED);
        
        

        
        while(apuntador !=null){

            int a = Math.max(apuntador.y1, apuntador.y2);
    int b = Math.min(apuntador.y1, apuntador.y2);
    int c = Math.max(apuntador.x1, apuntador.x2);
    int d = Math.min(apuntador.x1, apuntador.x2);

    int ancho = Math.abs(apuntador.x2 - apuntador.x1);
    int alto = Math.abs(apuntador.y2 - apuntador.y1);
    if (x>=d && x<=c+ancho&& y>=b && y<= a+alto){
        
    }
    if (x>=d && x<=c&& y>=b && y<= a){
        if(apuntador.tipo==apuntador.tipo.LINEA){
            g.drawLine(apuntador.x1, apuntador.y1, apuntador.x2, apuntador.y2);
    
        }

    }

    

    

        
            if (x>=d && x<=c+ancho&& y>=b && y<= a+alto){
                
                
                
              
                
                switch (apuntador.tipo) {
                   
                    case RECTANGULO:
                    
                    g.drawRect(apuntador.x1, apuntador.y1, apuntador.x2, apuntador.y2);
                    
                    break;
    
                    case CIRCULO:
                    g.drawOval(apuntador.x1, apuntador.y1,apuntador.x2, apuntador.y2);
                    break;
                
               
                
                
            }
            
            

    
            }else{
                
            }
            apuntador=apuntador.siguiente;
        }
        
    
        
    
    }


    public void dibujar(JPanel pnl){
        Graphics g=pnl.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0, pnl.getWidth(), pnl.getHeight());
        g.setColor(Color.BLUE);
        

        trazo apuntador=cabeza;
        
       
        while(apuntador !=null){
            switch (apuntador.tipo) {
                case LINEA:
                
                g.drawLine(apuntador.x1, apuntador.y1, apuntador.x2, apuntador.y2);
                
                
                    break;
                case RECTANGULO:
                g.drawRect(apuntador.x1, apuntador.y1, apuntador.x2, apuntador.y2);
                
                break;

                case CIRCULO:
                g.drawOval(apuntador.x1, apuntador.y1, apuntador.x2, apuntador.y2);
                break;
            
            
            }
           
            
            
            apuntador=apuntador.siguiente;
        }
    }
    public void limpiar() {
        cabeza = null; // Elimina todos los nodos
    }

    
    
    

    
    

    


    

}
