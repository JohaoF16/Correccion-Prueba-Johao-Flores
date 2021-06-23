package test;

import dominio.Estudiante;
import java.util.Scanner;

public class TestEstudiante {

    static Scanner datos = new Scanner(System.in);

    public static void main(String[] args) {
        int nroHombres;
        int ne;
        System.out.println("Cuantos estudiantes desea ingresar: ");
        int n = datos.nextInt();
        Estudiante[] estudiantes = new Estudiante[n];
        llenaEstudiante(estudiantes);
        System.out.println("Listado de Estudiantes de Mayor a Menor nota: ");
        System.out.println("Nombre\tNota\tGénero");
        //Llamar metodo para ordenar
        estMayorMenor(estudiantes, n);
        for (Estudiante est : estudiantes) {
            System.out.println(est);
        }
        nroHombres = porcentajeGenero(estudiantes);
        System.out.println("El porcentaje de hombres encima del promedio es: " + porcentajePromedio(estudiantes, nroHombres, 'm')+"%");
        System.out.println("El porcentaje de mujeres encima del promedio es: " + porcentajePromedio(estudiantes, nroHombres, 'f')+"%");
        ne = notaAlta(estudiantes);
        System.out.println("El estudiante con mayor nota es: " + estudiantes[ne].getNombre());

    }

    public static void llenaEstudiante(Estudiante est[]) {
        String nombre;
        char genero;
        double nota;
        for (int i = 0; i < est.length; i++) {
            datos.nextLine();
            System.out.println("Estudiante Nro " + (i + 1) + ":");
            System.out.print("Nombre: ");
            nombre = datos.nextLine();
            System.out.print("Género: ");
            genero = datos.nextLine().charAt(0);
            System.out.print("Nota: ");
            nota = datos.nextDouble();
            est[i] = new Estudiante(nombre, genero, nota);
        }
    }

    public static int porcentajeGenero(Estudiante est[]) {
        int cuentahombres = 0, cuentamujeres = 0;
        double porc;
        for (int i = 0; i < est.length; i++) {
            if (est[i].getGenero() == 'm') {
                cuentahombres++;
            }
        }
        cuentamujeres = est.length - cuentahombres;
        System.out.println("Porcentaje de Hombres: " + (cuentahombres * 100) / est.length + "%");
        System.out.println("Porcentaje de Mujeres: " + (cuentamujeres * 100) / est.length + "%");
        porc = cuentahombres * 100 / est.length;
        return cuentahombres;
    }

    public static double porcentajePromedio(Estudiante est[], int hom, char sex) {
        int muj = est.length - hom;
        int cuenta = 0;
        double promedio = 0, porch, porcm;
        for (int i = 0; i < est.length; i++) {
            promedio += est[i].getNota();
        }
        promedio = promedio / est.length;

        for (int i = 0; i < est.length; i++) {
            if (est[i].getNota() > promedio && est[i].getGenero() == sex) {
                cuenta++;
            }
        }
        porch = hom * 100 / est.length;
        porcm = muj * 100 / est.length;
        if (sex == 'm' && hom > 0) {
            porch = cuenta * porch / hom;
            return porch;
        } else if (muj > 0) {
            porcm = cuenta * porcm / muj;
            return porcm;
        } else {
            return 0;
        }
    }

    public static int notaAlta(Estudiante est[]) {
        double mayor;
        int indice = 0;
        mayor = est[0].getNota();
        for (int i = 1; i < est.length; i++) {
            if (est[i].getNota() > mayor) {
                mayor = est[i].getNota();
                indice = i;
            }
        }
        return indice;
    }

    public static void estMayorMenor(Estudiante est[], int aux1) {
        int i;
        int j;
        for (i=0; i < (aux1); i++) {
            for (j=0; j<(aux1-1); j++) {
                if (est[j].getNota()<est[j + 1].getNota()) {
                    Estudiante aux2;
                    aux2 = est[j];
                    est[j] = est[j+1];
                    est[j+1] = aux2;
                }

            }
        }

    }
}
