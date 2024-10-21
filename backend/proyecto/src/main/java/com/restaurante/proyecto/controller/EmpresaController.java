
package com.restaurante.proyecto.controller;

import com.restaurante.proyecto.entities.Empresa;
import com.restaurante.proyecto.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/administradorgeneral")

public class EmpresaController{

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/api/verempresa")
    @ResponseBody
    public List<Empresa> listarEmpresa(){
        return empresaService.obtenerTodos();
    }


    @GetMapping("/nuevo")
    public String mostrarFormularioDeNuevaEmpresa(Model model){
        model.addAttribute("empresa",new Empresa());
        model.addAttribute("accion","/empresa/nuevo");
        return "frontend\\src\\app\\administradorgeneral\\page.tsx";
    }

    @PostMapping("/api/nuevo")
    @ResponseBody
    public ResponseEntity<Empresa> guardarNuevaEmpresa(@RequestBody Empresa empresa){
        Empresa nuevaEmpresa = empresaService.crearEmpresa(empresa);
        return ResponseEntity.ok(nuevaEmpresa);
    }


    @GetMapping("/api/editar/{idEmpresa}")
    public String mostrarFormularioEditarEmpresa(@PathVariable Long idEmpresa,@ModelAttribute Empresa empresa, Model model){
        model.addAttribute("empresa",empresa);
        model.addAttribute("accion","/api/empresa/editar/"+idEmpresa);
        return "formulario";
    }

    @PostMapping("/api/editar/{idEmpresa}")
    public String actualizarEmpresa(@PathVariable Long idEmpresa, @ModelAttribute Empresa empresa){
        empresaService.actualizarEmpresa(idEmpresa,empresa);
        return "redirect:/empresa";
    }

    @GetMapping("/api/eliminar/{idEmpresa}")
    public String eliminarEmpresa(@PathVariable Long idEmpresa){
        empresaService.eliminarEmpresa(idEmpresa);
        return "redirect:/empresa";
    }
}

