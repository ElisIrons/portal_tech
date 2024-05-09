package com.portal_tech.portal_tech.controllers.controllersMVC;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UsuarioController {

       @GetMapping("/usuario/novo/chamado")
        public String novoChamado(){
               return "usuario.novo.chamado"; // Este é o nome do arquivo HTML para a rota de novo chamado

       }

       @GetMapping("/usuario/chamados")
        public String chamadosFeitos() {
               return "usuario.chamados";
       }


       @GetMapping("/index/usuario")
        public String indexUsuario() {
               return "index.usuario";
       }

}
