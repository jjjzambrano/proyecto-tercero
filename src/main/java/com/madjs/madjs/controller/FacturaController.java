package com.madjs.madjs.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import com.madjs.madjs.model.Cliente;
import com.madjs.madjs.model.DetalleFactura;
import com.madjs.madjs.model.DetalleYFactura;
import com.madjs.madjs.model.Factura;
import com.madjs.madjs.model.Producto;
import com.madjs.madjs.service.ClienteService;
import com.madjs.madjs.service.FacturaService;
import com.madjs.madjs.service.ProductoService;
import com.madjs.madjs.utils.GeneratorPDF;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/factura")
public class FacturaController {
  @Autowired
  FacturaService facturaService;
  @Autowired
  ClienteService clienteService;
  @Autowired
  ProductoService productoService;
  @Autowired
  GeneratorPDF generatorPDF;

  @GetMapping("/lista")
  public String lista(Model model) {

    model.addAttribute("listaFactura", facturaService.listadoFactura());

    return "factura/lista";
  }

  @ModelAttribute("clientes")
  public List<Cliente> clientes() {
    return clienteService.listadoCliente();
  }

  @ModelAttribute("productos")
  public List<Producto> producto() {
    return productoService.listaProductos();
  }

  @GetMapping("/factura")
  public String factura(DetalleYFactura detalleYFactura, Model model) {

    if (detalleYFactura == null) {
      detalleYFactura = new DetalleYFactura();
    }

    model.addAttribute("facturaDetallle", detalleYFactura);

    Map<Long, String[]> tablaFactura = new HashMap<>();
    model.addAttribute("tablaFactura", tablaFactura);

    return "factura/factura";
  }

  @PostMapping("/procesaFormulario")
  public String procesaFormulario(DetalleYFactura detalleYFactura, @RequestBody String request, Model model) {
    String vista = "";
    Factura factura;

    if (detalleYFactura.getFacturaId() == 0) {
      factura = new Factura();

      factura.setFecha(detalleYFactura.getFecha());
      factura.setRuc(detalleYFactura.getRuc());
      factura.setSubtotal(detalleYFactura.getSubtotal());
    } else {
      factura = facturaService.findById(detalleYFactura.getFacturaId());
    }

    if (request.contains("agregar")) {

      DetalleFactura detalleFactura = new DetalleFactura();
      detalleFactura.setClienteId(detalleYFactura.getClienteId());
      detalleFactura.setProductoId(detalleYFactura.getProductoId());
      detalleFactura.setCantidadProducto(detalleYFactura.getCantidadProducto());

      factura.getDetalleFactura().add(detalleFactura);

      factura = facturaService.saveFactura(factura);

      detalleYFactura.setFacturaId(factura.getFacturaId());

      Map<Long, String[]> tablaFactura = new HashMap<>();

      for (DetalleFactura filaFactura : factura.getDetalleFactura()) {
        Cliente cliente = clienteService.findById(filaFactura.getClienteId());
        Producto producto = productoService.findById(filaFactura.getProductoId());

        String[] filaTabla = new String[] {
            cliente.getNombre(),
            producto.getNombre(),
            String.valueOf(filaFactura.getCantidadProducto())
        };

        tablaFactura.put(filaFactura.getDetalleFacturaId(), filaTabla);
      }

      model.addAttribute("tablaFactura", tablaFactura);
      model.addAttribute("detalleYFactura", detalleYFactura);
      vista = "factura/factura";
    }

    else if (request.contains("factura")) {
      facturaService.saveFactura(factura);
      model.addAttribute("listaFactura", facturaService.listadoFactura());
      vista = "factura/lista";
    }
    return vista;
  }

  @GetMapping("visualizar/{id}")
  public void generarPdf(@PathVariable long id, HttpServletResponse response) throws Exception {
    List<DetalleYFactura> detalleYFacturas = facturaService.vistaFindbyId(id);

    Map<String, Map> data = new HashMap<>();

    Map<String, String> encabezado = new HashMap<>();

    encabezado.put("fecha", String.valueOf(detalleYFacturas.get(0).getFecha()));
    encabezado.put("ruc", detalleYFacturas.get(0).getRuc());
    // encabezado.put("total", String.valueOf(detalleYFacturas.get(0).getTotal()));
    encabezado.put("titulo", "Factura");
    data.put("encabezado", encabezado);

    Map<String, Map> tablaDetalle = new HashMap<>();
    DecimalFormat formatoDecimal = new DecimalFormat("0.00");

    for (DetalleYFactura mDetalle : detalleYFacturas) {
      Map<String, String> fila = new HashMap<>();

      fila.put("nombreCliente", mDetalle.getNombreCliente());
      fila.put("cedula", mDetalle.getCedula());
      fila.put("direccion", mDetalle.getDireccion());
      fila.put("telefono", mDetalle.getTelefono());
      fila.put("correo", mDetalle.getCorreo());
      fila.put("nombreProducto", mDetalle.getNombreProdcuto());
      fila.put("cantidad", String.valueOf(mDetalle.getCantidadProducto()));
      fila.put("descripcion", mDetalle.getDescripcion());
      fila.put("precio", String.valueOf(mDetalle.getPrecio()));
      fila.put("total", String.valueOf(mDetalle.getSubtotal()));
      tablaDetalle.put("fila" + mDetalle.getDetalleFacturaId(), fila);
    }
    data.put("tablaDetalle", tablaDetalle);

    String rutaCompleta = generatorPDF.createPdf("/factura/pdf.html", data, "pdf");

    File archivoADescargar = new File(rutaCompleta);
    InputStream inputStream = new FileInputStream(archivoADescargar);

    IOUtils.copy(inputStream, response.getOutputStream());
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "attachment; filename=\"factura.pdf\"");
    response.flushBuffer();

  }

  @GetMapping("/estadistica")
  public String estadistica(Model model) {

    List<DetalleYFactura> listadoFactura = facturaService.vistaFacturaDetalle();

    Map<String, Double> facturaMap = new HashMap<>();
    for (DetalleYFactura filaDetalle : listadoFactura) {
      if (facturaMap.containsKey(filaDetalle.getNombreProdcuto())) {
        Double cantidadActual = facturaMap.get(filaDetalle.getNombreProdcuto());
        facturaMap.put(filaDetalle.getNombreProdcuto(),
            cantidadActual + filaDetalle.getSubtotal());
      } else {
        facturaMap.put(filaDetalle.getNombreProdcuto(), filaDetalle.getSubtotal());
      }
    }

    Map<Double, Map<String, Double>> arbol = new TreeMap<>(Collections.reverseOrder());

    for (Entry<String, Double> factura : facturaMap.entrySet()) {
      if (arbol.containsKey(factura.getValue())) {
        Map<String, Double> escala = arbol.get(factura.getValue());
        escala.put(factura.getKey(), factura.getValue());
      } else {
        Map<String, Double> escala = new HashMap<>();
        escala.put(factura.getKey(), factura.getValue());
        arbol.put(factura.getValue(), escala);
      }
    }

    String[] leyenda = new String[5];
    Double[] valores = new Double[5];
    int indice = 0;
    for (Entry<Double, Map<String, Double>> escala : arbol.entrySet()) {
      for (Entry<String, Double> factura : escala.getValue().entrySet()) {
        leyenda[indice] = String.valueOf(factura.getKey());
        valores[indice] = factura.getValue();
        indice++;
        if (indice == 5)
          break;
      }
      if (indice == 5)
        break;
    }

    model.addAttribute("leyenda", leyenda);
    model.addAttribute("valores", valores);

    return "/estadistica/estadistica";
  }

}
