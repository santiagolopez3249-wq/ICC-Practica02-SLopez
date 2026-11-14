package icc;

/**
 * Clase para crear y manejar fechas (dia, mes y anio).
 * @author Santiago López Paulino
 * @version 1.0
 */
public class Fecha {

    // Atributos privados
    private int dia;
    private int mes;
    private int anio;

    /**
     * Constructor sin parametros. Crea una fecha inicial por omision
     */
    public Fecha() {
        this(1, 1, 2026);
    }

    /**
     * Constructor que va a recibir tres enteros: dia, mes y anio.
     * @param d dia de la fecha
     * @param m mes de la fecha (1 a 12)
     * @param a anio de la fecha
     */
    public Fecha(int d, int m, int a) {
        asignarFecha(d, m, a);
    }

    /**
     * Constructor que va a recibir dos enteros (dia y anio) y una cadena para el nombre del mes.
     * @param d dia de la fecha
     * @param a anio de la fecha
     * @param nombreMes cadena con el nombre del mes
     */
    public Fecha(int d, int a, String nombreMes) {
        int m = obtenerNumeroMes(nombreMes);
        asignarFecha(d, m, a);
    }

    /**
     * Constructor sobrecargado que recibe dia, cadena del mes y anio (año).
     * @param d dia de la fecha
     * @param nombreMes cadena con el nombre del mes
     * @param a anio de la fecha
     */
    public Fecha(int d, String nombreMes, int a) {
        int m = obtenerNumeroMes(nombreMes);
        asignarFecha(d, m, a);
    }

    /**
     * Metodo para asignar valor a los tres campos de una fecha.
     * @param d nuevo dia
     * @param m nuevo mes
     * @param a nuevo anio
     */
    public void asignarFecha(int d, int m, int a) {
        asignarAnio(a);
        asignarMes(m);
        asignarDia(d);
    }

    /**
     * Metodo modificador para asignar el dia.
     * @param nuevoDia valor para el dia
     */
    public void asignarDia(int nuevoDia) {
        int maxDias = diasDelMes(mes, anio);
        if (nuevoDia >= 1 && nuevoDia <= maxDias) {
            dia = nuevoDia;
        } else {
            dia = 1;
        }
    }

    /**
     * Metodo modificador para asignar el mes.
     * @param nuevoMes valor para el mes (1 a 12)
     */
    public void asignarMes(int nuevoMes) {
        if (nuevoMes >= 1 && nuevoMes <= 12) {
            mes = nuevoMes;
        } else {
            mes = 1;
        }
    }

    /**
     * Metodo modificador para asignar el anio.
     * @param nuevoAnio valor para el anio
     */
    public void asignarAnio(int nuevoAnio) {
        if (nuevoAnio > 0) {
            anio = nuevoAnio;
        } else {
            anio = 2026;
        }
    }

    /**
     * Metodo de acceso para obtener el dia de la fecha.
     * @return int el dia de la fecha
     */
    public int obtenerDia() {
        return dia;
    }

    /**
     * Metodo de acceso para obtener el mes de la fecha.
     * @return int el numero de mes de la fecha
     */
    public int obtenerMes() {
        return mes;
    }

    /**
     * Metodo de acceso para obtener el anio de la fecha.
     * @return int el anio de la fecha
     */
    public int obtenerAnio() {
        return anio;
    }

    /**
     * Metodo privado para determinar si un anio es bisiesto.
     * @param a anio a verificar
     * @return boolean true si es bisiesto y false en otro caso
     */
    private boolean esBisiesto(int a) {
        return (a % 4 == 0 && a % 100 != 0) || (a % 400 == 0);
    }

    /**
     * Metodo privado que va a devolver la cantidad de dias que tiene un mes.
     * @param m numero del mes (1 a 12)
     * @param a anio de la fecha
     * @return int cantidad de dias que tiene el mes
     */
    private int diasDelMes(int m, int a) {
        int dias = 31;
        if (m == 4 || m == 6 || m == 9 || m == 11) {
            dias = 30;
        } else {
            if (m == 2) {
                if (esBisiesto(a)) {
                    dias = 29;
                } else {
                    dias = 28;
                }
            }
        }
        return dias;
    }

    /**
     * Metodo privado que convierte una fecha al total de dias
     * transcurridos desde el anio 1.
     * @return int total de dias transcurridos desde el anio 1
     */
    private int enDias() {
        int a = anio - 1;
        int diasAniosAnteriores = a * 365 + (a / 4) - (a / 100) + (a / 400);
        return diasAniosAnteriores + diasTranscurridos();
    }

    /**
     * Metodo para obtener la diferencia en dias entre dos fechas.
     * @param f fecha con la que se desea calcular la diferencia
     * @return int diferencia en dias entre ambas fechas
     */
    public int obtenerDiferenciaDias(Fecha f) {
        return Math.abs(this.enDias() - f.enDias());
    }

    /**
     * Dada la fecha actual y un numero entero de dias, obtiene la fecha
     * que corresponde al sumar o restar esos dias.
     * @param dias cantidad de dias a desplazar (positivo o negativo)
     * @return Fecha nueva fecha resultante
     */
    public Fecha obtenerFecha(int dias) {
        int nuevoDia = dia + dias;
        int nuevoMes = mes;
        int nuevoAnio = anio;

        if (nuevoDia > diasDelMes(nuevoMes, nuevoAnio)) {
            do {
                nuevoDia = nuevoDia - diasDelMes(nuevoMes, nuevoAnio);
                nuevoMes = nuevoMes + 1;
                if (nuevoMes > 12) {
                    nuevoMes = 1;
                    nuevoAnio = nuevoAnio + 1;
                }
            } while (nuevoDia > diasDelMes(nuevoMes, nuevoAnio));
        } else {
            if (nuevoDia < 1) {
                do {
                    nuevoMes = nuevoMes - 1;
                    if (nuevoMes < 1) {
                        nuevoMes = 12;
                        nuevoAnio = nuevoAnio - 1;
                    }
                    nuevoDia = nuevoDia + diasDelMes(nuevoMes, nuevoAnio);
                } while (nuevoDia < 1);
            }
        }

        return new Fecha(nuevoDia, nuevoMes, nuevoAnio);
    }

    /**
     * Metodo para determinar si dos fechas son iguales.
     * @param f objeto Fecha contra el cual se va a comparar
     * @return boolean true si son iguales y false en otro caso
     */
    public boolean equals(Object f) {
        Fecha otra = (Fecha) f;
        return dia == otra.obtenerDia() && mes == otra.obtenerMes() && anio == otra.obtenerAnio();
    }

    /**
     * Metodo que devuelve el numero correspondiente a un mes a partir
     * de recibir una cadena con su nombre.
     * @param nombreMes cadena con el nombre del mes
     * @return int numero correspondiente al mes (1 a 12)
     */
    public int obtenerNumeroMes(String nombreMes) {
        String m = nombreMes.trim().toLowerCase();
        int num = 1;

        if (m.equals("enero")) {
            num = 1;
        } else if (m.equals("febrero")) {
            num = 2;
        } else if (m.equals("marzo")) {
            num = 3;
        } else if (m.equals("abril")) {
            num = 4;
        } else if (m.equals("mayo")) {
            num = 5;
        } else if (m.equals("junio")) {
            num = 6;
        } else if (m.equals("julio")) {
            num = 7;
        } else if (m.equals("agosto")) {
            num = 8;
        } else if (m.equals("septiembre")) {
            num = 9;
        } else if (m.equals("octubre")) {
            num = 10;
        } else if (m.equals("noviembre")) {
            num = 11;
        } else if (m.equals("diciembre")) {
            num = 12;
        }

        return num;
    }

    /**
     * Metodo que es el que devuelve la cadena correspondiente a un mes a partir
     * de recibir su numero.
     * @param numeroMes numero del mes (1 a 12)
     * @return String cadena con el nombre del mes
     */
    public String obtenerNombreMes(int numeroMes) {
        String nombre = "enero";

        if (numeroMes == 1) {
            nombre = "enero";
        } else if (numeroMes == 2) {
            nombre = "febrero";
        } else if (numeroMes == 3) {
            nombre = "marzo";
        } else if (numeroMes == 4) {
            nombre = "abril";
        } else if (numeroMes == 5) {
            nombre = "mayo";
        } else if (numeroMes == 6) {
            nombre = "junio";
        } else if (numeroMes == 7) {
            nombre = "julio";
        } else if (numeroMes == 8) {
            nombre = "agosto";
        } else if (numeroMes == 9) {
            nombre = "septiembre";
        } else if (numeroMes == 10) {
            nombre = "octubre";
        } else if (numeroMes == 11) {
            nombre = "noviembre";
        } else if (numeroMes == 12) {
            nombre = "diciembre";
        }

        return nombre;
    }

    /**
     * Metodo que especifica los dias transcurridos en el anio hasta la fecha.
     * Por ejemplo (para aclarar y no perderse), 30 de diciembre de 2013 devuelve 364.
     * @return int cantidad de dias transcurridos en el anio
     */
    public int diasTranscurridos() {
        int acumulados = 0;

        if (mes == 1) {
            acumulados = 0;
        } else if (mes == 2) {
            acumulados = 31;
        } else if (mes == 3) {
            acumulados = 59;
        } else if (mes == 4) {
            acumulados = 90;
        } else if (mes == 5) {
            acumulados = 120;
        } else if (mes == 6) {
            acumulados = 151;
        } else if (mes == 7) {
            acumulados = 181;
        } else if (mes == 8) {
            acumulados = 212;
        } else if (mes == 9) {
            acumulados = 243;
        } else if (mes == 10) {
            acumulados = 273;
        } else if (mes == 11) {
            acumulados = 304;
        } else if (mes == 12) {
            acumulados = 334;
        }

        if (esBisiesto(anio) && mes > 2) {
            acumulados = acumulados + 1;
        }

        return acumulados + dia;
    }

    /**
     * Metodo para representar una fecha en el formato dd/mes/aaaa.
     * @return String la fecha en formato dd/mes/aaaa
     */
    public String toString() {
        String d = "" + dia;
        if (dia < 10) {
            d = "0" + dia;
        }
        return d + "/" + obtenerNombreMes(mes) + "/" + anio;
    }
}
