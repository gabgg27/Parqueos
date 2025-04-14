package hotel;

import javax.swing.JOptionPane;




public class Hotel {
    
    static String[][][] habitaciones = new String[5][5][4];// Matriz para almacenar la info de las habitaciones 
    
    public static void main(String[] args){
        precargarHabitaciones();
        
        int opcion;
        do { 
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Menu Principal\n" 
            + " 1.Ver estado de las habitaciones\n" + "2.Modificar la habitacion\n" +
            "3. Resumen del hotel\n"+ "4.Salir"));
            
            switch (opcion){
                case 1:
                    mostrarHabitaciones();
                    break;
                case 2:
                    modificarHabitacion();
                    break;
                case 3:
                    resumenHotel();
                    break;
                case 4:
                    JOptionPane.showConfirmDialog(null,"Gracias por utilizar el sistema.");
                    break;
                default:
                    JOptionPane.showConfirmDialog(null,"Opcion no disponible");       
            }    
        } while (opcion !=4);  // Menu Principal    
    }
    
    static void precargarHabitaciones(){ // El metodo para rellenar la matriz 
        int numero = 100;
        for(int piso = 4; piso >= 0; piso--){
           for(int hab = 0; hab < 5; hab++){
               habitaciones[piso][hab][0]= String.valueOf(numero);
               habitaciones[piso][hab][1]= (hab % 2 == 0 )? " Simple" : "Doble";
               habitaciones[piso][hab][2]= String.valueOf(30 + (hab * 10));
               habitaciones[piso][hab][3]= (hab == 4)? "Sucia" : "Libre";
               numero++;
           }
           numero +=95;
        }  
    }
    
    static void mostrarHabitaciones(){ // muestra las habitaciones con su informacion 
        String mensaje = " Estado de las habitaciones:\n";
        for (int piso =4; piso >=0; piso--){
            mensaje += "Piso" + (5 - piso);
            for (int hab = 0; hab < 5; hab++){
                mensaje += "Habitacion" + habitaciones[piso][hab][0]
                        + "Estado" + habitaciones [piso][hab][3]
                        + "Tipo" + habitaciones[piso][hab][1]
                        + "Precios" + habitaciones[piso][hab][2]
                        + "\n";
            }
            mensaje += "\n";
        }
        JOptionPane.showConfirmDialog(null , mensaje);   
    }
    static void modificarHabitacion(){ // modifica la informacion de la habitacion 
        String numerohab = JOptionPane.showInputDialog("Ingrese el numero de habitacion que desea modificar");
        boolean encontrada = false;
        
        for(int piso =0; piso < 5; piso++){
            for ( int hab = 0; hab < 5; hab++){
                String nuevoEstado = JOptionPane.showInputDialog("Nuevo estado de la habitacion (Libre, Ocupada, Sucia):");
                String nuevoTipo = JOptionPane.showInputDialog("Nuevo Tipo ( Simple, Doble):");
                String nuevoPrecio = JOptionPane.showInputDialog("Nuevo precio");
                habitaciones[piso][hab][3] = nuevoEstado;
                habitaciones[piso][hab][1] = nuevoTipo;
                habitaciones[piso][hab][2] = nuevoPrecio;
                encontrada = true;
                JOptionPane.showMessageDialog(null,"La habitacion fue modificada exitosamente");
                break;
            }
            if (encontrada)break;
            
        }
        if (!encontrada){
            JOptionPane.showConfirmDialog(null, "La habitacion fue encontrada.");
           
        }
    } 
     static void resumenHotel() { // Muestra estadísticas del hotel
        int libres = 0, ocupadas = 0, sucias = 0;
        double ganancia = 0;
        int total = 0;

        for (int piso = 0; piso < 5; piso++) {
            for (int hab = 0; hab < 5; hab++) {
                String estado = habitaciones[piso][hab][3];
                total++;
                switch (estado) {
                    case "Libre":
                        libres++;
                        break;
                    case "Ocupada":
                        ocupadas++;
                        ganancia += Double.parseDouble(habitaciones[piso][hab][2]);
                        break;
                    case "Sucia":
                        sucias++;
                        break;
                }
            }
        }

        String mensaje = "RESUMEN DEL HOTEL:\n\n"; //Se crea el el mensaje con los datos 
        mensaje += "Habitaciones Libres: " + libres + " (" + String.format("%.2f", (libres * 100.0 / total)) + "%)\n";
        mensaje += "Habitaciones Ocupadas: " + ocupadas + " (" + String.format("%.2f", (ocupadas * 100.0 / total)) + "%)\n";
        mensaje += "Habitaciones Sucias: " + sucias + " (" + String.format("%.2f", (sucias * 100.0 / total)) + "%)\n\n";
        mensaje += "Ganancia actual: $" + String.format("%.2f", ganancia);

        JOptionPane.showMessageDialog(null, mensaje); // Muestra el resumen en la pantalla 
    }
}
    






