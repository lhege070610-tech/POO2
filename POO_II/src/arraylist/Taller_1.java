package arraylist;
import java.util.ArrayList;
import java.util.Scanner;


//MODIFICACIONES
//qyue se pueda ingresar por sccaner 
//menú con opciones
//método - lista vertical
//método - susutituir index off

class Alumno {
    private  final String nombre;
    private int edad;
    private boolean pagoCompleto;

    public Alumno(String nombre, int edad, boolean pagoCompleto) {
        this.nombre = nombre;
        this.edad = edad;
        this.pagoCompleto = pagoCompleto;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public boolean getPagoCompleto() { return pagoCompleto; }
    public void setPagoCompleto(boolean pagoCompleto) { this.pagoCompleto = pagoCompleto; }
    public void setEdad(int edad) { this.edad = edad; }

    @Override
    public String toString() {
        return nombre + " (" + edad + " años, pago: " + (pagoCompleto ? "completo" : "pendiente") + ")";
    }
}

public class Taller_1 {
    //definir capacidad máxima en variable finaln
    public static final int CAPACIDAD_MAX = 20;

    //métodos

    //mostrar la lista de manera vertical
    public static void mostrarLista(ArrayList<Alumno> inscritos){
        //validar si esta vacia
        if(inscritos.isEmpty()){
                System.out.println("No hay alumnos inscritos");
        }else{
            for (Alumno a: inscritos){
                    System.out.println(a);
            }
        }
        System.out.println("Total de inscritos: " + inscritos.size());
    }

    //devuelve la posición del alumno o -1 si no existe 
    public static int buscarIndicePorNombre(ArrayList<Alumno> inscritos, String nombre) {
        //elimianr espacios
        nombre = nombre.trim();
        for (int i = 0; i < inscritos.size(); i++) {
            //comparar nombres sin importar mayusculas y minusculas
            if (inscritos.get(i).getNombre().equalsIgnoreCase(nombre)) {
                return i; // devolver el índice donde se encontro al alumno
            }
        }
        return -1;// si termina el recorrido sin encontrarlo devolver -1
    }

    //validación para edad y que este en el rango de edadwas
    public static int leerEntero(Scanner sc, String mensaje, int minimo, int maximo) {

        int numero;
        //repetir hasta que el usuario ingrese un dato válido
        while (true) {
            System.out.print(mensaje);
            // hasNextInt comprueba si ek dato es un entero
            if (sc.hasNextInt()) {
                numero = sc.nextInt();
                sc.nextLine();
                // Verificar que el num este en el rango
                if (numero >= minimo && numero <= maximo) {
                    return numero;
                }

                System.out.println("Ingresa un nuemero entre " + minimo + " y " + maximo + ".");
            } else {
                System.out.println("Error: debes ingresar un numero entero.");
                sc.nextLine();
            }
        }
    }

    //validar que el pago sea si o no
    public static boolean leerPago(Scanner sc) {
        String respuesta;

        while (true) {
            System.out.print("El Pago esta completo? (si/no): ");
            //leer la respuesta y quitar espacios al inicio y al final
            respuesta = sc.nextLine().trim();

            if (respuesta.equalsIgnoreCase("si")) {
                return true;
            }

            if (respuesta.equalsIgnoreCase("no")) {
                return false;
            }

            System.out.println("Escribe si o no");
        }
    }


    //para corregir la edad de un alumno
    public static void corregirEdad(ArrayList<Alumno> inscritos, Scanner sc) {

        System.out.println("\n=== CORREGIR EDAD ===");
        System.out.print("Nombre del alumno: ");
        String nombre = sc.nextLine();

        //buscar posición del alumno
        int indice = buscarIndicePorNombre(inscritos, nombre);

        // Validar que el alumno exista
        if (indice == -1) {
            System.out.println("Alumno no encontrado.");
            return;
        }

        //Obtener al alumno que se desea corregir
        Alumno anterior = inscritos.get(indice);

        //pedir nueva edad
        int nuevaEdad = leerEntero(sc, "Nueva edad (1 a 18): ", 1, 18);

        //modiificar edad del alumno
        anterior.setEdad(nuevaEdad);

        System.out.println("Edad corregida correctamente.");
        System.out.println(inscritos.get(indice));
    }





    public static void main(String[] args) {

        //Crear array list vacia
        ArrayList<Alumno> inscritos = new ArrayList<>();

        //Scanner
        Scanner sc = new Scanner(System.in);
    
        //Ciclo para menú
        int opcion;
        do{
            System.out.println(" === MENÚ ===");
            System.out.println("1. Inscribir alumno");
            System.out.println("2. Mostrar lista completa");
            System.out.println("3. Buscar alumno por nombre");
            System.out.println("4. Corregir edad");
            System.out.println("5. Marcar pago completo");
            System.out.println("6. Mostrar alumnos menores de 12 años");
            System.out.println("7. Contar pagos pendientes");
            System.out.println("8. Calcular promedio de edades");
            System.out.println("9. Mostrar alumno de mayor edad");
            System.out.println("10. Eliminar alumno por nombre");
            System.out.println("11. Eliminar alumnos con pago pendiente");
            System.out.println("12. Mostrar lugares disponibles");
            System.out.println("13. Inscribir alumno prioritario");
            System.out.println("14. Salir");

            //solicitar opcion y validar que sea un entero del 1 al 14
            opcion = leerEntero(sc, "Selecciona una opcion: ", 1, 14);

            switch (opcion) {
                case 1:
                    System.out.println("\n=== AGREGAR ALUMNO ===");
                    //validar que si se pueda inscribir
                    if (inscritos.size() >= CAPACIDAD_MAX) {
                        System.out.println("El ya taller esta lleno :(");
                        break;
                    }
                    //pedir nombre de alumno y verificar si ya esta inscrito
                    System.out.print("Nombre del alumno: ");
                    String nombre = sc.nextLine().trim();

                    if (nombre.isEmpty()) {
                        //verificar que no este vacio
                        System.out.println("El nombre no puede estar vacío.");
                        break;
                    }
                    
                    if (buscarIndicePorNombre(inscritos, nombre) != -1) {
                        System.out.println("Ese alumno ya esta inscrito");
                        break;
                    }
                    //leer edad y pago usando la validacion de los metodos
                    int edad = leerEntero(sc, "Edad del alumno (1 a 18): ", 1, 18);

                    boolean pago = leerPago(sc);
                    
                    //si todo esta bien agregar a la lista
                    inscritos.add(new Alumno(nombre, edad, pago));
                    break;

                case 2:
                    System.out.println("\n==== MOSTRAR LISTA ===");
                    //llamar método
                    mostrarLista(inscritos);
                    break;
                case 3: 
                    System.out.println("\n=== BUSCAR POR NOMBRE ===");
                    System.out.println("Nombre que deseas buscar: ");
                    String buscado = sc.nextLine();
                    //obtener el indice del alumno
                    int indice = buscarIndicePorNombre(inscritos, buscado);

                    // si el indice no es -1 el alumno existe
                    if(indice != -1){
                        System.out.println("Alumno encontrado:");
                        System.out.println(inscritos.get(indice));
                        System.out.println("Posicion: " + indice);
                    }else{
                        //si no se encuentra mostar no encontrado
                        System.out.println("No encontardo");
                    }
                    break;
                case 4:
                    corregirEdad(inscritos, sc); //mandar llamar metodo de correción de edad
                    break;
                
                case 5: 
                    System.out.println("\n===MARCAR PAGO COMPLETO ===");
                    System.out.print("Nombre del alumno: ");
                    String nombre_pagCom = sc.nextLine();
                    //buscar su posición
                    int indice_pagCom  = buscarIndicePorNombre(inscritos, nombre_pagCom );
                    // Si existe cambiar su estado de pago a true
                    if (indice_pagCom  != -1) {
                        inscritos.get(indice_pagCom ).setPagoCompleto(true);
                        System.out.println("Pago marcado como completo.¿");
                        System.out.println(inscritos.get(indice_pagCom ));
                    } else {
                        System.out.println("Alumno no encontrado");
                    }

                    break;
                
                case 6:
                    System.out.println("=== MOSTRAR ALUMNOS MENORES DE 12 AÑOS ===");
                    //variable de contador
                    int infantiles = 0;

                    //recorrer y validar
                    for (Alumno a : inscritos) {
                        if (a.getEdad() < 12) {
                            System.out.println(a.getNombre()
                                    + " (" + a.getEdad() + " años)");
                            infantiles++;
                        }
                    }

                    if (infantiles == 0) {
                        System.out.println(
                                "No hay alumnos menores de 12 años.");
                    }

                    System.out.println(
                            "Total del grupo infantil: " + infantiles);
                    break;

                case 7: 
                    System.out.println("\n=== PAGOS PENDIENTES ===");
                    //contador de pagos pendiented
                    int pendientes = 0;
                    //recorrer y comprobrar
                    for (Alumno a : inscritos) {
                        if (!a.getPagoCompleto()) {
                            pendientes++;
                        }
                    }

                    System.out.println(
                            "Alumnos con pago pendiente: " + pendientes);
                    break;
                

                case 8: 
                    System.out.println("\n=== PROMEDIO DE EDADES ===");
                    //validar si esta vacia la lista
                    if (inscritos.isEmpty()) {
                        System.out.println("No hay alumnos para calcular el promedio");
                        break;
                    }
                    //si no esta vacia, calcular
                    int sumaEdades = 0;

                    for (Alumno a : inscritos) {
                        sumaEdades += a.getEdad();
                    }

                    double promedio = (double) sumaEdades / inscritos.size();

                    System.out.print("promedio de edades: "+promedio);
                    break;
                

                case 9: {
                    System.out.println("\n=== ALUMNO DE MAYOR EDAD ===");
                    if (inscritos.isEmpty()) {
                        System.out.println("No hay alumnos inscritos.");
                        break;
                    }
                    //recorrer para encontrar alumno mayor
                    Alumno mayor = inscritos.get(0);
                    for (Alumno a : inscritos) {
                        if (a.getEdad() > mayor.getEdad()) {
                            mayor = a;
                        }
                    }

                    System.out.println("Alumno de mayor edad: " + mayor.getNombre());
                    break;
                }

                case 10: {
                    System.out.println("\n=== ELIMINAR ALUMNO POR NOMBRE ===");
                    System.out.print("Nombre del alumno a eliminar: ");
                    String nombre_elim = sc.nextLine();

                    //obtener indice
                    int indice_elim = buscarIndicePorNombre(inscritos, nombre_elim);
                    //si existe, borrar
                    if (indice_elim != -1) {
                        Alumno eliminado = inscritos.remove(indice_elim);
                        System.out.println("Alumno eliminado: " + eliminado.getNombre());
                    } else {
                        System.out.println("Alumno no encontrado");
                    }

                    break;
                }

                case 11: 
                    System.out.println("\n=== ELIMINAR ALUMNOS CON PAGO PENDIENTE ===");
                    int eliminados = 0;
                    //for invertido e ir eliminanando a los que no tienen pago completo
                    for (int i = inscritos.size() - 1; i >= 0; i--) {
                        if (!inscritos.get(i).getPagoCompleto()) {
                            inscritos.remove(i);
                            eliminados++;
                        }
                    }

                    System.out.println("Alumnos eliminados por pago pendiente: "+ eliminados);

                    System.out.println("Lista final: alumnos que ya pagaron");
                    mostrarLista(inscritos);
                    break;
                

                case 12: 
                    //hacer calculo de lugares disponibles
                    System.out.println("\n=== MOSTRAR LUGARES DISPONIBLES ===");
                    int disponibles = CAPACIDAD_MAX - inscritos.size();

                    System.out.println("Alumnos inscritos: " + inscritos.size());
                    System.out.println("Lugares disponibles: " + disponibles + " de " + CAPACIDAD_MAX);
                    break;
                

                case 13:
                    System.out.println("\n=== INSCRIBIR ALUMNO PRIORITARIO===");
                    //validar que aun haya espacio
                    if (inscritos.size() >= CAPACIDAD_MAX) {
                        System.out.println("El taller esta lleno.");
                        break;
                    }
                    //verificar que no este vacio
                    System.out.print("Nombre del alumno: ");
                    String nombre_prior = sc.nextLine().trim();

                    if (nombre_prior.isEmpty()) {
                        System.out.println("El nombre no puede estar vacío.");
                        break;
                    }
                     // validar si esta inscrito
                    if (buscarIndicePorNombre(inscritos, nombre_prior) != -1) {
                        System.out.println("Ese alumno ya esta inscrito");
                        break;
                    }

                    int edad_prior = leerEntero(sc, "Edad del alumno (1 a 18): ", 1, 18);
                    boolean pago_prior = leerPago(sc); 
                    // Crear el objeto del alumno prioritario.
                    Alumno prioritario = new Alumno(nombre_prior, edad_prior, pago_prior);

                    // Agregarlo al principio de la lista
                    inscritos.add(0, prioritario);

                    System.out.println("Alumno prioritario inscrito correctamente");
                    

                    break;

                case 14:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no valida");
                    break;
            }

        }while(opcion!= 14);


        
        
       
    }
}