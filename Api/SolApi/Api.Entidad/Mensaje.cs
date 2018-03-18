using NPoco;
using System;
using System.Collections.Generic;
using System.Text;

namespace Api.Entidad
{
    [TableName("Mensaje")]
    [PrimaryKey("IdMensaje")]
    public class Mensaje
    {
        public int IdMensaje { get; set; }
        public int De { get; set; }
        public int Para { get; set; }
        public string Contenido { get; set; }

    }
}
