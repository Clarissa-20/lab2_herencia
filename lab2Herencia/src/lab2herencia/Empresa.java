/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2herencia;


import java.util.ArrayList;
import java.util.Calendar;

public class Empresa {
    private ArrayList<Empleado> listaEmpleados = new ArrayList<>();

    public Empleado buscarEmpleadoPorCodigo(int codigo) {
        for (Empleado e : listaEmpleados) {
            if (e.getCodigo() == codigo) return e;
        }
        return null;
    }

    public void registrarEmpleado(Empleado empleado) {
        if (buscarEmpleadoPorCodigo(empleado.getCodigo()) != null) {
            throw new IllegalArgumentException("Ya existe un empleado con ese código.");
        }
        listaEmpleados.add(empleado);
    }

    public void registrarHoras(int codigo, int horas) {
        Empleado e = buscarEmpleadoPorCodigo(codigo);
        if (e == null) throw new IllegalArgumentException("Empleado no encontrado.");
        e.horasTrabajadas(horas);
    }

    public void registrarVenta(int codigo, double monto) {
        Empleado e = buscarEmpleadoPorCodigo(codigo);
        if (e == null) throw new IllegalArgumentException("Empleado no encontrado.");

        if (!(e instanceof EmpleadoVentas)) {
            throw new IllegalArgumentException("Ese empleado no es de ventas.");
        }
        ((EmpleadoVentas) e).registrarVentas(monto);
    }

    public void actualizarFinContrato(int codigo, Calendar nuevaFecha) {
        Empleado e = buscarEmpleadoPorCodigo(codigo);
        if (e == null) throw new IllegalArgumentException("Empleado no encontrado.");

        if (!(e instanceof EmpleadoTemporal)) {
            throw new IllegalArgumentException("Ese empleado no es temporal.");
        }
        ((EmpleadoTemporal) e).actualizarFechaFinContrato(nuevaFecha);
    }

    public double calcularPago(int codigo) {
        Empleado e = buscarEmpleadoPorCodigo(codigo);
        if (e == null) throw new IllegalArgumentException("Empleado no encontrado.");
        return e.calculoPago();
    }

    public String reporte() {
        int est = 0, temp = 0, ven = 0;
        StringBuilder sb = new StringBuilder("=== REPORTE ===\n");

        sb.append("\n-- Estándar --\n");
        for (Empleado e : listaEmpleados) {
            if (e instanceof EmpleadoEstandar) {
                est++;
                sb.append(e.toString())
                  .append(" | Horas: ").append(e.getHorasTotal())
                  .append(" | Pago: ").append(String.format("%.2f", e.calculoPago()))
                  .append("\n");
            }
        }

        sb.append("\n-- Temporales --\n");
        for (Empleado e : listaEmpleados) {
            if (e instanceof EmpleadoTemporal) {
                temp++;
                sb.append(e.toString())
                  .append(" | Horas: ").append(e.getHorasTotal())
                  .append(" | Pago: ").append(String.format("%.2f", e.calculoPago()))
                  .append("\n");
            }
        }

        sb.append("\n-- Ventas --\n");
        for (Empleado e : listaEmpleados) {
            if (e instanceof EmpleadoVentas ev) {
                ven++;
                sb.append(ev.toString())
                  .append(" | Horas: ").append(ev.getHorasTotal())
                  .append(" | Comisión mes: ").append(String.format("%.2f", ev.calcularComision()))
                  .append(" | Pago: ").append(String.format("%.2f", ev.calculoPago()))
                  .append("\n");
            }
        }

        sb.append("\nTotales: Estándar=").append(est)
          .append(" | Temporales=").append(temp)
          .append(" | Ventas=").append(ven);

        return sb.toString();
    }
}
