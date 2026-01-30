/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
package lab2herencia;

import com.toedter.calendar.JDateChooser; 
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class MenuPrincipal extends JFrame {

    private final Empresa empresa = new Empresa();

    private JTextField txtCodBusq;
    private JLabel lblEstado;
    private JTextArea areaLog;

    private final Color COLOR_FONDO = new Color(245, 245, 245); 
    private final Color COLOR_AZUL_OSC = new Color(66, 133, 244);
    private final Color COLOR_AZUL_CLAR = new Color(120, 170, 230);

    private final int ANCHO = 780;
    private final int ALTO = 480;

    public MenuPrincipal() {
        setTitle("Sistema Gestión Empleados");
        setSize(ANCHO, ALTO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        setLayout(new BorderLayout());
        getContentPane().setBackground(COLOR_FONDO);

        setJMenuBar(crearMenuBar());
        add(crearPanelIzquierdo(), BorderLayout.WEST);
        add(crearPanelCentro(), BorderLayout.CENTER);
        add(crearBarraEstado(), BorderLayout.SOUTH);

        setVisible(true);
        log("Sistema iniciado. Listo para trabajar.");
    }

  
    private JMenuBar crearMenuBar() {
        JMenuBar bar = new JMenuBar();

        JMenu mArchivo = new JMenu("Archivo");
        JMenuItem miSalir = new JMenuItem("Salir");
        miSalir.addActionListener(e -> dispose());
        mArchivo.add(miSalir);

        JMenu mEmpleados = new JMenu("Empleados");
        JMenuItem miRegistrar = new JMenuItem("Registrar");
        JMenuItem miHoras = new JMenuItem("Registrar horas");
        JMenuItem miVentas = new JMenuItem("Registrar ventas");
        JMenuItem miContrato = new JMenuItem("Actualizar contrato");

        miRegistrar.addActionListener(e -> mostrarFormularioRegistro());
        miHoras.addActionListener(e -> registrarHorasGUI());
        miVentas.addActionListener(e -> registrarVentasGUI());
        miContrato.addActionListener(e -> actualizarFechaGUI());

        mEmpleados.add(miRegistrar);
        mEmpleados.add(miHoras);
        mEmpleados.add(miVentas);
        mEmpleados.add(miContrato);

        JMenu mReportes = new JMenu("Reportes");
        JMenuItem miPago = new JMenuItem("Calcular pago");
        JMenuItem miReporte = new JMenuItem("Reporte general");

        miPago.addActionListener(e -> calcularPagoGUI());
        miReporte.addActionListener(e -> mostrarReporteGUI());

        mReportes.add(miPago);
        mReportes.add(miReporte);

        JMenu mAyuda = new JMenu("Ayuda");
        JMenuItem miAcerca = new JMenuItem("Acerca de");
        miAcerca.addActionListener(e -> JOptionPane.showMessageDialog(
                this,
                "Empresa J A T\nGestión de Empleados\nSwing + Herencia",
                "Acerca de",
                JOptionPane.INFORMATION_MESSAGE
        ));
        mAyuda.add(miAcerca);

        bar.add(mArchivo);
        bar.add(mEmpleados);
        bar.add(mReportes);
        bar.add(mAyuda);

        return bar;
    }


    private JPanel crearPanelIzquierdo() {
        JPanel left = new JPanel();
        left.setBackground(COLOR_FONDO);
        left.setPreferredSize(new Dimension(240, 0));
        left.setLayout(new BorderLayout(10, 10));
        left.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        JPanel busq = new JPanel();
        busq.setBackground(COLOR_FONDO);
        busq.setLayout(new GridLayout(0, 1, 6, 6));
        busq.setBorder(BorderFactory.createTitledBorder("Buscar empleado"));

        txtCodBusq = new JTextField();
        JButton btnBuscar = new JButton("Buscar por código");
        btnBuscar.setBackground(COLOR_AZUL_OSC);
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFocusPainted(false);

        btnBuscar.addActionListener(e -> buscarEmpleadoGUI());

        busq.add(new JLabel("Código:"));
        busq.add(txtCodBusq);
        busq.add(btnBuscar);


        JPanel acciones = new JPanel();
        acciones.setBackground(COLOR_FONDO);
        acciones.setLayout(new GridLayout(0, 1, 8, 8));
        acciones.setBorder(BorderFactory.createTitledBorder("Acciones"));

        JButton btnRegEmp = boton("Registrar empleado", COLOR_AZUL_CLAR);
        JButton btnHoras = boton("Registrar horas", COLOR_AZUL_CLAR);
        JButton btnVentas = boton("Registrar ventas", COLOR_AZUL_OSC);
        JButton btnContrato = boton("Actualizar contrato", COLOR_AZUL_OSC);
        JButton btnPago = boton("Calcular pago", Color.RED.darker());
        JButton btnReporte = boton("Reporte general", Color.DARK_GRAY);

        btnRegEmp.addActionListener(e -> mostrarFormularioRegistro());
        btnHoras.addActionListener(e -> registrarHorasGUI());
        btnVentas.addActionListener(e -> registrarVentasGUI());
        btnContrato.addActionListener(e -> actualizarFechaGUI());
        btnPago.addActionListener(e -> calcularPagoGUI());
        btnReporte.addActionListener(e -> mostrarReporteGUI());

        acciones.add(btnRegEmp);
        acciones.add(btnHoras);
        acciones.add(btnVentas);
        acciones.add(btnContrato);
        acciones.add(btnPago);
        acciones.add(btnReporte);

        left.add(busq, BorderLayout.NORTH);
        left.add(acciones, BorderLayout.CENTER);

        return left;
    }

    private JButton boton(String texto, Color color) {
        JButton b = new JButton(texto);
        b.setBackground(color);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Arial", Font.BOLD, 13));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return b;
    }

 
    private JPanel crearPanelCentro() {
        JPanel center = new JPanel(new BorderLayout(10, 10));
        center.setBackground(COLOR_FONDO);
        center.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Gestión de Empleados", SwingConstants.LEFT);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(COLOR_AZUL_OSC);

        areaLog = new JTextArea();
        areaLog.setEditable(false);
        areaLog.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(areaLog);

        center.add(titulo, BorderLayout.NORTH);
        center.add(scroll, BorderLayout.CENTER);

        return center;
    }


    private JPanel crearBarraEstado() {
        JPanel south = new JPanel(new BorderLayout());
        south.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        south.setBackground(Color.WHITE);

        lblEstado = new JLabel("Empresa J A T");
        lblEstado.setForeground(Color.DARK_GRAY);

        south.add(lblEstado, BorderLayout.WEST);
        return south;
    }

    private void setEstado(String msg) {
        lblEstado.setText(msg);
    }

    private void log(String msg) {
        areaLog.append("• " + msg + "\n");
    }



    private void buscarEmpleadoGUI() {
        try {
            int codigo = Integer.parseInt(txtCodBusq.getText().trim());
            Empleado empleado = empresa.buscarEmpleadoPorCodigo(codigo);

            if (empleado != null) {
                JOptionPane.showMessageDialog(this,
                        "Empleado encontrado:\n\n" + empleado.toString(),
                        "Búsqueda",
                        JOptionPane.INFORMATION_MESSAGE);
                setEstado("Encontrado: " + empleado.getNombre());
                log("Búsqueda OK. Código " + codigo + " -> " + empleado.getNombre());
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se encontró empleado con código: " + codigo,
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                setEstado("No encontrado");
                log("Búsqueda fallida. Código " + codigo);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Código inválido. Ingrese un número.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarFormularioRegistro() {
        String[] opciones = {"Estandar", "Temporal", "Ventas"};
        int seleccion = JOptionPane.showOptionDialog(this,
                "Seleccione el tipo de empleado:",
                "Registro de empleado",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        if (seleccion == JOptionPane.CLOSED_OPTION) return;

        JPanel panelForm = new JPanel(new GridLayout(0, 2, 6, 6));
        JTextField txtCodigo = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtSalario = new JTextField();
        JTextField txtTasaCom = new JTextField("0.05");

        JDateChooser chooserFinContrato = new JDateChooser();
        chooserFinContrato.setDateFormatString("dd/MM/yyyy");

        panelForm.add(new JLabel("Código:")); panelForm.add(txtCodigo);
        panelForm.add(new JLabel("Nombre:")); panelForm.add(txtNombre);
        panelForm.add(new JLabel("Salario base:")); panelForm.add(txtSalario);

        if (seleccion == 1) {
            panelForm.add(new JLabel("Fin contrato:"));
            panelForm.add(chooserFinContrato);
        } else if (seleccion == 2) {
            panelForm.add(new JLabel("Tasa comisión (0-1):"));
            panelForm.add(txtTasaCom);
        }

        int ok = JOptionPane.showConfirmDialog(this, panelForm,
                "Registrar: " + opciones[seleccion],
                JOptionPane.OK_CANCEL_OPTION);

        if (ok != JOptionPane.OK_OPTION) return;

        try {
            int codigo = Integer.parseInt(txtCodigo.getText().trim());
            String nombre = txtNombre.getText().trim();
            double salario = Double.parseDouble(txtSalario.getText().trim());

            if (nombre.isEmpty()) throw new IllegalArgumentException("El nombre no puede ir vacío.");

            Empleado nuevoEmp;

            if (seleccion == 0) {
                nuevoEmp = new EmpleadoEstandar(codigo, nombre, salario);
            } else if (seleccion == 1) {
                Date d = chooserFinContrato.getDate();
                if (d == null) throw new IllegalArgumentException("Seleccione la fecha fin del contrato.");

                Calendar fechaFin = Calendar.getInstance();
                fechaFin.setTime(d);
                limpiarHora(fechaFin);

                nuevoEmp = new EmpleadoTemporal(codigo, nombre, salario, fechaFin);
            } else {
                double tasaCom = Double.parseDouble(txtTasaCom.getText().trim());
                if (tasaCom < 0 || tasaCom > 1) throw new IllegalArgumentException("La tasa debe estar entre 0 y 1 (ej: 0.05).");
                nuevoEmp = new EmpleadoVentas(codigo, nombre, salario, tasaCom);
            }

            empresa.registrarEmpleado(nuevoEmp);

            JOptionPane.showMessageDialog(this, "Empleado registrado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            setEstado("Registrado: " + nombre);
            log("Registro OK. " + nombre + " (código " + codigo + ") tipo: " + opciones[seleccion]);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrarHorasGUI() {
        String codigoStr = JOptionPane.showInputDialog(this, "Código del empleado:");
        if (codigoStr == null) return;

        String horasStr = JOptionPane.showInputDialog(this, "Horas a registrar:");
        if (horasStr == null) return;

        try {
            int codigo = Integer.parseInt(codigoStr.trim());
            int horas = Integer.parseInt(horasStr.trim());

            empresa.registrarHoras(codigo, horas);

            JOptionPane.showMessageDialog(this, "Horas registradas.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            setEstado("Horas registradas. Cod: " + codigo);
            log("Horas registradas -> Cod " + codigo + " + " + horas);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrarVentasGUI() {
        String codigoStr = JOptionPane.showInputDialog(this, "Código del empleado de ventas:");
        if (codigoStr == null) return;

        String montoStr = JOptionPane.showInputDialog(this, "Monto de la venta:");
        if (montoStr == null) return;

        try {
            int codigo = Integer.parseInt(codigoStr.trim());
            double monto = Double.parseDouble(montoStr.trim());

            empresa.registrarVenta(codigo, monto);

            JOptionPane.showMessageDialog(this, "Venta registrada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            setEstado("Venta registrada. Cod: " + codigo);
            log("Venta registrada -> Cod " + codigo + " monto " + String.format("%.2f", monto));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarFechaGUI() {
        String codigoStr = JOptionPane.showInputDialog(this, "Código del empleado temporal:");
        if (codigoStr == null) return;

        JDateChooser chooser = new JDateChooser();
        chooser.setDateFormatString("dd/MM/yyyy");

        int ok = JOptionPane.showConfirmDialog(this, chooser,
                "Nueva fecha fin de contrato",
                JOptionPane.OK_CANCEL_OPTION);

        if (ok != JOptionPane.OK_OPTION) return;

        try {
            int codigo = Integer.parseInt(codigoStr.trim());
            Date d = chooser.getDate();
            if (d == null) throw new IllegalArgumentException("Debe seleccionar una fecha.");

            Calendar nuevaFecha = Calendar.getInstance();
            nuevaFecha.setTime(d);
            limpiarHora(nuevaFecha);

            empresa.actualizarFinContrato(codigo, nuevaFecha);

            JOptionPane.showMessageDialog(this, "Contrato actualizado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            setEstado("Contrato actualizado. Cod: " + codigo);
            log("Contrato actualizado -> Cod " + codigo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calcularPagoGUI() {
        String codigoStr = JOptionPane.showInputDialog(this, "Código del empleado:");
        if (codigoStr == null) return;

        try {
            int codigo = Integer.parseInt(codigoStr.trim());
            double pago = empresa.calcularPago(codigo);

            JOptionPane.showMessageDialog(this,
                    "Pago mensual: Lps. " + String.format("%.2f", pago),
                    "Pago",
                    JOptionPane.INFORMATION_MESSAGE);

            setEstado("Pago calculado. Cod: " + codigo);
            log("Pago calculado -> Cod " + codigo + " = " + String.format("%.2f", pago));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarReporteGUI() {
        try {
            String reporte = empresa.reporte();

            JTextArea areaTexto = new JTextArea(reporte);
            areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 12));
            areaTexto.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(areaTexto);
            scrollPane.setPreferredSize(new Dimension(540, 360));

            JOptionPane.showMessageDialog(this, scrollPane, "Reporte general", JOptionPane.PLAIN_MESSAGE);

            setEstado("Reporte generado");
            log("Reporte general generado.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarHora(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuPrincipal::new);
    }
}
