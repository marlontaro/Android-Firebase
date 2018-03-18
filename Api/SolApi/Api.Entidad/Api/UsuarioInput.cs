using System;
using System.Collections.Generic;
using System.Text;

namespace Api.Entidad.Api
{
    public class UsuarioInput
    {
        public string nombre { get; set; }
        public string correo { get; set; }
        public int tipo { get; set; }
        public string socialId {get;set;} 
        public string firebase { get; set; }
    }
}
