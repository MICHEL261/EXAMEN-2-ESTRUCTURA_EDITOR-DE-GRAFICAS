import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;


public class FrmEditor extends JFrame {

    private JButton btnCargar;
    private JButton btnGuardar;
    private JButton btneliminar;
    private JButton btnSeleccionar;
    private JComboBox cmbTipo;
    private JToolBar tbEditor;
    private JPanel pnlGrafica;

    Estado estado;
    int x;
    int y;
    Color color;
    

    dibujo dibujo = new dibujo();

    public FrmEditor() {

        tbEditor = new JToolBar();
        btnCargar = new JButton();
        btnGuardar = new JButton();
        btneliminar = new JButton();
        cmbTipo = new JComboBox();
        btnSeleccionar = new JButton();
        pnlGrafica = new JPanel();

        setSize(600, 300);
        setTitle("Editor de gráficas");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        btnCargar.setIcon(new ImageIcon(getClass().getResource("/iconos/AbrirArchivos.png")));
        btnCargar.setToolTipText("Agregar");
        btnCargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCargarClick(evt);
            }
        });
        tbEditor.add(btnCargar);

        btnGuardar.setIcon(new ImageIcon(getClass().getResource("/iconos/Guardar.png")));
        btnGuardar.setToolTipText("Agregar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnGuardarClick(evt);
            }
        });
        tbEditor.add(btnGuardar);

        cmbTipo.setModel(new DefaultComboBoxModel(new String[] { "Línea", "Rectángulo", "Circulo" }));
        tbEditor.add(cmbTipo);

        btnSeleccionar.setIcon(new ImageIcon(getClass().getResource("/iconos/Seleccionar.png")));
        btnSeleccionar.setToolTipText("Agregar");
        btnSeleccionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnSeleccionarClick(evt);
            }
        });
        tbEditor.add(btnSeleccionar);

        btneliminar.setIcon(new ImageIcon(getClass().getResource("/iconos/eliminar.png")));
        btneliminar.setToolTipText("eliminar");
        btneliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btneliminarClick(evt);
            }
        });
        tbEditor.add(btneliminar);

        pnlGrafica.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                pnlGraficaMouseClicked(evt);
            }
        });
        pnlGrafica.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent evt) {
                pnlGraficaMouseMoved(evt);
            }
        });

        pnlGrafica.setPreferredSize(new Dimension(300, 200));

        getContentPane().add(tbEditor, BorderLayout.NORTH);
        getContentPane().add(pnlGrafica, BorderLayout.CENTER);

        estado = Estado.NADA;
        color = Color.white;

        this.pack();
        limpiarPanel();
    }

    private void limpiarPanel() {
        Graphics g = pnlGrafica.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, pnlGrafica.getWidth(), pnlGrafica.getHeight());
        
        

        
    }

    private void btnCargarClick(ActionEvent evt) {
        String nombreArchivo = Archivo.elegirArchivo();
        dibujo.desdeArchivo(nombreArchivo);
        dibujo.dibujar(pnlGrafica);
        

    }

    private void btnGuardarClick(ActionEvent evt) {
        dibujo.guardar();
        
      
    }

    private void btnSeleccionarClick(ActionEvent evt) {
        estado= Estado.SELECCIONANDO;
        if (estado == Estado.SELECCIONANDO) {
            dibujo.seleccionartrazo(pnlGrafica, x, y);
            estado =Estado.NADA;
            
            
        }

        
        


    }

    private void btneliminarClick(ActionEvent evt) {
        dibujo.limpiar();
        limpiarPanel();

    }

    private void pnlGraficaMouseClicked(MouseEvent evt) {
        if (estado == Estado.SELECCIONANDO) {
            x=evt.getX();
            y=evt.getY();

            


        dibujo.seleccionartrazo( pnlGrafica, x, y);
        estado=Estado.NADA;
        
                
        
        }if (estado == Estado.NADA) {
            x=evt.getX();
            y=evt.getY();
            
            

        dibujo.seleccionartrazo( pnlGrafica, x, y);
        estado=Estado.NADA;}
        

        
        if (estado == Estado.NADA) {
            estado = Estado.TRAZANDO;
            x = evt.getX();
            y = evt.getY();
        }else if(estado==Estado.TRAZANDO){
            
            switch (cmbTipo.getSelectedIndex()) {
                case 0:
                trazo nuevotrazo=new trazo("LINEA",x ,y, evt.getX(), evt.getY());
                dibujo.agregar(nuevotrazo);
            dibujo.dibujar(pnlGrafica);
            break;
            

            case 1:
            int 
            x1 = evt.getX() < x ? evt.getX() : x;
              int      y1 = evt.getY() < y ? evt.getY() : y;
                 
            trazo nuevotrazo2=new trazo("RECTANGULO",x1 ,y1, Math.abs(evt.getX() - x), Math.abs(evt.getY() - y));
                dibujo.agregar(nuevotrazo2);
            dibujo.dibujar(pnlGrafica);
        
                break;

            case 2:

            x1 = evt.getX() < x ? evt.getX() : x;
                    y1 = evt.getY() < y ? evt.getY() : y;
            trazo nuevotrazo3=new trazo("CIRCULO",x1 ,y1,  Math.abs(evt.getX() - x), Math.abs(evt.getY() - y));
             dibujo.agregar(nuevotrazo3);
            dibujo.dibujar(pnlGrafica);
            
                    break;

            }

            
            
            
        
           
            estado=Estado.NADA;
            
            
        }
    }
    

    private void pnlGraficaMouseMoved(MouseEvent evt) {
       
        
        
        
        switch (estado) {
            
            
            case SELECCIONANDO:
            
                break;
            case TRAZANDO:
            limpiarPanel();
        
            dibujo.dibujar(pnlGrafica);
            
         
                Graphics g = pnlGrafica.getGraphics();
                g.setColor(Color.yellow);
                int x1,y1;
                switch (cmbTipo.getSelectedIndex()) {
                    case 0:
                    g.drawLine(x, y, evt.getX(), evt.getY());
                    
                        break;
                    case 1:
                     x1 = evt.getX() < x ? evt.getX() : x;
                    y1 = evt.getY() < y ? evt.getY() : y;
            
                        g.drawRect(x1, y1, Math.abs(evt.getX() - x), Math.abs(evt.getY() - y) );
                        
                        break;
                    case 2:
                    x1 = evt.getX() < x ? evt.getX() : x;
                    y1 = evt.getY() < y ? evt.getY() : y;
                        g.drawOval(x1, y1, Math.abs(evt.getX() - x), Math.abs(evt.getY() - y));
                        
                        


                    
                        break;
                        
                }
                

                
                
        }
        
    }

}
